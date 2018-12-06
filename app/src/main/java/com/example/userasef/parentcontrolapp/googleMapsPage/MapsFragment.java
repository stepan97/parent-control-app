package com.example.userasef.parentcontrolapp.googleMapsPage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.payload.MyForbiddenLocation;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.view.Loader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener, IMapsContract.View, GoogleMap.OnMapClickListener {

    private static final String TAG = "TAGO";
    private static final String ARG_PARAM_USER_OBJECT = "user_object";
    private static final String ARG_PARAM_TIME = "time";
    private GoogleMap mMap;
    private MapView mapView;
    private TextView add_new_forbidden_location;
    private TextView add_marker_hint_TextView;
//    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
//    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    private ChildUser mChildUser;
    private LocationManager mLocationManager;
    private IMapsContract.Presenter presenter;
    private TextView popup_menu_TextView;
    private ArrayList<MyLatLng> locations_list = null;
    private boolean allowMapClicking = false;
    private MyForbiddenLocation forbidden_location = null;
    private Loader loader;

    public static MapsFragment newInstance(ChildUser childUser) {
        Gson gson = new Gson();
        String tmpUser = gson.toJson(childUser, ChildUser.class);

        Bundle args = new Bundle();
        args.putString(ARG_PARAM_USER_OBJECT, tmpUser);

        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MapsFragment() {
        // default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (getArguments() != null) {
            Gson gson = new Gson();
            mChildUser = gson.fromJson(getArguments().getString(ARG_PARAM_USER_OBJECT), ChildUser.class);
//            mUser = getArguments().getString(ARG_PARAM_USER_OBJECT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maps_fragment, container, false);

        initView(view);
        initListeners();

        return view;
    }

    private void initView(View view) {
        mapView = view.findViewById(R.id.my_map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        presenter = new MapsPresenter(this, getContext());

        popup_menu_TextView = view.findViewById(R.id.popup_menu_tv);
        add_new_forbidden_location = view.findViewById(R.id.add_forbidden_location_tv);
        add_marker_hint_TextView = view.findViewById(R.id.adding_marker_hint_tv);
        loader = view.findViewById(R.id.loader);
    }

    private void initListeners() {
        popup_menu_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenuForUserLocations();
            }
        });

        add_new_forbidden_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forbidden_location != null)
                    presenter.addNewForbiddenLocation(forbidden_location);
                else
                    showMessage("Please select a location on map.");
            }
        });
    }

    private void togglePopupMenuForUserLocations() {
        PopupMenu popupMenu = new PopupMenu(getContext(), popup_menu_TextView);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case 0:
                        // get and show forbidden locations
                        presenter.getForbiddenLocationForUser(mChildUser.getId());
                        break;
                    case 1:
                        // add new forbidden location popup menu button
                        if (mMap != null) {
                            mMap.clear();

                            allowMapClicking = true;
                            add_marker_hint_TextView.setVisibility(View.VISIBLE);
                        }
                        return true;
                    case 2:
                        showLocations(locations_list, false);
                        break;
                        default:
                            if(id > 2 && id < locations_list.size()){
                                Location location = new Location("");
                                location.setLongitude(locations_list.get(id).getLongitude());
                                location.setLatitude(locations_list.get(id).getLatitude());

                                drawMarker(location, mChildUser.getName(), locations_list.get(id).getDateAndTime(), true, true, false, false);
                                break;
                            }
                            return false;

                }

                return true;
            }
        });

        Menu menu = popupMenu.getMenu();

        int index = 0, groupId = 0;

        menu.add(groupId, index, index++, R.string.show_forbidden_locations);
        menu.add(groupId, index, index++, R.string.add_new_forbidden_location);
        menu.add(groupId, index, index++, R.string.show_user_locations);

        for (int i = index; i < locations_list.size(); i++) {
            menu.add(groupId, index, index++, locations_list.get(i).getDateAndTime());
        }

        popupMenu.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mMap = googleMap;

        presenter.getUserLocationsForToday(mChildUser.getId());

        mMap.setOnMapClickListener(this);
    }

    private void drawMarker(Location location, String username, String time, boolean clearOtherMarkers, boolean animateCamera, boolean draggable, boolean isForbidden) {
        if (mMap != null) {
            if (clearOtherMarkers)
                mMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());

            if(isForbidden){
                int height = 50;
                int width = 50;
                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.icon_map_forbidden_location);
                Bitmap b=bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

                mMap.addMarker(new MarkerOptions()
                        .position(gps)
                        .title(username)
                        .snippet(time)
                        .visible(true)
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)))
                        .setDraggable(draggable);
            }else{
                mMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title(username)
                    .snippet(time)
                    .visible(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(103)))
                    .setDraggable(draggable);
            }

            if (animateCamera)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 16.0f));

            if (draggable) {
                add_new_forbidden_location.setVisibility(View.VISIBLE);
                add_marker_hint_TextView.setVisibility(View.VISIBLE);
            } else {
                add_new_forbidden_location.setVisibility(View.GONE);
                add_marker_hint_TextView.setVisibility(View.GONE);
                allowMapClicking = false;
            }
        }

    }

    /****************  LOCATION LISTENER for CHILD APP  *****************/
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            drawMarker(location, mChildUser.getName(), "", true, true, false, false);
            mLocationManager.removeUpdates(this);
        } else {
            Log.d(TAG, "Location is NULL");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged: provider: " + provider + ", status: " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled: provider: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled: provider: " + provider);
    }


    /************** MAPS VIEW *******************/

    @Override
    public void showLocations(ArrayList<MyLatLng> list, boolean isForbidden) {

        if (mMap != null)
            mMap.clear();

        for (int i = 0; i < list.size(); i++) {
            MyLatLng my_location = list.get(i);

            Location location = new Location("");
            location.setLatitude(my_location.getLatitude());
            location.setLongitude(my_location.getLongitude());

            if (i == list.size() - 1) {
                drawMarker(location, getActivity().getString(R.string.last_known_location), my_location.getDateAndTime(), false, true, false, isForbidden);
            } else {
                drawMarker(location, mChildUser.getName(), my_location.getDateAndTime(), false, false, false, isForbidden);
            }
        }

        locations_list = list;
        popup_menu_TextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (!allowMapClicking)
            return;

        Location location = new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);

        drawMarker(location, "", "", true, false, true, true);
        // todo: change childId
        forbidden_location = new MyForbiddenLocation(mChildUser.getId(), location.getLatitude(), location.getLongitude());

        add_marker_hint_TextView.setVisibility(View.GONE);
    }

    @Override
    public void noInternet(String methodName, Object object) {

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoaderVisibility(int visibility) {
        loader.setVisibility(visibility);
    }
}

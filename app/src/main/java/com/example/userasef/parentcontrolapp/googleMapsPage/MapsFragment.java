package com.example.userasef.parentcontrolapp.googleMapsPage;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.response.MyLatLng;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback, LocationListener, IMapsContract.View {

    private static final String TAG = "TAGO";
    private static final String ARG_PARAM_USER_OBJECT = "user_object";
    private static final String ARG_PARAM_TIME = "time";
    private GoogleMap mMap;
    private MapView mapView;
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    private ChildUser mChildUser;
    private LocationManager mLocationManager;
    private IMapsContract.Presenter presenter;
    private ImageView popup_ImageView;
    private ArrayList<MyLatLng> locations_list = null;


    public static MapsFragment newInstance(ChildUser childUser){
        Gson gson = new Gson();
        String tmpUser = gson.toJson(childUser, ChildUser.class);

        Bundle args = new Bundle();
        args.putString(ARG_PARAM_USER_OBJECT, tmpUser);

        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MapsFragment(){
        // default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if(getArguments() != null){
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

    private void initView(View view){
        mapView = view.findViewById(R.id.my_map);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        presenter = new MapsPresenter(this);

        popup_ImageView = view.findViewById(R.id.popup_iv);
    }

    private void initListeners(){
        popup_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu();
            }
        });
    }

    private void togglePopupMenu(){
        PopupMenu popupMenu = new PopupMenu(getContext(), popup_ImageView);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();

                if(id >= 0 && id < locations_list.size()){
                    Location location = new Location("");
                    location.setLongitude(locations_list.get(id).getLongitude());
                    location.setLatitude(locations_list.get(id).getLatitude());

                    drawMarker(location, mChildUser.getName(), locations_list.get(id).getDateAndTime(), true, true);
                    return true;
                }else
                    return false;
            }
        });

        Menu menu = popupMenu.getMenu();

        for (int i = 0; i < locations_list.size(); i++) {
            menu.add(0, i, i, locations_list.get(i).getDateAndTime());
        }

//        MenuInflater menuInflater = popupMenu.getMenuInflater();
//        menuInflater.inflate(R.menu.menu_bottom_navigation, popupMenu.getMenu());
        popupMenu.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mMap = googleMap;

        presenter.getUserLocationsForToday(mChildUser.getId());

//        Location location = new Location("My Provider");
//
//        location.setLatitude(40.689247);
//        location.setLongitude(-74.044502);
//
//        if(location != null)
//            drawMarker(location);
    }

    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled)){
            Log.d(TAG, "GPS or NETWORK is UNAVAILABLE");
        } else{
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, this);
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                Log.d(TAG, "Network Provider passive: " + mLocationManager.getProvider("passive"));
//                Log.d(TAG, "Network Provider gps: " + mLocationManager.getProvider("gps"));
//                Log.d(TAG, "Network Provider network: " + mLocationManager.getProvider("network"));
            }

            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, this);
                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                Log.d(TAG, "GPS Provider passive: " + mLocationManager.getProvider("passive"));
//                Log.d(TAG, "GPS Provider gps: " + mLocationManager.getProvider("gps"));
//                Log.d(TAG, "GPS Provider network: " + mLocationManager.getProvider("network"));
            }
        }

        // todo: UNDERSTAND.
        // when i check here location is null, and under it does not call drawMarker.
        // but somehow drawMarker is called and in it location is NOT NULL
        if(location == null){
            Log.d(TAG, "LOCATION IS NULL");
        }else{
            Log.d(TAG, "LOCATION IS NOT NULL");
        }

        if (location != null) {
            drawMarker(location, "No user name", "No time", true, true);
        }
    }

    private void drawMarker(Location location, String username, String time, boolean clearOtherMarkers, boolean animateCamera) {
        if (mMap != null) {
            if(clearOtherMarkers)
                mMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title(username)
                    .snippet(time)
                    .visible(true));

            if(animateCamera)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 16.0f));
        }

    }


    /****************  LOCATION LISTENER for CHILD APP  *****************/
    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            drawMarker(location, mChildUser.getName(), "", true, true);
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
    public void showUserLocations(ArrayList<MyLatLng> list) {

        for (int i = 0; i < list.size(); i++) {
            MyLatLng my_location = list.get(i);

            Location location = new Location("");
            location.setLatitude(my_location.getLatitude());
            location.setLongitude(my_location.getLongitude());

            if(i == list.size() - 1){
                drawMarker(location, getActivity().getString(R.string.last_known_location), my_location.getDateAndTime(), false, true);
            }else{
                drawMarker(location, mChildUser.getName(), my_location.getDateAndTime(), false, false);
            }
        }

        locations_list = list;
        popup_ImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void noInternet(String methodName, Object object) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setLoaderVisibility(int visibility) {

    }
}

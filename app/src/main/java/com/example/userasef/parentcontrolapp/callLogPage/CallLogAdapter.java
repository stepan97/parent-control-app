package com.example.userasef.parentcontrolapp.callLogPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.ResponseModel;
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.net.ConnectException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CallLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICallLogContract.Presenter{

    private Context mContext;
    private ArrayList<MyCallLog> logList;
    private ICallLogContract.View mView;
    private static IParentService service;

    public CallLogAdapter(ICallLogContract.View view, Context context){
        mView = view;
        mContext = context;
        service = ParentClient.getClient().create(IParentService.class);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call_log, parent, false);

        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyCallLog item = logList.get(position);
        CallViewHolder viewHolder = (CallViewHolder) holder;
        viewHolder.number_TextView.setText(item.getNumber());
        String duration = item.getDuration() + " " + mContext.getString(R.string.duration_unit);
        viewHolder.duration_TextView.setText(duration);

        switch (item.getType()){
            case "INCOMING":
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_incoming_call);
                break;
            case "OUTGOING":
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_outgoing_call);
                break;
            case "MISSED":
            case "REJECTED":
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_missed_call);
                break;
            default:
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_question_mark);
        }

        if(item.getName() != null && !item.getName().equals(""))
            viewHolder.name_TextView.setText(item.getName());
        else
            viewHolder.name_TextView.setText(R.string.no_name);
    }

    @Override
    public int getItemCount() {
        return logList == null ? 0 : logList.size();
    }

    public void setCallLogList(ArrayList<MyCallLog> list){
        logList = list;
    }

    class CallViewHolder extends RecyclerView.ViewHolder{

        ImageView type_ImageView;
        TextView name_TextView;
        TextView number_TextView;
        TextView duration_TextView;

        public CallViewHolder(View itemView) {
            super(itemView);

            type_ImageView = itemView.findViewById(R.id.call_type_item_iv);
            name_TextView = itemView.findViewById(R.id.call_name_item_tv);
            number_TextView = itemView.findViewById(R.id.call_number_item_tv);
            duration_TextView = itemView.findViewById(R.id.call_duration_item_tv);
        }
    }


    /********************* PRESENTER ****************/

    @Override
    public void getCallLogs(String id) {
        mView.setLoaderVisibility(View.VISIBLE);

        service.getCallLogs(id).enqueue(new Callback<ResponseModel<ArrayList<MyCallLog>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<ArrayList<MyCallLog>>> call,@NonNull Response<ResponseModel<ArrayList<MyCallLog>>> response) {

                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                mView.showCallLog(response.body().getData());

                mView.setLoaderVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<ArrayList<MyCallLog>>> call,@NonNull Throwable t) {
                if(t instanceof ConnectException)
                    mView.showMessage(mContext.getString(R.string.no_internet));

                mView.setLoaderVisibility(View.GONE);

                ArrayList<MyCallLog> list = LocalExamples.getCallLog();
                mView.showCallLog(list);
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}

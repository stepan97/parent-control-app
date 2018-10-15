package com.example.userasef.parentcontrolapp.callLogPage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.data.response.CallLog;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CallLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICallLogContract.Presenter{

    private ArrayList<CallLog> logList;
    private ICallLogContract.View mView;

    public CallLogAdapter(ICallLogContract.View view){
        mView = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call_log, parent, false);

        CallViewHolder viewHolder = new CallViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CallLog item = logList.get(position);
        CallViewHolder viewHolder = (CallViewHolder) holder;
        viewHolder.number_TextView.setText(item.getNumber());
        viewHolder.length_TextView.setText(item.getLength());

    }

    @Override
    public int getItemCount() {
        return logList == null ? 0 : logList.size();
    }

    public void setCallLogList(ArrayList<CallLog> list){
        logList = list;
    }

    class CallViewHolder extends RecyclerView.ViewHolder{

        TextView number_TextView;
        TextView length_TextView;

        public CallViewHolder(View itemView) {
            super(itemView);

            number_TextView = itemView.findViewById(R.id.call_number_item_tv);
            length_TextView = itemView.findViewById(R.id.call_length_item_tv);
        }
    }


    /********************* PRESENTER ****************/

    @Override
    public void getCallLogs() {
        // network logic
        ArrayList<CallLog> list = LocalExamples.getCallLog();
        mView.showCallLog(list);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}

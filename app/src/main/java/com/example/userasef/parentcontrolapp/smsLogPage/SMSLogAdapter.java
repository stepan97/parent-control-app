package com.example.userasef.parentcontrolapp.smsLogPage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.callLogPage.ICallLogContract;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/10/2018.
 */

public class SMSLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISMSLogContract.Presenter{

    private ArrayList<String> logList;
    private ISMSLogContract.View mView;

    public SMSLogAdapter(ISMSLogContract.View view){
        mView = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_name, parent, false);

        SMSViewHolder holder = new SMSViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String item = logList.get(position);
        SMSViewHolder viewHolder = (SMSViewHolder) holder;
        viewHolder.number_TextView.setText(item);
    }

    public void setSMSLogList(ArrayList<String> list){
        this.logList = list;
    }

    @Override
    public int getItemCount() {
        return logList == null ? 0 : logList.size();
    }


    class SMSViewHolder extends RecyclerView.ViewHolder{

        TextView number_TextView;

        public SMSViewHolder(View itemView) {
            super(itemView);

            number_TextView = itemView.findViewById(R.id.select_name_item_tv);
        }
    }

    /*********************** PRESENTER *******************/

    @Override
    public void getSMSLogs() {
        // network logic
        mView.showSMSLog(LocalExamples.getSMSLogs());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}

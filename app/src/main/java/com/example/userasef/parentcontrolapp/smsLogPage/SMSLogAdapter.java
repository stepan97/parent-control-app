package com.example.userasef.parentcontrolapp.smsLogPage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userasef.parentcontrolapp.R;
import com.example.userasef.parentcontrolapp.callLogPage.ICallLogContract;
import com.example.userasef.parentcontrolapp.data.response.MySmsLog;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by userAsef on 10/10/2018.
 */

public class SMSLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISMSLogContract.Presenter{

    private ArrayList<MySmsLog> logList;
    private ISMSLogContract.View mView;

    public SMSLogAdapter(ISMSLogContract.View view){
        mView = view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms_log, parent, false);

        SMSViewHolder holder = new SMSViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MySmsLog item = logList.get(position);
        SMSViewHolder viewHolder = (SMSViewHolder) holder;

        viewHolder.date_TextView.setText(getMyDateString(item.getDate()));
        viewHolder.time_TextView.setText(getMyTimeString(item.getDate()));

        switch (item.getType()){
            case "INCOMING":
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_recieved_sms);
                break;
            case "OUTGOING":
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_sent_sms);
                break;
            default:
                viewHolder.type_ImageView.setImageResource(R.drawable.icon_question_mark);
        }

        if(item.getName() != null && !item.getNumber().equals(""))
            viewHolder.name_or_number_TextView.setText(item.getName());
        else
            viewHolder.name_or_number_TextView.setText(item.getNumber());
    }

    public void setSMSLogList(ArrayList<MySmsLog> list){
        this.logList = list;
    }

    @Override
    public int getItemCount() {
        return logList == null ? 0 : logList.size();
    }


    class SMSViewHolder extends RecyclerView.ViewHolder{

        ImageView type_ImageView;
        TextView name_or_number_TextView;
        TextView date_TextView;
        TextView time_TextView;


        public SMSViewHolder(View itemView) {
            super(itemView);

            type_ImageView = itemView.findViewById(R.id.sms_type_item_iv);
            name_or_number_TextView = itemView.findViewById(R.id.sms_name_or_number_item_tv);
            date_TextView = itemView.findViewById(R.id.sms_date_item_tv);
            time_TextView = itemView.findViewById(R.id.sms_time_item_tv);
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

    private String getMyDateString(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String dateStr = cal.get(Calendar.DAY_OF_MONTH) + "";
        dateStr = (cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + dateStr : dateStr);

        dateStr += "-" + (cal.get(Calendar.MONTH) < 10 ? "0" + cal.get(Calendar.MONTH) : cal.get(Calendar.MONTH));
        dateStr += "-" + cal.get(Calendar.YEAR);

        return dateStr;
    }

    private String getMyTimeString(Date date){
//        String timeStr = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String timeStr = cal.get(Calendar.HOUR_OF_DAY) + "";
        timeStr = (cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + timeStr : timeStr);

        timeStr += ":" + (cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : cal.get(Calendar.MINUTE));
        timeStr += ":" + cal.get(Calendar.SECOND);

        return timeStr;
    }
}

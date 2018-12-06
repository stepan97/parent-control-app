package com.example.userasef.parentcontrolapp.smsLogPage;

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
import com.example.userasef.parentcontrolapp.data.response.MySmsLog;
import com.example.userasef.parentcontrolapp.network.IParentService;
import com.example.userasef.parentcontrolapp.network.ParentClient;
import com.example.userasef.parentcontrolapp.utils.LocalExamples;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by userAsef on 10/10/2018.
 */

public class SMSLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISMSLogContract.Presenter{

    private ArrayList<MySmsLog> logList;
    private ISMSLogContract.View mView;
    private Context mContext;
    private static IParentService service;

    public SMSLogAdapter(ISMSLogContract.View view, Context context){
        mView = view;
        mContext = context;
        service = ParentClient.getClient().create(IParentService.class);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms_log, parent, false);

        return new SMSViewHolder(view);
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
    public void getSMSLogs(String id) {
        mView.setLoaderVisibility(View.VISIBLE);

        service.getSmsLogs(id).enqueue(new Callback<ResponseModel<ArrayList<MySmsLog>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel<ArrayList<MySmsLog>>> call,@NonNull Response<ResponseModel<ArrayList<MySmsLog>>> response) {
                if(response.body() == null){
                    mView.showMessage(mContext.getString(R.string.error_try_again_later));
                    return;
                }

                if(response.body().getErrors() != null){
                    mView.showMessage(response.body().getErrors());
                    return;
                }

                mView.showSMSLog(response.body().getData());

                mView.setLoaderVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel<ArrayList<MySmsLog>>> call,@NonNull Throwable t) {
                if(t instanceof ConnectException){
                    mView.showMessage(mContext.getString(R.string.no_internet));
                }

                mView.setLoaderVisibility(View.GONE);

                mView.showSMSLog(LocalExamples.getSMSLogs()); //todo: delete this line
            }
        });
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

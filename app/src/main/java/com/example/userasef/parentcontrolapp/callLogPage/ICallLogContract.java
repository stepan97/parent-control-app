package com.example.userasef.parentcontrolapp.callLogPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.response.MyCallLog;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/8/2018.
 */

public interface ICallLogContract {
    interface View extends IBaseView {
        void showCallLog(ArrayList<MyCallLog> list);
    }

    interface Presenter extends IBasePresenter {
        void getCallLogs(String id);
    }
}

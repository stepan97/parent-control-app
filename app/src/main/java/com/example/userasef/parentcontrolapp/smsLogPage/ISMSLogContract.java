package com.example.userasef.parentcontrolapp.smsLogPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.response.MySmsLog;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/10/2018.
 */

public interface ISMSLogContract {
    interface View extends IBaseView{
        void showSMSLog(ArrayList<MySmsLog> list);
    }

    interface Presenter extends IBasePresenter{
        void getSMSLogs(/*id of the user ? name of the user ?*/);
    }
}

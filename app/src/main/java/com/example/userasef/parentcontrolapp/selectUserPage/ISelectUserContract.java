package com.example.userasef.parentcontrolapp.selectUserPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;

import java.util.ArrayList;

/**
 * Created by userAsef on 10/8/2018.
 */

public interface ISelectUserContract {
    interface View extends IBaseView {

        void setAdapterChildUserList(ArrayList<ChildUser> list);
        void gotoSelectedUserFragment(ChildUser selectedItem);
    }

    interface Presenter extends IBasePresenter {
        void getAllChildUsers();
    }
}

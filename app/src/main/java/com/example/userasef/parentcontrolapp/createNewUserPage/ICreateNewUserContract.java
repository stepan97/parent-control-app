package com.example.userasef.parentcontrolapp.createNewUserPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;

/**
 * Created by userAsef on 10/9/2018.
 */

public interface ICreateNewUserContract {
    interface View extends IBaseView{
        void newUserCreationSuccess(String name);
    }

    interface Presenter extends IBasePresenter{
        void createNewUser(String name);
    }
}

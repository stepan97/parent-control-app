package com.example.userasef.parentcontrolapp.createNewUserPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;

/**
 * Created by userAsef on 10/9/2018.
 */

public interface ICreateNewUserContract {
    interface View extends IBaseView{
        void newUserCreationSuccess(String name);
        void newUserCreatingError(String message);
    }

    interface Presenter extends IBasePresenter{
        void createNewUser(String name);
    }
}

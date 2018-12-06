package com.example.userasef.parentcontrolapp.createNewUserPage;

import com.example.userasef.parentcontrolapp.IBasePresenter;
import com.example.userasef.parentcontrolapp.IBaseView;
import com.example.userasef.parentcontrolapp.data.response.ChildUser;

/**
 * Created by userAsef on 10/9/2018.
 */

public interface ICreateNewUserContract {
    interface View extends IBaseView{
        void newUserCreationSuccess(ChildUser childUser);
    }

    interface Presenter extends IBasePresenter{
        void createNewUser(ChildUser childUser);
    }
}

package com.example.userasef.parentcontrolapp.createNewUserPage;

/**
 * Created by userAsef on 10/9/2018.
 */

public class CreateNewUserPresenter implements ICreateNewUserContract.Presenter {

    private ICreateNewUserContract.View mView;

    public CreateNewUserPresenter(ICreateNewUserContract.View view){
        mView = view;
    }

    @Override
    public void createNewUser(String name) {
        // network request
        mView.newUserCreationSuccess(name);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}

package com.cabezas.alexander.mvp_pattern.login;

import com.cabezas.alexander.mvp_pattern.R;
import com.cabezas.alexander.mvp_pattern.home.HomeActivity;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by alexandercabezas on 23/10/17.
 */

public class LoginPresenterImpl implements LoginPresenter{

    LoginView mView;
    LoginInteractor mLoginInteractor;

    LoginPresenterImpl(LoginView view) {
        mView = view;
        mLoginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onLoginButtonPressed(String userNameText, String passwordText) {
       boolean result = mLoginInteractor.loginUser(userNameText, passwordText);

        if(result) {
            Intent intent = new Intent((Activity)mView, HomeActivity.class);
            ((Activity)mView).startActivity(intent);
        } else {
            mView.onLoginFailed(R.string.login_failed);
        }
    }
}

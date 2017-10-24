package com.cabezas.alexander.mvp_pattern.login;

/**
 * Created by alexandercabezas on 23/10/17.
 */

public interface LoginPresenter {

    void onDestroy();
    void onLoginButtonPressed(String userNameText, String passwordText);
}

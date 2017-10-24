package com.cabezas.alexander.mvp_pattern.login;

/**
 * Created by alexandercabezas on 23/10/17.
 */

public class LoginInteractorImpl implements LoginInteractor{

    @Override
    public boolean loginUser(String userName, String password) {
        if(userName.equals("username") && password.equals("12345")) {
            return true;
        } else {
            return false;
        }
    }
}

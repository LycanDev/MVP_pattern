package com.cabezas.alexander.mvp_pattern.control;

import com.cabezas.alexander.mvp_pattern.login.LoginActivity;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by alexandercabezas on 23/10/17.
 */

public class ControlPresenterImpl implements ControlPresenter{

    private Activity mActivity;


    ControlPresenterImpl(ControlView view) {
        mActivity = (Activity) view;
    }

    @Override
    public void goToLoginScreen() {

        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }
}

package com.cabezas.alexander.mvp_pattern.control;

import com.cabezas.alexander.mvp_pattern.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ControlActivity extends AppCompatActivity implements ControlView{

    ControlPresenter mControlPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mControlPresenter = new ControlPresenterImpl(this);
        mControlPresenter.goToLoginScreen();
    }
}

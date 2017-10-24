package com.cabezas.alexander.mvp_pattern.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cabezas.alexander.mvp_pattern.R;

public class HomeActivity extends AppCompatActivity implements HomeView{

    HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenterImpl();
    }
}

package com.cabezas.alexander.mvp_pattern.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cabezas.alexander.mvp_pattern.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.user_name_edit_text)
    EditText userName;
    @BindView(R.id.password_edit_text)
    EditText userPassword;
    @BindView(R.id.login_button)
    Button login;

    private LoginPresenter mPresenter;

    private void onLoginButtonPressed() {
        String userNameText = userName.getText().toString();
        String passwordText = userPassword.getText().toString();
        mPresenter.onLoginButtonPressed(userNameText, passwordText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonPressed();
            }
        });
    }

    @Override
    public void onLoginFailed(int id) {
        String message = getResources().getString(id);
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}

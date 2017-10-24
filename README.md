# MVP_pattern

In this article I will explain how to create a basic application with WVP pattern, all the code is available here.

For this tutorial I asume that you are already familiar with Android development and the java programming lenguaje, so I won't explain the most basic things about creation an Android application or basics in java programming. For basic information there are many good sources of information in internet like Udacity.

What is MVP?

MVP stands for Model View Controller, this pattern helps to separate all the related functionality from how it is represented on the screen. That means that it would be possible to replace the design with cero extra work, since all the logic is in a different layer.

MVP project example.

This project contains a small example on how create an Android application using the MVP pattern, for this I use one of the most common functionalities in an application, the login process.

How the example application was created?

First create a project in AndroidStudio with the following packages.

```
control
home
login

```
Inside the control package create the following classes

```
class ControlActivity
interface ControlPresenter
class ControlPresenterImpl
interface ControlView
```
ControlActivity is the start point of the application and it basically send the user to the login screen. It will implements the ControlView interface and will create the ControlPresenterImpl instance.

```
public class ControlActivity extends AppCompatActivity implements ControlView{

    ControlPresenter mControlPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mControlPresenter = new ControlPresenterImpl(this);
        mControlPresenter.goToLoginScreen();
    }
}
```

The controlPresenterImpl will hace a reference to the activity and will have the logic related to the screen, is this case there is no screen, so it will only contains the intent to the login

```
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
```

Now the login process, this will show how is the interaction between the layers.

In the login package create the following classes and interfaces

```
class LoginActivity
interface LoginInteractor
class LoginInteractorImpl
interface LoginPresenter
class LoginPresenterImpl
interface LoginView
```

The LoginActivity will implement the LoginView interface which provides all methods that will be used for communication between the view and the presenter, like show the login errors for example.

```
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
```

The LoginPresenterImpl will implement the LoginPresenter interface, which will handle the events from the view and will call the methods that actually have some functionality in the interactors.

```
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

```





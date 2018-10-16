package com.cloudminds.sain.baasapp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by sain on 10/16/18
 */
public class LoginHomeActivity extends AppCompatActivity {
    private static final String TAG = "LoginHomeActivity";
    private LoginFacade loginFacade;
    private EditText phoneEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.login_home, null);
        setContentView(root);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setToolBar();
        if (loginFacade == null) {
            loginFacade = new LoginFacade(root);
        }
        initView();
    }

    private void initView() {
        phoneEdit = findViewById(R.id.phone);
        passwordEdit = findViewById(R.id.password);
        ImageView imageView = findViewById(R.id.bar_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                if (loginFacade.getState() != LoginFacade.LOGIN) {
                    loginFacade.setContent(LoginFacade.LOGIN);
                    return;
                }
                finish();
            }
        });

        Button submit = findViewById(R.id.login_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(isMobileNumber(phone) && password.length()>= 6) {
                    //TODO: 登录处理逻辑
                    Log.i(TAG, "input ok: ");
                } else {
                    Log.e(TAG, "input illegal" );
                }
            }
        });
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsRelative(0, 0);
        setSupportActionBar(toolbar);
    }


    public void switchRegister(View view) {
        loginFacade.setContent(LoginFacade.REGISTER);
    }

    public void forgetPassWord(View view) {
        loginFacade.setContent(LoginFacade.FORGET_PASSWORD);
    }

    public static boolean isMobileNumber(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}

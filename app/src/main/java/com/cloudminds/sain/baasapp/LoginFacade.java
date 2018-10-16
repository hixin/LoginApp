package com.cloudminds.sain.baasapp;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * created by sain on 10/16/18
 */
public class LoginFacade {
    private static final String TAG = "LoginFacade";

    public static final int LOGIN = 1;
    public static final int REGISTER = LOGIN << 1;
    public static final int FORGET_PASSWORD = LOGIN << 2;

    private TextView barTitle;
    private TextView passWord;
    private TextView submit;
    private TextView forget;
    private TextView register;
    private int state = LOGIN;

    public int getState() {
        return state;
    }

    public LoginFacade(View root) {
        barTitle = root.findViewById(R.id.bar_title);
        passWord = root.findViewById(R.id.password);
        submit = root.findViewById(R.id.login_btn);
        forget = root.findViewById(R.id.forget_text);
        register = root.findViewById(R.id.register_text);
    }

    public void setContent(int type) {
        Log.i(TAG, "setContent: " + type);
        switch (type) {
            case LOGIN:
                setLogin();
                break;
            case REGISTER:
                setRegister();
                break;
            case FORGET_PASSWORD:
                setForgetPassword();
                break;
        }
    }

    private void setLogin() {
        state = LOGIN;
        forget.setVisibility(View.VISIBLE);
        register.setVisibility(View.VISIBLE);
        barTitle.setText(R.string.login_title);
        passWord.setHint(R.string.login_password_edit);
        submit.setText(R.string.login_button);
    }

    private void setRegister() {
        state = REGISTER;
        forget.setVisibility(View.GONE);
        register.setVisibility(View.GONE);
        barTitle.setText(R.string.register_title);
        passWord.setHint(R.string.other_password_edit);
        submit.setText(R.string.other_button);
    }

    private void setForgetPassword() {
        state = FORGET_PASSWORD;
        forget.setVisibility(View.GONE);
        register.setVisibility(View.GONE);
        barTitle.setText(R.string.forget_title);
        passWord.setHint(R.string.other_password_edit);
        submit.setText(R.string.other_button);
    }

}

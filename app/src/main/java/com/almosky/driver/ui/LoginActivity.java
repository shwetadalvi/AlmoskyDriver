package com.almosky.driver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.almosky.driver.BaseActivity;
import com.almosky.driver.R;
import com.almosky.driver.contracts.LoginContracts;
import com.almosky.driver.databinding.ActivityLoginBinding;
import com.almosky.driver.presenter.LoginPresenterImpl;
import com.almosky.driver.utils.PrefUtils;

import androidx.databinding.DataBindingUtil;

import static com.almosky.driver.utils.Constants.DEFAULT_PREFERENCE;
import static com.almosky.driver.utils.Constants.LOGIN_PREFERENCE;
import static com.almosky.driver.utils.Constants.LOGIN_PREFERENCE_DRVER_ID;
import static com.almosky.driver.utils.Constants.REGISTRATION_TOKEN_PREFERENCE;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContracts.ILoginView {

    EditText pin2;
    Button buttonLogin;
    LoginPresenterImpl mPresenter;
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mBinding.buttonLogin.setOnClickListener(this);
        mPresenter = new LoginPresenterImpl(this);
    }


    boolean hasEmptyFields(String pin1) {
        return isEmpty(pin1);
    }

    @Override public void onClick(View v) {
        String pin = mBinding.pin2.getText().toString();
        if (!hasEmptyFields(pin)) {
            showProgress("");
            PrefUtils prefUtils = new PrefUtils(this);
            String token = prefUtils.getStringPreference(DEFAULT_PREFERENCE,REGISTRATION_TOKEN_PREFERENCE);
            mPresenter.doLogin(pin,token);
        }
    }

    @Override public void success(String message) {
        PrefUtils prefUtils = new PrefUtils(this);
        prefUtils.putBooleanPreference(DEFAULT_PREFERENCE, LOGIN_PREFERENCE, true);
        prefUtils.putStringPreference(DEFAULT_PREFERENCE, LOGIN_PREFERENCE_DRVER_ID, message);
        startActivity(new Intent(this, NextOrderActivity.class));
        hideProgress();
        finish();
    }

    @Override public void failed(String message) {
        showSnackBar(mBinding.getRoot(), message, 15000);
        hideProgress();
    }

    @Override public void error(String message) {
        showSnackBar(mBinding.getRoot(), message, 15000);
        hideProgress();
    }
}

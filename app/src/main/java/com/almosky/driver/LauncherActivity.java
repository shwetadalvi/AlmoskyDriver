package com.almosky.driver;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.almosky.driver.ui.LoginActivity;
import com.almosky.driver.ui.NextOrderActivity;
import com.almosky.driver.utils.PrefUtils;

import static com.almosky.driver.utils.Constants.DEFAULT_PREFERENCE;
import static com.almosky.driver.utils.Constants.LOGIN_PREFERENCE;

public class LauncherActivity extends BaseActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefUtils prefUtils = new PrefUtils(this);
        if (prefUtils.getBooleanPreference(DEFAULT_PREFERENCE, LOGIN_PREFERENCE)){
            startActivity(new Intent(this, NextOrderActivity.class));
        }else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override protected void onPause() {
        super.onPause();
        finish();
    }
}

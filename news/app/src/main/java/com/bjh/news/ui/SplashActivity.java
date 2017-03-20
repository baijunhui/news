package com.bjh.news.ui;

import android.content.Intent;
import android.os.Handler;

import com.bjh.news.MainActivity;
import com.bjh.news.R;
import com.bjh.news.base.BaseActivity;

/**
 * Created by baijunhui on 17-3-20.
 */

public class SplashActivity extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        }, 2000);
    }

    private void toMain() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public int getContentViewId() {
        return R.layout.splash_activity;
    }
}

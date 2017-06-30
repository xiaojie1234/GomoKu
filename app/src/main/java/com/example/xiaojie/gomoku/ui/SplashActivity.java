package com.example.xiaojie.gomoku.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.xiaojie.gomoku.R;
import com.example.xiaojie.gomoku.view.SplashView;

/**
 * Created by xiaojie on 2017/6/15.
 */

public class SplashActivity extends BaseActivity implements SplashView{

    private static final long DELAY = 2000;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResById());
    }

    @Override
    protected void init() {
        super.init();
        skip();
    }

    @Override
    public void skip() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },DELAY);

    }
    @Override
    public int getLayoutResById() {
        return R.layout.activity_splash;
    }


}

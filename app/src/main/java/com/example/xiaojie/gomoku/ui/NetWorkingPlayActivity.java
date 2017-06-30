package com.example.xiaojie.gomoku.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xiaojie.gomoku.R;
import com.example.xiaojie.gomoku.databinding.ActivityMainBinding;
import com.example.xiaojie.gomoku.databinding.ActivityNetworkingplayBinding;

/**
 * Created by xiaojie on 2017/6/24.
 */

public class NetWorkingPlayActivity extends BaseActivity {

    private ActivityNetworkingplayBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutResById());
    }

        @Override
    public int getLayoutResById() {
        return R.layout.activity_networkingplay;
    }

}

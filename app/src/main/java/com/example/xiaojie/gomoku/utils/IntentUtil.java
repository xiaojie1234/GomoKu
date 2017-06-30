package com.example.xiaojie.gomoku.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by xiaojie on 2017/6/24.
 */

public class IntentUtil {
    public static void skinActivity(Context activity, Class activityClass){
        Intent intent = new Intent();
        intent.setClass(activity, activityClass);
        activity.startActivity(intent);
    }
}

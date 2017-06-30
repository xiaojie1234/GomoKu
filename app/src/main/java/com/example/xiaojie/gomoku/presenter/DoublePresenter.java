package com.example.xiaojie.gomoku.presenter;

import android.view.View;

import com.example.xiaojie.gomoku.bean.PanelBean;
import com.example.xiaojie.gomoku.bean.UserBean;

/**
 * Created by xiaojie on 2017/6/24.
 */

public interface DoublePresenter {

    void restartGame(View v);
    void preparedGame(View v);
    void withdrawGame(View v);
    void surrenderGame(View v);
    void initData(UserBean white, UserBean black, PanelBean panelBean);
    boolean chess(int x, int y);
    void checkIsGameOver();
}

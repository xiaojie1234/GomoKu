package com.example.xiaojie.gomoku.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.xiaojie.gomoku.R;
import com.example.xiaojie.gomoku.bean.PanelBean;
import com.example.xiaojie.gomoku.bean.UserBean;
import com.example.xiaojie.gomoku.databinding.ActivityDoubleplayBinding;
import com.example.xiaojie.gomoku.presenter.DoublePresenter;
import com.example.xiaojie.gomoku.presenter.impl.DoublePresenterImpl;
import com.example.xiaojie.gomoku.view.DoubleView;

/**
 * Created by xiaojie on 2017/6/24.
 */

public class DoublePlayActivity extends BaseActivity implements DoubleView{

    private ActivityDoubleplayBinding binding;
    private DoublePresenter mDoublePresenter;
    private WuZiQiPanel panel;
    private UserBean blackUser;
    private UserBean whiteUser;
    private PanelBean mPanelBean;
    private boolean status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutResById());
        mDoublePresenter = new DoublePresenterImpl(this);
        binding.setPresenter((DoublePresenterImpl) mDoublePresenter);
        status=true;
        binding.setStatus(status);
        whiteUser = new UserBean();
        blackUser = new UserBean();
        mPanelBean = new PanelBean();
        mDoublePresenter.initData(whiteUser,blackUser, mPanelBean);
        panel = (WuZiQiPanel) findViewById(R.id.panel);
        panel.setWhiteUser(whiteUser);
        panel.setBlackUser(blackUser);
        panel.setPanelBean(mPanelBean);
    }

    @Override
    public void chessSuccess() {
        Log.i("chessSuccess","ok");
        panel.invalidate();
        mDoublePresenter.checkIsGameOver();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("wj","in");
        if(whiteUser.ismIsGameOVer() && blackUser.ismIsGameOVer()){
            Log.i("wj","false");
            return false;
        }
        int action = event.getAction();
        if(action == MotionEvent.ACTION_UP){
            int x = (int) event.getX();
            int y = (int) event.getY();
            Log.i("wj",x+","+y);
            return mDoublePresenter.chess(x,y);
        }
        return true;
    }


    @Override
    public int getLayoutResById() {
        return R.layout.activity_doubleplay;
    }

    @Override
    public void refreshPanel() {
        panel.invalidate();
    }

    @Override
    public void gameIsOver() {
        if(whiteUser.ismIsWinner()){
            Toast.makeText(DoublePlayActivity.this,"白棋胜",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(DoublePlayActivity.this,"黑棋胜",Toast.LENGTH_SHORT).show();
        }
        status=!status;
        binding.setStatus(status);
    }

    @Override
    public void isClear() {

    }

    @Override
    public void beginGame(){
        status=!status;
        binding.setStatus(status);
    }


}

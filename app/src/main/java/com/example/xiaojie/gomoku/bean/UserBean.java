package com.example.xiaojie.gomoku.bean;

import android.databinding.BaseObservable;
import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.List;

/**
 * Created by xiaojie on 2017/6/25.
 */

public class UserBean extends BaseObservable{

    private List<Point> PieceList;
    private boolean mIsGameOVer;
    private boolean mIsWinner;

    public List<Point> getPieceList() {
        return PieceList;
    }

    public void setPieceList(List<Point> pieceList) {
        PieceList = pieceList;
        notifyChange();
    }

    public boolean ismIsGameOVer() {
        return mIsGameOVer;
    }

    public void setmIsGameOVer(boolean mIsGameOVer) {
        this.mIsGameOVer = mIsGameOVer;
    }

    public boolean ismIsWinner() {
        return mIsWinner;
    }

    public void setmIsWinner(boolean mIsWinner) {
        this.mIsWinner = mIsWinner;
    }
}

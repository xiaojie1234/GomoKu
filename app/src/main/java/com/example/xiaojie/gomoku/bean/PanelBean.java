package com.example.xiaojie.gomoku.bean;

import android.databinding.BaseObservable;

/**
 * Created by xiaojie on 2017/6/25.
 */

public class PanelBean extends BaseObservable{

    private int mPanelWidth;
    private float mLineHeight;
    private int MAX_LINE;
    private int MAX;
    private boolean isWhite;
    private float ratioPieceofLineHeight;

    public float getRatioPieceofLineHeight() {
        return ratioPieceofLineHeight;
    }

    public void setRatioPieceofLineHeight(float ratioPieceofLineHeight) {
        this.ratioPieceofLineHeight = ratioPieceofLineHeight;
    }

    public int getmPanelWidth() {
        return mPanelWidth;
    }

    public void setmPanelWidth(int mPanelWidth) {
        this.mPanelWidth = mPanelWidth;
    }

    public float getmLineHeight() {
        return mLineHeight;
    }

    public void setmLineHeight(float mLineHeight) {
        this.mLineHeight = mLineHeight;
    }

    public int getMAX_LINE() {
        return MAX_LINE;
    }

    public void setMAX_LINE(int MAX_LINE) {
        this.MAX_LINE = MAX_LINE;
    }

    public int getMAX() {
        return MAX;
    }

    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
        notifyChange();
    }
}

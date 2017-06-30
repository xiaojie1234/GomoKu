package com.example.xiaojie.gomoku.presenter.impl;

import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.xiaojie.gomoku.bean.PanelBean;
import com.example.xiaojie.gomoku.bean.UserBean;
import com.example.xiaojie.gomoku.presenter.DoublePresenter;
import com.example.xiaojie.gomoku.view.DoubleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/24.
 */

public class DoublePresenterImpl implements DoublePresenter {

    private DoubleView mDoubleView;
    private List<Point> whiteList;
    private List<Point> blackList;
    private UserBean whiteUser;
    private UserBean blackUser;
    private PanelBean panelBean;

    public DoublePresenterImpl(DoubleView doubleView){
        mDoubleView = doubleView;
    }

    @Override
    public void restartGame(View v) {
        initData();
        mDoubleView.refreshPanel();
    }
    public void initData(){
        whiteList.clear();
        blackList.clear();
        whiteUser.setPieceList(whiteList);
        whiteUser.setmIsWinner(false);
        whiteUser.setmIsGameOVer(false);
        blackUser.setPieceList(blackList);
        blackUser.setmIsGameOVer(false);
        blackUser.setmIsWinner(false);
    }

    @Override
    public void preparedGame(View v) {
        Log.i("prepare","ing");
        whiteUser.setmIsGameOVer(false);
        blackUser.setmIsGameOVer(false);
        if(panelBean != null){
            initData();
        }
        mDoubleView.refreshPanel();
        mDoubleView.beginGame();
    }

    @Override
    public void withdrawGame(View v) {
        if(whiteList.size() > 0 || blackList.size() > 0) {
            panelBean.setWhite(!panelBean.isWhite());
            if (panelBean.isWhite()) {
                whiteList.remove(whiteList.size() - 1);
                mDoubleView.refreshPanel();
            } else {
                blackList.remove(blackList.size() - 1);
                mDoubleView.refreshPanel();
            }
        }
    }

    @Override
    public void surrenderGame(View v) {
        if(panelBean.isWhite()){
            //black胜
            blackUser.setmIsWinner(true);
            whiteUser.setmIsWinner(false);
            mDoubleView.gameIsOver();
            initData();
        }else{
            //white胜
            blackUser.setmIsWinner(false);
            whiteUser.setmIsWinner(true);
            mDoubleView.gameIsOver();
            initData();
        }
    }

    @Override
    public void initData(UserBean white, UserBean black, PanelBean panel) {
        this.whiteUser = white;
        this.blackUser = black;
        this.panelBean = panel;
        whiteList = new ArrayList<>();
        blackList = new ArrayList<>();
        whiteUser.setPieceList(whiteList);
        whiteUser.setmIsWinner(false);
        whiteUser.setmIsGameOVer(true);
        blackUser.setPieceList(blackList);
        blackUser.setmIsGameOVer(true);
        blackUser.setmIsWinner(false);
        panelBean.setWhite(true);
        panelBean.setMAX(5);
        panelBean.setMAX_LINE(10);
        panelBean.setRatioPieceofLineHeight(3*1.0f/4);
    }

    @Override
    public boolean chess(int x, int y) {
        Point point = getValidPoint(x,y);
        Log.i("x:"+point.x,"y:"+point.y+",mLineHeight:"+panelBean.getmLineHeight());
        if(whiteList.contains(point) || blackList.contains(point)){
            Log.i("contains","point");
            return false;
        }
        if(panelBean.isWhite()){
            whiteList.add(point);
        }else{
            blackList.add(point);
        }
        mDoubleView.chessSuccess();
        panelBean.setWhite(!panelBean.isWhite());
        return true;
    }
//(white.x+(1-panelBean.getRatioPieceofLineHeight())/2)*mLineHeight,
//    (white.y+(1-panelBean.getRatioPieceofLineHeight())/2)*mLineHeight
    @Override
    public void checkIsGameOver() {
        boolean flag=false;
        boolean whiteWin = checkFiveLine(whiteList);
        boolean blackWin = checkFiveLine(blackList);
        if(whiteWin){
            flag=true;
            whiteUser.setmIsWinner(true);
        }else if(blackWin){
            flag=true;
            blackUser.setmIsWinner(true);
        }
        if(flag){
            whiteUser.setmIsGameOVer(true);
            blackUser.setmIsGameOVer(true);
        }
        if(flag) mDoubleView.gameIsOver();

    }
    private boolean checkFiveLine(List<Point> points) {
        for (Point point : points) {
            int x = point.x;
            int y = point.y;
            boolean win = checkHorizontal(x, y, points);
            if(win) return true;
            win = checkVertical(x, y, points);
            if(win) return true;
            win = checkRightCorner(x, y, points);
            if(win) return true;
            win = checkLeftCorner(x, y, points);
            if(win) return true;
        }
        return false;
    }

    private boolean checkLeftCorner(int x, int y, List<Point> points) {
        int count = 1;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x-i,y-i))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x+i,y+i))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        return false;
    }

    private boolean checkRightCorner(int x, int y, List<Point> points) {
        int count = 1;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x-i,y+i))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x+i,y-i))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        return false;
    }

    private boolean checkHorizontal(int x, int y, List<Point> points) {
        int count = 1;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x-i,y))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x+i,y))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        return false;
    }

    private boolean checkVertical(int x, int y, List<Point> points) {
        int count = 1;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x,y-i))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        for(int i=1;i<panelBean.getMAX();i++){
            if(points.contains(new Point(x,y+i))){
                count++;
            }else{
                break;
            }
        }
        if(count == panelBean.getMAX()) return true;
        return false;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int) (x/panelBean.getmLineHeight()), (int) (y/panelBean.getmLineHeight())-2);
    }

}

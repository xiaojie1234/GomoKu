package com.example.xiaojie.gomoku.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.xiaojie.gomoku.R;
import com.example.xiaojie.gomoku.bean.PanelBean;
import com.example.xiaojie.gomoku.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/8.
 */

public class WuZiQiPanel extends View{

    private int mPanelWidth;
    private float mLineHeight;
    private Paint mPaint = new Paint();
    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;
    private List<Point> whiteList;
    private List<Point> blackList;

    public UserBean whiteUser;
    public UserBean blackUser;
    public PanelBean panelBean;


    public WuZiQiPanel(Context context) {
        super(context,null);
    }

    public WuZiQiPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        whiteList = whiteUser.getPieceList();
        blackList = blackUser.getPieceList();
    }

    private void init() {
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mWhitePiece = BitmapFactory.decodeResource(getResources(),R.mipmap.stone_w2);
        mBlackPiece = BitmapFactory.decodeResource(getResources(),R.mipmap.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = Math.min(widthSize,heightSize);
        if(widthMode == MeasureSpec.UNSPECIFIED){
            width = heightSize;
        }else if(heightMode == MeasureSpec.UNSPECIFIED){
            width = widthSize;
        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = mPanelWidth*1.0f / panelBean.getMAX_LINE();
        panelBean.setmPanelWidth(mPanelWidth);
        panelBean.setmLineHeight(mLineHeight);

        int pieceWidth = (int) (mLineHeight*panelBean.getRatioPieceofLineHeight());
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece,pieceWidth,pieceWidth,false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece,pieceWidth,pieceWidth,false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBoard(canvas);
        drawPiece(canvas);
//        checkIsGameOver();
    }


    private void drawPiece(Canvas canvas) {

        if(whiteList == null) return;
        for(int i=0,n=whiteList.size();i<n;i++){
            Point white = whiteList.get(i);
            canvas.drawBitmap(mWhitePiece,(white.x+(1-panelBean.getRatioPieceofLineHeight())/2)*mLineHeight,
                    (white.y+(1-panelBean.getRatioPieceofLineHeight())/2)*mLineHeight,mPaint);
        }
        for(int i=0,n=blackList.size();i<n;i++){
            Point black = blackList.get(i);
            canvas.drawBitmap(mBlackPiece,(black.x+(1-panelBean.getRatioPieceofLineHeight())/2)*mLineHeight,
                    (black.y+(1-panelBean.getRatioPieceofLineHeight())/2)*mLineHeight,mPaint);
        }
    }

    private void drawBoard(Canvas canvas) {
        int width = mPanelWidth;
        float lineHeight = mLineHeight;
        for(int i=0;i<panelBean.getMAX_LINE();i++){
            int startX = (int) (lineHeight/2);
            int startY = (int) ((0.5+i)*lineHeight);
            int endX = (int) (width - lineHeight/2);
            int endY = (int) ((0.5+i)*lineHeight);
            canvas.drawLine(startX,startY,endX,endY,mPaint);
            startX = (int) ((0.5+i)*lineHeight);
            startY = (int) (lineHeight/2);
            endX = (int) ((0.5+i)*lineHeight);
            endY = (int) (width-lineHeight/2);
            canvas.drawLine(startX,startY,endX,endY,mPaint);
        }
    }

    public void setPanelBean(PanelBean panelBean) {
        this.panelBean = panelBean;
    }

    public void setWhiteUser(UserBean whiteUser) {
        this.whiteUser = whiteUser;
    }

    public void setBlackUser(UserBean blackUser) {
        this.blackUser = blackUser;
    }

}

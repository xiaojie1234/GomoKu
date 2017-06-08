package com.example.xiaojie.gomoku;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/8.
 */

public class WuZiQiPanel extends View{

    private int mPanelWidth;
    private float mLineHeight;
    private int MAX_LINE = 10;
    private Paint mPaint = new Paint();
    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;
    private float ratioPieceofLineHeight = 3*1.0f/4;
    private List<Point> whiteList = new ArrayList<>();
    private List<Point> blackList = new ArrayList<>();
    private boolean isWhite=true;

    public WuZiQiPanel(Context context) {
        super(context,null);
    }

    public WuZiQiPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mWhitePiece = BitmapFactory.decodeResource(getResources(),R.mipmap.stone_b1);
        mBlackPiece = BitmapFactory.decodeResource(getResources(),R.mipmap.stone_w2);
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
        mLineHeight = mPanelWidth*1.0f / MAX_LINE;
        int pieceWidth = (int) (mLineHeight*ratioPieceofLineHeight);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece,pieceWidth,pieceWidth,false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece,pieceWidth,pieceWidth,false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPiece(canvas);
    }

    private void drawPiece(Canvas canvas) {
        for(int i=0,n=whiteList.size();i<n;i++){
            Point white = whiteList.get(i);
            canvas.drawBitmap(mWhitePiece,(white.x+(1-ratioPieceofLineHeight)/2)*mLineHeight,
                    (white.y+(1-ratioPieceofLineHeight)/2)*mLineHeight,mPaint);
        }
        for(int i=0,n=blackList.size();i<n;i++){
            Point black = blackList.get(i);
            canvas.drawBitmap(mBlackPiece,(black.x+(1-ratioPieceofLineHeight)/2)*mLineHeight,
                    (black.y+(1-ratioPieceofLineHeight)/2)*mLineHeight,mPaint);
        }
    }

    private void drawBoard(Canvas canvas) {
        int width = mPanelWidth;
        float lineHeight = mLineHeight;
        for(int i=0;i<MAX_LINE;i++){
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_UP){
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point point = getValidPoint(x,y);
            if(whiteList.contains(point) || blackList.contains(point)){
                return false;
            }
            if(isWhite){
                    whiteList.add(point);
            }else{
                    blackList.add(point);
            }
            invalidate();
            isWhite = !isWhite;

        }
        return true;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int) (x/mLineHeight), (int) (y/mLineHeight));
    }
}

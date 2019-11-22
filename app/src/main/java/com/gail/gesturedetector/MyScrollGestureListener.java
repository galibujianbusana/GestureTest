package com.gail.gesturedetector;

import android.graphics.RectF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName MyScrollGestureListener
 * @Description TODO
 * @Author guoxw
 * @Date 2019/11/21 16:03
 */
public class MyScrollGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final String TAG = "MyScrollGestureListener";

    private ViewGroup viewGroup;
    private View targetView;

    /**
     * translation 的偏移量
     */
    private float distanceXTemp = 0;
    private float distanceYTemp = 0;

    /**
     * targetView 初始时的大小
     */
    private float viewWidthNormal;
    private float viewHeightNormal;

    /**
     * targetView 缩放后的大小
     */
    private float viewWidthReal;
    private float viewHeightReal;



    boolean first = true;

    public MyScrollGestureListener(View targetView, ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.targetView = targetView;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        if(first){
            first = false;
            viewWidthNormal = targetView.getWidth();
            viewHeightNormal = targetView.getHeight();
            viewWidthReal = viewWidthNormal;
            viewHeightReal = viewHeightNormal;
        }

        Log.d(TAG, "onDown:  - -> " + viewWidthNormal);
        return true;
    }


    public void setScale(float scale) {

        viewWidthReal = viewWidthNormal * scale;

        viewHeightReal = viewHeightNormal * scale;

        Log.d(TAG, "setScale:  - -> " + viewWidthReal);


    }



    @Override
    public void onShowPress(MotionEvent e) {

    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        Log.d(TAG, "onScroll: " + targetView.getX() + "- - - " + targetView.getY());

        translationXOnScrollEvent(-distanceX);
        translationYOnScrollEvent(-distanceY);
        return true;
        // return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        float left1 = targetView.getX() - (viewWidthReal - viewWidthNormal) /2;
        float top1 = targetView.getTop() - (viewHeightReal - viewHeightNormal) /2;
        float right1 = left1 + viewWidthReal;
        float bottom1 = top1 + viewHeightReal;

        Log.d(TAG, "onSingleTapUp:  - -> " + left1 + " : " + top1 +" : " + right1 +" : " + bottom1);

        RectF rectF = new RectF(left1, top1, right1, bottom1);
        if (rectF.contains(e.getX(), e.getY())) {
            targetView.performClick();
        }
        return super.onSingleTapUp(e);
    }

    private void translationXOnScrollEvent(float distanceX) {

        distanceXTemp = distanceXTemp + distanceX;

        targetView.setTranslationX(distanceXTemp);


    }

    private void translationYOnScrollEvent(float distanceY) {
        distanceYTemp = distanceYTemp + distanceY;

        targetView.setTranslationY(distanceYTemp);
    }



}

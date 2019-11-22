package com.gail.gesturedetector;

import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;


public class MyScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {


    private static final String TAG = "MyScaleGestureListener";

    private View targetView;
    private ViewGroup viewGroup;
    float scale = 1;
    float scaleTemp = 1;




    public MyScaleGestureListener(View targetView, ViewGroup viewGroup) {
        this.targetView = targetView;
        this.viewGroup = viewGroup;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.d(TAG, "onScale:  - - -");
        scale = scaleTemp * detector.getScaleFactor();
        targetView.setScaleX(scale);
        targetView.setScaleY(scale);
        return false;

    }

    public float getScale() {
       return  scale;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        Log.d(TAG, "onScaleBegin:  - - -");
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        Log.d(TAG, "onScaleEnd:  - - -");
        scaleTemp = scale;

    }
}

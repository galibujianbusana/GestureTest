package com.gail.gesturedetector.gesture.destureviewbinder;

import android.content.Context;
import android.view.GestureDetector;

/**
 * Created by JarvisLau on 2018/5/30.
 * Description :
 */

class ScrollGestureBinder extends GestureDetector {

    ScrollGestureBinder(Context context, ScrollGestureListener scrollGestureListener) {
        super(context, scrollGestureListener);
    }

}
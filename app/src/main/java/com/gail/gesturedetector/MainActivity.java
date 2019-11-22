package com.gail.gesturedetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gail.gesturedetector.gesture.destureviewbinder.GestureViewBinder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TestView - - >";

    RelativeLayout relayout;

    ImageView tv;

    GestureDetector gestureDetector;

    ScaleGestureDetector scaleGestureDetector;

    private boolean isScaleEnd = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relayout = findViewById(R.id.relayout);
        tv = findViewById(R.id.tv);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "onclick!", Toast.LENGTH_SHORT).show();
            }
        });

        tv.setClickable(false);

        final MyScrollGestureListener myScrollGestureListener = new MyScrollGestureListener(tv, relayout);
        gestureDetector = new GestureDetector(this, myScrollGestureListener);

        final MyScaleGestureListener myScaleGestureListener = new MyScaleGestureListener(tv, relayout);
        scaleGestureDetector = new ScaleGestureDetector(this, myScaleGestureListener);

        relayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "relayout:  setOnTouchListener 触发这里");
                if (event.getPointerCount() == 1) {
                    return gestureDetector.onTouchEvent(event);
                } else {
                    myScrollGestureListener.setScale(myScaleGestureListener.getScale());
                    Log.d(TAG, "onTouch:  scaleGestureDetector 触发这里");
                    return scaleGestureDetector.onTouchEvent(event);
                }


            }

        });


       // GestureViewBinder.bind(this, relayout, tv);


    }
}
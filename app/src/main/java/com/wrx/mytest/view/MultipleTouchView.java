package com.wrx.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/5/5.
 */

public class MultipleTouchView extends View{

    public MultipleTouchView(Context context) {
        super(context);
    }

    public MultipleTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();
        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                Log.e("ACTION", "down" +
                        ",pointIndex" + pointerIndex +
                        ",pointId" + pointerId);
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    pointerIndex = i;
                    pointerId = event.getPointerId(pointerIndex);
                    Log.e("ACTION", "move" +
                            ",pointIndex" + pointerIndex +
                            ",pointId" + pointerId +
                            ",X " + event.getX(pointerIndex) +
                            ",Y " + event.getY(pointerIndex)
                    );
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.e("ACTION", "down multiple" +
                        ",pointIndex" + pointerIndex +
                        ",pointId" + pointerId);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.e("ACTION", "up multiple" +
                        ",pointIndex" + pointerIndex +
                        ",pointId" + pointerId);;
                break;
            case MotionEvent.ACTION_UP:
                // Suppose the NO.1 point down in the first, NO.2 point down in the second
                // in this time, NO.1 point is index 0, NO.2 point is index 1,
                // if NO.2 point up in the first, the Action Index is 1, NO.1 point up in the second, the Action Index is 0,
                // if NO.1 point up in the first, the Action Index is 0, NO.2 point up in the second, the Action Index is 0,
                // conclusion: the pointerIndex change some time, but the pointerId never change
                Log.e("ACTION", "up" +
                        ",pointIndex" + pointerIndex +
                        ",pointId" + pointerId);
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.e("ACTION", "out" +
                        ",pointIndex" + pointerIndex +
                        ",pointId" + pointerId);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("ACTION", "cancel" +
                        ",pointIndex" + pointerIndex +
                        ",pointId" + pointerId);
                break;
        }
        return true;
    }
}

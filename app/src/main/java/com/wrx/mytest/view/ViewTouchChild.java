package com.wrx.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ViewTouchChild extends LinearLayout {

    public ViewTouchChild(Context context) {
        super(context);
    }

    public ViewTouchChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTouchChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchTest", "Child" + "/onTouchEvent/" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchTest", "Child" + "/onTouchEvent/" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("TouchTest", "Child" + "/onTouchEvent/" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TouchTest", "Child" + "/onTouchEvent/" + "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchTest", "Child" + "/onInterceptTouchEvent/" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchTest", "Child" + "/onInterceptTouchEvent/" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("TouchTest", "Child" + "/onInterceptTouchEvent/" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TouchTest", "Child" + "/onInterceptTouchEvent/" + "ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchTest", "Child" + "/dispatchTouchEvent/" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchTest", "Child" + "/dispatchTouchEvent/" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("TouchTest", "Child" + "/dispatchTouchEvent/" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TouchTest", "Child" + "/dispatchTouchEvent/" + "ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}

package com.wrx.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ViewTouchParent extends LinearLayout{

    public ViewTouchParent(Context context) {
        super(context);
    }

    public ViewTouchParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTouchParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchTest", "Parent" + "/onTouchEvent/" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchTest", "Parent" + "/onTouchEvent/" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("TouchTest", "Parent" + "/onTouchEvent/" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TouchTest", "Parent" + "/onTouchEvent/" + "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchTest", "Parent" + "/onInterceptTouchEvent/" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchTest", "Parent" + "/onInterceptTouchEvent/" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("TouchTest", "Parent" + "/onInterceptTouchEvent/" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TouchTest", "Parent" + "/onInterceptTouchEvent/" + "ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TouchTest", "Parent" + "/dispatchTouchEvent/" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TouchTest", "Parent" + "/dispatchTouchEvent/" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("TouchTest", "Parent" + "/dispatchTouchEvent/" + "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TouchTest", "Parent" + "/dispatchTouchEvent/" + "ACTION_UP");
                break;
        }
        //return super.dispatchTouchEvent(ev);
        //return super.dispatchTouchEvent(ev);
        return true;
    }

}

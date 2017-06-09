package com.wrx.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/5/5.
 */

public class TestTouchView extends View{

    public TestTouchView(Context context) {
        super(context);
    }

    public TestTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}

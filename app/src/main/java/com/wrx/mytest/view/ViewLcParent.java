package com.wrx.mytest.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.wrx.mytest.R;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ViewLcParent extends LinearLayout{

    public ViewLcParent(Context context) {
        super(context);
    }

    public ViewLcParent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewLcParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}

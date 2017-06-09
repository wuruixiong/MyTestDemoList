package com.wrx.mytest.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wu_ru on 2017/5/30.
 */

public class MyGLSurfaceView extends GLSurfaceView{

    private MyGLRenderer myGLRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init () {
        // init open GL
        setEGLContextClientVersion(2);
        // create GLRenderer
        myGLRenderer = new MyGLRenderer();
        setRenderer(myGLRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}

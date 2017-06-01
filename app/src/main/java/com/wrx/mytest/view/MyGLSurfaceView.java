package com.wrx.mytest.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

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
        setEGLContextClientVersion(2);
        myGLRenderer = new MyGLRenderer();
        setRenderer(myGLRenderer);
    }

}

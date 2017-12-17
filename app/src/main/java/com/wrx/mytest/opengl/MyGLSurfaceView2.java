package com.wrx.mytest.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wu_ru on 2017/5/30.
 */

public class MyGLSurfaceView2 extends GLSurfaceView{

    private MyMoveGLRenderer mRenderer;

    public MyGLSurfaceView2(Context context) {
        super(context);
        init();
    }

    public MyGLSurfaceView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init () {
        // init open GL
        setEGLContextClientVersion(2);
        // create GLRenderer
        mRenderer = new MyMoveGLRenderer(getContext());
        setRenderer(mRenderer);
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // updateRotate method is get the real degrees
                float degrees = updateRotate(mPreviousX, mPreviousY, x, y, getWidth()/2, getHeight()/2);
                // but you must set  phase negative degrees to setAngle method
                mRenderer.setAngle(mRenderer.getAngle() + (-degrees));
                requestRender();
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    public float updateRotate (float oldX, float oldY, float newX, float newY, float centerX, float centerY) {
        // Two straight lines for the angle
        // The two-point coordinates of line1 are (oldX, oldY) and (centerX, centerY)
        // The two-point coordinates of line2 are (newX, newY) and (centerX, centerY)
        // Straight line1 slope  k1 = （mHelpToolBoxRect.centerY() - oldY ）/（mHelpToolBoxRect.centerX() - oldX ）
        // Straight line2 slope  k2 = （mHelpToolBoxRect.centerY() - newY ）/（mHelpToolBoxRect.centerX() - newX ）
        // included angle  θ: tan θ = | (k2 - k1) / (1 + k1 * k2) |

        // Angle and radian conversion, 1 radian = 180 / π degrees, 1 degree = π / 180 radians

        // about Java method:
        // Math.PI : PI
        // Math.abs : absolute value
        // Math.toDegrees : The input parameter is radianL, and the return value is the angle
        // Math.atan : The input parameter is the tangent value tan θ, and the return value is radian
        // Math.tan : The input parameter is radian and the return value is tangent

        float k1 = (centerY - oldY) / (centerX - oldX);
        float k2 = (centerY - newY) / (centerX - newX);
        float realTanValue = (k2 - k1) / ( 1 + k2 * k1);
        float absTanValue = Math.abs( realTanValue );
        double degrees = Math.toDegrees(Math.atan(absTanValue));

        // To determine the clockwise rotation, or counterclockwise rotation
        float degreesF = 0;
        if (realTanValue >= 0) {
            degreesF = (float)degrees;
        } else if (realTanValue < 0) {
            degreesF = -(float)degrees;
        }
        return degreesF;
    }
}

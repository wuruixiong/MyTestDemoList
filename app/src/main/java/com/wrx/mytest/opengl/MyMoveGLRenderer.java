package com.wrx.mytest.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;

import com.wrx.mytest.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wu_ru on 2017/5/31.
 */

public class MyMoveGLRenderer implements GLSurfaceView.Renderer{

    private final float[] mProjectionMatrix = new float[16];

    private Triangle mTriangle;
    public volatile float mAngle;

    private Context mContext;

    public MyMoveGLRenderer(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mTriangle = new Triangle();
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Redraw background color
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        float[] scratch = new float[16];
        float[] scratch2 = new float[16];
        float[] viewMatrix = new float[16];
        float[] MVPMatrix = new float[16];
        float[] rotationMatrix = new float[16];
        float[] translateMatrix = new float[16];

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(MVPMatrix, 0, mProjectionMatrix, 0, viewMatrix, 0);

        Matrix.setRotateM(rotationMatrix, 0, mAngle, 0, 0, -1.0f);
        // result相乘的结果，两个矩阵相乘，lhs参数是左矩阵，rhs参数是右矩阵， result = lhs x rhs
        Matrix.multiplyMM(scratch, 0, MVPMatrix, 0, rotationMatrix, 0);

        /*Matrix.translateM(translateMatrix, 0, 0, 2, 0f);
        Matrix.multiplyMM(scratch2, 0, MVPMatrix, 0, translateMatrix, 0);*/

        // Draw triangle
        mTriangle.draw(scratch);

    }



}

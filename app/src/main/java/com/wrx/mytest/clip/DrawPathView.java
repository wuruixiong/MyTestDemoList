package com.wrx.mytest.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wrx.mytest.R;

import java.util.Arrays;

/**
 * Created by zhuanfa on 2017/9/19.
 */

public class DrawPathView extends View{

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private Paint circlePaint;
    private Path circlePath;

    private Paint mPaint;

    private Bitmap mOriginBp;

    private Path mTestPath;
    private Paint mTestPaint;

    public DrawPathView(Context context) {
        super(context);
        init();
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xfff0f0f0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        //mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        circlePaint = new Paint();
        circlePath = new Path();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(0x88f0f0f0);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(5f);

        mOriginBp = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.timg);
        mTestPaint = new Paint();
        mTestPaint.setShader(new BitmapShader(mOriginBp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

        setLayerType(LAYER_TYPE_HARDWARE, new Paint());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        mTestPath = new Path();
        for (int i = 100; i < 300; i++) {
            mTestPath.moveTo(i, 100);
            mTestPath.lineTo(i, 300);
        }
        mTestPath.close();

        mLeft = w;
        mTop = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!drawClip) {
            canvas.drawBitmap(mOriginBp, 0, 0, mBitmapPaint);
            if (mPath != null) {
                canvas.drawPath( mPath,  mPaint);
            }
            canvas.drawPath( circlePath,  circlePaint);
        } else {

            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
            p.setColor(0xffffffff);
            canvas.drawPath(mPath, p);

            Paint mMaskPaint = new Paint();
            mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(mOriginBp, 0, 0, mMaskPaint);
        }
    }

    private float mX, mY;
    private float mLeft, mTop;
    private float mStartX, mStartY;
    private static final float TOUCH_TOLERANCE = 4;

    /*private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

            circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        circlePath.reset();
        // commit the path to our offscreen
        mCanvas.drawPath(mPath,  mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }*/

    private boolean isFirstDraw = true;
    private void touch_start(float x, float y) {
        if (mPath == null) {
            mStartX = x;
            mStartY = y;

            mPath = new Path();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;

            isFirstDraw = true;
        } else {
            /*mStartX = x;
            mStartY = y;
            mPath.moveTo(x, y);
            mX = x;
            mY = y;*/

            isFirstDraw = false;
        }
    }

    private void touch_move(float x, float y) {
        if (isFirstDraw) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;
                circlePath.reset();
                circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
            }
        }
    }

    private void touch_up(float x, float y) {
        if (isFirstDraw) {
            //mPath.close();

            mPath.lineTo(mStartX, mStartY);
            circlePath.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (mLeft > x) {
            mLeft = x;
        }
        if (mTop > y) {
            mTop = y;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return isFirstDraw;
    }

    private boolean drawClip;
    public void setClip() {
        drawClip = true;
        invalidate();
    }

    public void resetClip () {
        if (mPath != null) {
            mPath.reset();
            mPath = null;
        }
        drawClip = false;
        invalidate();
    }

    public Bitmap getClipBitmap () {
        if (mPath != null) {
            Bitmap resultBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            canvas.setBitmap(resultBitmap);

            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
            p.setColor(0xffffffff);
            canvas.drawPath(mPath, p);

            Paint mMaskPaint = new Paint();
            mMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(mOriginBp, 0, 0, mMaskPaint);

            Bitmap cutBitmap = crop(resultBitmap);
            resultBitmap.recycle();

            return cutBitmap;
        }
        return null;
    }

    // cut Transparent edge
    public Bitmap crop (Bitmap bitmap){

        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int[] empty = new int[width];
        int[] buffer = new int[width];
        Arrays.fill(empty,0);
        int top = 0;
        int left = 0;
        int botton = height;
        int right = width;

        for (int y = 0; y < height; y++) {
            bitmap.getPixels(buffer, 0, width, 0, y, width, 1);
            if (!Arrays.equals(empty, buffer)) {
                top = y;
                break;
            }
        }

        for (int y = height - 1; y > top; y--) {
            bitmap.getPixels(buffer, 0, width, 0, y, width, 1);
            if (!Arrays.equals(empty, buffer)) {
                botton = y;
                break;
            }
        }

        int bufferSize = botton -top +1;
        empty = new int[bufferSize];
        buffer = new int[bufferSize];
        Arrays.fill(empty,0);

        for (int x = 0; x < width; x++) {
            bitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize);
            if (!Arrays.equals(empty, buffer)) {
                left = x;
                break;
            }
        }

        for (int x = width - 1; x > left; x--) {
            bitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize);
            if (!Arrays.equals(empty, buffer)) {
                right = x;
                break;
            }
        }

        Bitmap cropedBitmap = Bitmap.createBitmap(bitmap, left, top, right-left, botton-top);
        return cropedBitmap;
    }



}

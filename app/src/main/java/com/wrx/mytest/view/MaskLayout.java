package com.wrx.mytest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.wrx.mytest.R;

/**
 * Created by zhuanfa on 2017/7/11.
 */

public class MaskLayout extends RelativeLayout{

    private Bitmap mBitmap;
    private Bitmap mBgBitmap;
    private Paint mPaint;
    private Paint mEmptyPaint;

    public MaskLayout(Context context) {
        super(context);
        init();
    }

    public MaskLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init () {


        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.mask);
        mBgBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.bg);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mEmptyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        setLayerType(LAYER_TYPE_HARDWARE, mEmptyPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        if (mBgBitmap != null) {
            Matrix matrix = new Matrix();
            float scaleW = (float) (getWidth()) / mBgBitmap.getWidth();
            float scaleH = (float) (getHeight()) / mBgBitmap.getHeight();
            matrix.setScale(scaleW, scaleH);
            canvas.drawBitmap(mBgBitmap, matrix, mEmptyPaint);
        }

        if (mBitmap != null) {
            Matrix matrix = new Matrix();
            float scaleW = (float) (getWidth()) / mBitmap.getWidth();
            float scaleH = (float) (getHeight()) / mBitmap.getHeight();
            matrix.setScale(scaleW, scaleH);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(mBitmap, matrix, mPaint);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mBitmap != null) {
            Matrix matrix = new Matrix();
            float scaleW = (float) (getWidth()) / mBitmap.getWidth();
            float scaleH = (float) (getHeight()) / mBitmap.getHeight();
            matrix.setScale(scaleW, scaleH);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(mBitmap, matrix, mPaint);
        }
    }
}

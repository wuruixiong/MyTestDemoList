package com.wrx.mytest.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhuanfa on 2017/9/20.
 */

public class RepeatView extends View{

    private Bitmap mRepeatBitmap;
    private Paint mRepeatPaint;

    private float mItemWHRatio;

    private float mItemW = 160;
    private float mItemH;
    private float mItemSpaceX = 30;
    private float mItemSpaceY;

    private int mXItemCount, mYItemCount;

    public RepeatView(Context context) {
        super(context);
        init();
    }

    public RepeatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RepeatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRepeatPaint = new Paint();
    }

    public void setRepeatBitmap (Bitmap bitmap) {
        mRepeatBitmap = bitmap;
        mItemWHRatio = (float) mRepeatBitmap.getWidth()/mRepeatBitmap.getHeight();
        mItemH = mItemW / mItemWHRatio;
        mItemSpaceY = mItemSpaceX / mItemWHRatio;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRepeatBitmap != null) {
            mXItemCount = 4;
            mYItemCount = 7;

            /*mItemW = getWidth()/mXItemCount;
            mItemH = getHeight()/mYItemCount;*/

            int centerX = getWidth()/2;
            int centerY = getHeight()/2;
            RectF centerRectF = new RectF(centerX - mItemW/2, centerY - mItemH/2,
            centerX + mItemW/2, centerY + mItemH/2);

            //canvas.drawBitmap(mRepeatBitmap, null, centerRectF, mRepeatPaint);

            /*int i = 0;
            while (centerRectF.left - (mItemSpaceX + mItemW) * i > 0) {
                i++;
                float left = (centerRectF.left - (mItemSpaceX + mItemW) * i);
                RectF rectF = new RectF(left, centerRectF.top, left + mItemW, centerRectF.bottom);
                canvas.drawBitmap(mRepeatBitmap, null, rectF, mRepeatPaint);
            }
            i = 0;
            while (centerRectF.right + (mItemSpaceX + mItemW) * i < getWidth()) {
                i++;
                float right = (centerRectF.right + (mItemSpaceX + mItemW) * i);
                RectF rectF = new RectF(right - mItemW, centerRectF.top, right, centerRectF.bottom);
                canvas.drawBitmap(mRepeatBitmap, null, rectF, mRepeatPaint);
            }*/

            for (int i = 0; i < mXItemCount; i++) {
                RectF rectCon = new RectF(0, 0, mItemW, mItemH);
                rectCon.offset(mItemW * i, 0);
                for (int j = 0; j < mYItemCount; j++) {
                    RectF rectF = new RectF(rectCon.left + mItemSpaceX, rectCon.top + mItemSpaceY,
                            rectCon.right - mItemSpaceX, rectCon.bottom - mItemSpaceY);
                    canvas.drawBitmap(mRepeatBitmap, null, rectF, mRepeatPaint);
                    rectCon.offset(0, mItemH);
                }
            }
        }
    }


}

package com.wrx.mytest.clip;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wrx.mytest.R;

/**
 * Created by Administrator on 2017/5/5.
 */

public class PathActivity extends Activity{

    private View mBtOk;
    private View mBtCancel;
    private View mBtEraser;
    private DrawPathView mDrawPathView;
    private RepeatView mImageView;
    private EraserView mEraserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        mBtOk = findViewById(R.id.ok);
        mBtCancel = findViewById(R.id.cancel);
        mBtEraser = findViewById(R.id.eraser);
        mImageView = (RepeatView) findViewById(R.id.preview_view);
        mDrawPathView = (DrawPathView) findViewById(R.id.path_view);
        mEraserView = (EraserView) findViewById(R.id.eraser_view);

        mBtEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawPathView.setVisibility(View.GONE);
                mEraserView.setVisibility(View.VISIBLE);
                Bitmap bitmap = mDrawPathView.getClipBitmap();
                mEraserView.setBitmap(bitmap);
            }
        });
        mBtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawPathView.setVisibility(View.GONE);
                mImageView.setVisibility(View.VISIBLE);
                Bitmap bitmap = mDrawPathView.getClipBitmap();
                mImageView.setRepeatBitmap(bitmap);
                //mDrawPathView.setClip();
            }
        });
        mBtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawPathView.setVisibility(View.VISIBLE);
                mEraserView.setVisibility(View.GONE);
                mImageView.setVisibility(View.GONE);
                mDrawPathView.resetClip();
            }
        });
    }


}

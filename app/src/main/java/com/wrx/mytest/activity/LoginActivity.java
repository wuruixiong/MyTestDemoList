package com.wrx.mytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.wrx.mytest.R;

/**
 * Created by Administrator on 2017/5/5.
 */

public class LoginActivity extends Activity{

    private View mIconView;
    private View mTextView;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mIconView = findViewById(R.id.image_icon);
        mTextView = findViewById(R.id.text);
        mRootView = findViewById(R.id.container);

        mRootView.addOnLayoutChangeListener(mListener);
    }

    public View.OnLayoutChangeListener mListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (oldBottom != 0) {
                if ((oldBottom - bottom) > 200) {
                    mIconView.setVisibility(View.GONE);
                    mTextView.setVisibility(View.GONE);
                } else if ((oldBottom - bottom) < -200) {
                    mIconView.setVisibility(View.VISIBLE);
                    mTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    };

}

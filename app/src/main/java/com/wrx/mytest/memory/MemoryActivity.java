package com.wrx.mytest.memory;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wrx.mytest.R;

import com.wrx.mytest.opengl.*;

import java.lang.reflect.Array;

/**
 * Created by wuruixiong on 2017/10/3.
 */

public class MemoryActivity extends Activity implements View.OnClickListener{

    private Button mButton;
    private Bitmap[] mDrawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_anal);
        mButton = (Button) findViewById(R.id.set_mem_over_bt);
        mButton.setOnClickListener(this);
        mDrawables = new Bitmap[10];
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_mem_over_bt:
                Resources res = getResources();
                for (int i = 0; i < mDrawables.length; i++) {
                    mDrawables[i] = BitmapFactory.decodeResource(res, R.mipmap.bg);
                }
                SaveAppContext.setsContext(MemoryActivity.this);
                break;
        }
    }

}

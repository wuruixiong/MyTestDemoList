package com.wrx.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.wrx.mytest.R;

/**
 * Created by zhuanfa on 2017/6/9.
 */

public class TouchTestActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        Button button1 = (Button) findViewById(R.id.bt1);
        Button button2 = (Button) findViewById(R.id.bt2);
        //View fillView = findViewById(R.id.fill_view);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("", "");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("", "");
            }
        });
    }



}

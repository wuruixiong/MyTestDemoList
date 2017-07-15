package com.wrx.mytest;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Administrator on 2017/5/5.
 */

public class TestActivity extends Activity{

    private Button mButton;
    private EditText mEditText;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mEditText = (EditText) findViewById(R.id.test_edit);
        mButton = (Button) findViewById(R.id.test_bt);
        mImageView = (ImageView) findViewById(R.id.test_image);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}

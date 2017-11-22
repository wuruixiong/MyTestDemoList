package com.wrx.mytest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wrx.mytest.R;

/**
 * Created by wuruixiong on 2017/7/25.
 */

public class TextBeMaskActivity extends Activity implements View.OnClickListener{

    Button mButton;
    public static TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_be_mask);
        mButton = (Button) findViewById(R.id.my_bt);
        mButton.setOnClickListener(this);

        mTextView = (TextView)findViewById(R.id.my_text);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), TextMaskActivity.class);
        startActivity(intent);
    }
}

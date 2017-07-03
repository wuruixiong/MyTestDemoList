package com.wrx.mytest.textIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wrx.mytest.R;

/**
 * Created by zhuanfa on 2017/6/21.
 */

public class IntentTestActivity extends Activity{

    TextView mTextView;
    Button mButton;
    int mTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
        mTextView = (TextView) findViewById(R.id.intent_text);
        mButton = (Button) findViewById(R.id.intent_bt);
        mTextView.setText("IntentTestActivity:" + mTime);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime = mTime + 1;
                mTextView.setText("IntentTestActivity:" + mTime);

                Intent intent = new Intent(getApplicationContext(), IntentTestActivity2.class);
                startActivity(intent);
            }
        });
    }



}

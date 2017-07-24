package com.wrx.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

/**
 * Created by wuruixiong on 2017/7/25.
 */

public class TextMaskActivity extends Activity{

    View mBlueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_mask);
        mBlueView = findViewById(R.id.mask_blue_view);
        mBlueView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int i = random.nextInt(9);
                TextBeMaskActivity.mTextView.setText("");
                TextBeMaskActivity.mTextView.append(i + "");
                TextBeMaskActivity.mTextView.append(i + "");
                TextBeMaskActivity.mTextView.append(i + "");
            }
        });
    }
}

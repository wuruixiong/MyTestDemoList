package com.wrx.mytest;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wrx.mytest.view.ViewLcChild;
import com.wrx.mytest.view.ViewLcParent;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ViewLifecycleActivity extends Activity{

    Button mButton;
    ViewLcParent mViewLcParent;
    ViewLcChild mViewLcChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lc);
        mViewLcParent = (ViewLcParent) findViewById(R.id.lc_parent);
        mViewLcChild = (ViewLcChild) findViewById(R.id.lc_child);

        mButton = (Button) findViewById(R.id.lc_bt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewLcChild viewLcChild = new ViewLcChild(ViewLifecycleActivity.this);
                viewLcChild.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
                mViewLcParent.addView(viewLcChild);
            }
        });

    }

}

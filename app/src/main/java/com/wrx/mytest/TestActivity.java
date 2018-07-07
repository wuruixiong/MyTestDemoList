package com.wrx.mytest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/5/5.
 */

public class TestActivity extends Activity{

    private TextView mTextView1;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        mTextView1 = (TextView) findViewById(R.id.text1);
        mTextView2 = (TextView) findViewById(R.id.text2);

        /*new Count1().execute();
        new Count2().execute();*/

    }


    class Count1 extends AsyncTask<Void, String, Void>{
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            mTextView1.setText(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 20 ; i++) {
                try {
                    Thread.sleep(1000);
                }catch (Exception e) {
                }
                publishProgress("count:" + i);
            }
            return null;
        }
    }

    class Count2 extends AsyncTask<Void, String, Void>{
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            mTextView2.setText(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 20 ; i++) {
                try {
                    Thread.sleep(1000);
                }catch (Exception e) {
                }
                publishProgress("count:" + i);
            }
            return null;
        }
    }


}

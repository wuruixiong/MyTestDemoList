package com.wrx.mytest.http;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wrx.mytest.R;

import java.io.IOException;
import java.lang.ref.SoftReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhuanfa on 2017/8/16.
 */

public class OkHttpActivity extends Activity{

    private String mUrl1 = "";

    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        mButton = (Button) findViewById(R.id.http_bt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncGet();
            }
        });

        SoftReference<Button> softRef=new SoftReference<Button>(mButton);
        SoftReference sr = new SoftReference(mButton);

    }

    private void syncGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(mUrl1)
                            .build();
                    Response response = client.newCall(request).execute();
                    String s = response.body().string();
                    Log.e("", "");
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void asyncTaskGet () {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(mUrl1)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Log.e("", "");
            }
        });
    }

}

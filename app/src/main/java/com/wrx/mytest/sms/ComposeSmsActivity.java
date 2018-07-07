package com.wrx.mytest.sms;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wrx.mytest.R;

/**
 * Created by wuruixiong on 2018/2/23.
 */

public class ComposeSmsActivity extends Activity{

    private String defaultSmsApp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compost_sms);

        defaultSmsApp = Telephony.Sms.getDefaultSmsPackage(this);

        final String myPackageName = getPackageName();
        if (!defaultSmsApp.equals(myPackageName)) {
            // Set up a button that allows the user to change the default SMS app
            Button button = (Button) findViewById(R.id.change_default_app);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent =
                            new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                            myPackageName);
                    startActivity(intent);
                }
            });
        }

        findViewById(R.id.send_sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("address", "123456789");
                values.put("body", "Sent a sms");
                getContentResolver().insert(Uri.parse("content://sms/sent"), values);
            }
        });
        findViewById(R.id.receive_sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("address", "123456789");
                values.put("body", "Received a sms");
                getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*//检测权限是否已经拥有
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // 判断用户是否 选过禁止该权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

                // 如果用户曾经选择了不授予权限，弹出提示，提示该权限是必要的，并且重新弹出一个权限申请
                Toast.makeText(getApplicationContext(), "please agree", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS,
                                Manifest.permission.SEND_SMS},
                        1011);

            } else {

                // 申请权限，一般第一次申请会走这里
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        1011);
            }
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsApp);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1011: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "permission ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "permission cancel", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }*/

}

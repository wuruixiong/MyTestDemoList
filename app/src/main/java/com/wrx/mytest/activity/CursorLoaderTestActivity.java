package com.wrx.mytest.activity;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.wrx.mytest.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class CursorLoaderTestActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                }
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1001);
            }
        }

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        if (id == LOADER_ID) {
            //  1 column is the image path,  2 column is the image name, 3 column is the image size
            String[] projection = new String[]{MediaStore.Images.Media.DATA,
                                               MediaStore.Images.Media.DISPLAY_NAME,
                                               MediaStore.Images.Media.SIZE};
            return new CursorLoader(this,
                    //The content:// style URI for the "primary" external storage volume.
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    // Get what kind of data's list
                    projection,
                    // data filter,  alike as  SQL WHERE
                    null,
                    // data filter parameter
                    null,
                    // data Sort
                    null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {

            ArrayList<String> pathList = new ArrayList<>();
            cursor.moveToFirst();
            do {
                // 1 column is the image path,  2 column is the image name
                String fileLocation = cursor.getString(0);
                String fileName = cursor.getString(1);
                String size = cursor.getString(2);
                pathList.add(fileLocation);
                /*  File imageFile = new File(fileLocation);
                Bitmap bitmap = BitmapFactory.decodeFile(fileLocation);*/
            } while (cursor.moveToNext());
            Log.e("", "");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

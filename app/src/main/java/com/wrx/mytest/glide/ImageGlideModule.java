package com.wrx.mytest.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.wrx.mytest.R;

/**
 * Created by zhuanfa on 2017/8/16.
 */

public class ImageGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        String filePathStr = null;
        String dirName = "GlideImageCache";
        boolean existSDCard = android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (existSDCard) {
            filePathStr = context.getExternalCacheDir() + "/" + dirName;
        } else {
            filePathStr = context.getCacheDir() + "/" + dirName;
        }
        int size = 1024 * 1024 * 10;
        builder.setDiskCache(new DiskLruCacheFactory(filePathStr, size));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}

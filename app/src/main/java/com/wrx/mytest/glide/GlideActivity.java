package com.wrx.mytest.glide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wrx.mytest.R;

/**
 * Created by Administrator on 2017/5/5.
 */

public class GlideActivity extends Activity{

    final String imageUri = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502788284541&di=0025b9c63a13a446d4fed109fd504352&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D3575306531%2C4004779095%26fm%3D214%26gp%3D0.jpg";
    final String imageUri2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502788224762&di=be929bd068c65af0c7b25aecebc84ac5&imgtype=0&src=http%3A%2F%2Fpx.thea.cn%2FPublic%2FUpload%2F2467637%2FIntro%2F1426853144.jpg";
    final String imageUri3 = "http://img1.imgtn.bdimg.com/it/u=519302865,2920057036&fm=11&gp=0.jpg";
    final String imageUri4 = "http://img5.imgtn.bdimg.com/it/u=3015658538,89224587&fm=11&gp=0.jpg";

    ImageView mSImageView;
    ImageView mSImageView1;
    ImageView mSImageView2;
    ImageView mSImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mSImageView = (ImageView) findViewById(R.id.glide_sample_image0);
        mSImageView1 = (ImageView) findViewById(R.id.glide_sample_image1);
        mSImageView2 = (ImageView) findViewById(R.id.glide_sample_image2);
        mSImageView3 = (ImageView) findViewById(R.id.glide_sample_image3);

        Glide.with(getApplicationContext())
                .load(imageUri4)
                .placeholder(R.mipmap.loading)
                // if set NONE, it will not cache in disk
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mSImageView);

        Glide.with(getApplicationContext())
                .load(imageUri)
                .placeholder(R.mipmap.loading)
                // cache the source image
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mSImageView1);

        new LoadBitmap().execute();

        Glide.with(getApplicationContext()).load(imageUri3).asBitmap()
                .into(new SimpleTarget<Bitmap>(2000, 2000) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mSImageView3.setImageBitmap(resource);
                    }
                });
       /* DrawableTypeRequest drawableTypeRequest = Glide.with(getApplicationContext()).load(imageUri3);
        drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        drawableTypeRequest.asBitmap()
                .into(new SimpleTarget<Bitmap>(2000, 2000) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mSImageView3.setImageBitmap(resource);
                    }
                });*/
        /*Glide.with(getApplicationContext())
                .load(imageUri)
	            .asBitmap()
                .toBytes()
                .centerCrop()
                .into(new SimpleTarget<byte[]>(250, 250) {
                    @Override
                    public void onResourceReady(byte[] data, GlideAnimation anim) {
                    }
                });*/
    }


    class LoadBitmap extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            mSImageView2.setImageBitmap(aVoid);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            /*try {
                Bitmap bitmap = Glide
                        .with(getApplicationContext())
                        .load(imageUri2)
                        .asBitmap()
                        .into(2000, 2000)
                        .get();
            } catch (Exception e) {
            }*/

            DrawableTypeRequest drawableTypeRequest = Glide
                    .with(getApplicationContext())
                    .load(imageUri2);
            drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE);
            try {
                Bitmap bitmap = (Bitmap) drawableTypeRequest.asBitmap().into(2000, 2000).get();
                return bitmap;
            } catch (Exception e) {
            }
            return null;
        }
    }


}

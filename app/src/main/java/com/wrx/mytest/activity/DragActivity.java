package com.wrx.mytest.activity;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.wrx.mytest.R;

/**
 * Created by wuruixiong on 2017/10/20.
 */

public class DragActivity extends Activity implements View.OnDragListener {

    View mRootLayout;
    ImageView mDrawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        mRootLayout = findViewById(R.id.drag_root_layout);
        mDrawingView = (ImageView) findViewById(R.id.draging_view);

        mDrawingView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(mDrawingView);
                ClipData data = ClipData.newPlainText("", "");
                mDrawingView.startDrag(data, shadowBuilder, mDrawingView, 0);
                return false;
            }
        });

        mRootLayout.setOnDragListener(this);


    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.e("DragEvent", "started");
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                Log.e("DragEvent", "location");
                break;
            case DragEvent.ACTION_DROP:
                Log.e("DragEvent", "drop");
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.e("DragEvent", "ended");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.e("DragEvent", "entered");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.e("DragEvent", "edited");
                break;
            default:
                break;
        }
        return true;
    }
}

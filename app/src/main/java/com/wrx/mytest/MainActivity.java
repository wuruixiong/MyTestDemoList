package com.wrx.mytest;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.wrx.mytest.activity.CursorLoaderTestActivity;
import com.wrx.mytest.activity.DragActivity;
import com.wrx.mytest.activity.ElevationActivity;
import com.wrx.mytest.activity.InputTypeActivity;
import com.wrx.mytest.activity.LoginActivity;
import com.wrx.mytest.activity.MaskActivity;
import com.wrx.mytest.activity.MixViewEfActivity;
import com.wrx.mytest.activity.MultiplePointersActivity;
import com.wrx.mytest.activity.TestActivity;
import com.wrx.mytest.activity.TextBeMaskActivity;
import com.wrx.mytest.activity.TouchTestActivity;
import com.wrx.mytest.activity.ViewLifecycleActivity;
import com.wrx.mytest.activity.AnimZoomActivity;
import com.wrx.mytest.memory.MemoryActivity;
import com.wrx.mytest.opengl.GLSurActivity;
import com.wrx.mytest.opengl.GLSurActivity2;
import com.wrx.mytest.textIntent.IntentTestActivity;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private Sample[] mSamples;
    private GridView mGridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prepare list of samples in this dashboard.
        mSamples = new Sample[]{
                new Sample("Hello title", "hello my test demo list",
                        TestActivity.class),
                new Sample("Status bar and mix views", "Some views for effect : " +
                        "dotted line"
                        , MixViewEfActivity.class),
                new Sample("Open GL", "GL SurfaceView Test",
                        GLSurActivity.class),
                new Sample("Open GL 2", "GL SurfaceView Test",
                        GLSurActivity2.class),
                new Sample("View Lifecycle", "Test View Create Process Method",
                        ViewLifecycleActivity.class),
                new Sample("View Touch", "Test View Touch",
                        TouchTestActivity.class),
                new Sample("Intent Test", "Intent Test",
                        IntentTestActivity.class),
                new Sample("Elevation Test", "Elevation Shade Test",
                        ElevationActivity.class),
                new Sample("Multiple Pointers", "The view touch with multiple pointers",
                        MultiplePointersActivity.class),
                new Sample("Mask Test", "Test the layout mask",
                        MaskActivity.class),
                new Sample("Be Text Mask", "Test activity mask activity",
                        TextBeMaskActivity.class),
                new Sample("Anim image zoom", "zooming with anim",
                        AnimZoomActivity.class),
                new Sample("Login Layout", "Login and keyboard up test",
                        LoginActivity.class),
                new Sample("InputType List", "All input type EditView",
                        InputTypeActivity.class),
                new Sample("Memory analyse", "memory overflow analyse",
                        MemoryActivity.class),
                new Sample("Drag", "drag view test",
                        DragActivity.class),
                new Sample("CursorLoader", "CursorLoader and LoaderManager test",
                        CursorLoaderTestActivity.class),
        };


        // Prepare the GridView
        mGridView = (GridView) findViewById(android.R.id.list);
        mGridView.setAdapter(new SampleAdapter());
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> container, View view, int position, long id) {
        startActivity(mSamples[position].intent);
    }

    private class SampleAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mSamples.length;
        }

        @Override
        public Object getItem(int position) {
            return mSamples[position];
        }

        @Override
        public long getItemId(int position) {
            return mSamples[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.sample_item,
                        container, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(
                    mSamples[position].title);
            ((TextView) convertView.findViewById(android.R.id.text2)).setText(
                    mSamples[position].description);
            return convertView;
        }
    }

    private class Sample {
        String title;
        String description;
        Intent intent;

        private Sample(String titleResId, String descriptionResId, Intent intent) {
            this.intent = intent;
            this.title = titleResId;
            this.description = descriptionResId;
        }

        private Sample(String title, String description,
                       Class<? extends Activity> activityClass) {
            this(title, description,
                    new Intent(MainActivity.this, activityClass));
        }
    }
}

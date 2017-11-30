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
import com.wrx.mytest.design.CollapsingToolbarActivity;
import com.wrx.mytest.clip.PathActivity;
import com.wrx.mytest.glide.GlideActivity;
import com.wrx.mytest.http.OkHttpActivity;
import com.wrx.mytest.recycle.RecycleBtDeleteActivity;
import com.wrx.mytest.recycle.RecycleMeActivity;
import com.wrx.mytest.recycle.RecycleTouchHelpActivity;


/**
 * A simple launcher activity offering access to the individual samples in this project.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private Sample[] mSamples;
    private GridView mGridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prepare list of samples in this dashboard.
        mSamples = new Sample[]{
                new Sample("Hello title", "hello",
                        TestActivity.class),
                new Sample("Recycle View Touch Help", "Recycle View Touch Help",
                        RecycleTouchHelpActivity.class),
                new Sample("Gestures", "Gestures touch test",
                        GesturesActivity.class),
                new Sample("Glide", "Glide libs test",
                        GlideActivity.class),
                new Sample("OK http", "OK http test",
                        OkHttpActivity.class),
                new Sample("Draw path", "draw path",
                        PathActivity.class),
                new Sample("Recycle delete", "recycle delete",
                        RecycleBtDeleteActivity.class),
                new Sample("Recycle delete2", "recycle delete",
                        RecycleMeActivity.class),
                new Sample("CollapsingToolbar", "CollapsingToolbar test",
                        CollapsingToolbarActivity.class),
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

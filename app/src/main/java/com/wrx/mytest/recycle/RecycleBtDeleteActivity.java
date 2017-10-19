package com.wrx.mytest.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wrx.mytest.R;

/**
 * Created by wuruixiong on 2017/10/19.
 */

public class RecycleBtDeleteActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gridview);
        RecycleListAdapter adapter = new RecycleListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


}

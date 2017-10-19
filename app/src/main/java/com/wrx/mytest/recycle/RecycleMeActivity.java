package com.wrx.mytest.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wrx.mytest.R;

/**
 * Created by wuruixiong on 2017/10/19.
 */

public class RecycleMeActivity extends Activity{


    private RecyclerView mRecyclerView;
    private MyAda myAda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_delete);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myAda = new MyAda();
        mRecyclerView.setAdapter(myAda);
    }


    class MyAda extends RecyclerView.Adapter <MyHold>{
        @Override
        public MyHold onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecycleMeActivity.this).inflate(R.layout.rec_me_item, parent, false);
            return new MyHold(view);
        }

        @Override
        public void onBindViewHolder(MyHold holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class MyHold extends RecyclerView.ViewHolder implements View.OnClickListener{

        public MyHold(View itemView) {
            super(itemView);
            //View view = itemView.findViewById(R.id.my_rec_me_content);
            View tView = itemView.findViewById(R.id.btnTop);
            View rView = itemView.findViewById(R.id.btnUnRead);
            View dView = itemView.findViewById(R.id.btnDelete);

            //view.setOnClickListener(this);
            tView.setOnClickListener(this);
            rView.setOnClickListener(this);
            dView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int p = getAdapterPosition();
            switch (view.getId()) {
                /*case R.id.content:
                    Toast.makeText(getApplicationContext(), p + "/主体点击", Toast.LENGTH_SHORT).show();
                    break;*/
                case R.id.btnTop:
                    Toast.makeText(getApplicationContext(), p + "/置顶点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnUnRead:
                    Toast.makeText(getApplicationContext(), p + "/已读点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnDelete:
                    Toast.makeText(getApplicationContext(), p + "/删除点击", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }



}

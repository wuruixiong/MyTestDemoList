package com.wrx.mytest.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhuanfa on 2017/7/4.
 */

public class BaseTouchHelpAdapter extends RecyclerView.Adapter<BaseTouchHelpAdapter.MyViewHolder>{

    private ArrayList<String> mStringArrayList;

    public BaseTouchHelpAdapter() {
        super();
        mStringArrayList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            mStringArrayList.add("text" + i);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setBackgroundResource(android.R.color.holo_purple);
        holder.mTextView.setTextSize(35);
        holder.mTextView.setText(mStringArrayList.get(position));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);

        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER);
        return new MyViewHolder(textView);
    }

    @Override
    public int getItemCount() {
        return mStringArrayList.size();
    }

    public void onItemDismiss(int position) {
        mStringArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mStringArrayList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public MyViewHolder(TextView itemView) {
            super(itemView);
            mTextView = itemView;
        }
    }
}

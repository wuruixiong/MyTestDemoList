package com.wrx.mytest.design;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wuruixiong on 2017/11/30.
 */

public class TextRecycleView extends RecyclerView{

    public TextRecycleView(Context context) {
        super(context);
        init();
    }

    public TextRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init () {
        LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setLayoutManager(layoutManager);
        TextAdapter textAdapter = new TextAdapter();
        setAdapter(textAdapter);
    }

    class TextAdapter extends Adapter<TextHolder> {
        @Override
        public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TextHolder(new TextView(getContext()));
        }
        @Override
        public void onBindViewHolder(TextHolder holder, int position) {
            ((TextView)holder.itemView).setText(position + "");
            ((TextView)holder.itemView).setTextSize(35);
        }
        @Override
        public int getItemCount() {
            return 30;
        }
    }

    class TextHolder extends ViewHolder {
        public TextHolder(View itemView) {
            super(itemView);
        }
    }




}

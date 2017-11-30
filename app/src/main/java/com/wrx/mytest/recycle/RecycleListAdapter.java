package com.wrx.mytest.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.wrx.mytest.R;

public class RecycleListAdapter extends RecyclerSwipeAdapter<RecycleListAdapter.MyViewHolder> {

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText("position: " + (position));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        TextView mTextView;
        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.position);
            mTextView = (TextView)itemView.findViewById(R.id.position_text);
            mImageView = (ImageView)itemView.findViewById(R.id.trash);
            mView.setOnClickListener(this);
            mImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.trash) {
                Toast.makeText(view.getContext().getApplicationContext(),
                        "click delete " + getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
            } else
            if (view.getId() == R.id.position) {
                Toast.makeText(view.getContext().getApplicationContext(),
                        "click position " + getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

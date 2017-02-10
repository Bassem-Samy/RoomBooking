package com.bassem.roombooking.roomdetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bassem.roombooking.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bassem Samy on 2/11/2017.
 */

public class StringRecyclerViewAdapter extends RecyclerView.Adapter<StringRecyclerViewAdapter.ViewHolder> {
    ArrayList<String> mDataset;
    int mTextViewLayoutId;

    public StringRecyclerViewAdapter(String[] items, int textViewLayoutId) {
        mDataset = new ArrayList<String>(Arrays.asList(items));
        mTextViewLayoutId = textViewLayoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(mTextViewLayoutId, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTextView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView;
        }
    }
}

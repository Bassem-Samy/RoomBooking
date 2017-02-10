package com.bassem.roombooking.roomdetails;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bassem.roombooking.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bassem Samy on 2/11/2017.
 */

public class RoomImagesRecyclerViewAdapter extends RecyclerView.Adapter<RoomImagesRecyclerViewAdapter.ViewHolder> {
    ArrayList<String> mDataset;
    View.OnClickListener mOnClickListener;
    Context mContext;
    private static final String IMAGES_BASEURL = "https://challenges.1aim.com/roombooking_app/";

    public RoomImagesRecyclerViewAdapter(Context context, String[] imagesUrls, View.OnClickListener onClickListener) {
        mDataset = new ArrayList<>(Arrays.asList(imagesUrls));
        mOnClickListener = onClickListener;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_image_item, parent, false);
        v.setOnClickListener(mOnClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(IMAGES_BASEURL + mDataset.get(position)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.roomImageView);
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView roomImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            roomImageView = (ImageView) itemView.findViewById(R.id.img_room);
        }
    }
}

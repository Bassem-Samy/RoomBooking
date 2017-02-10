package com.bassem.roombooking.imagegallery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bassem.roombooking.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bassem Samy on 2/11/2017.
 */

public class GalleryPagerAdapter extends PagerAdapter {
    private static final String IMAGES_BASEURL = "https://challenges.1aim.com/roombooking_app/";

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> mDataset;

    public GalleryPagerAdapter(Context context, String[] imageurls) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDataset = new ArrayList<>(Arrays.asList(imageurls));
    }

    @Override
    public int getCount() {
        if (mDataset != null)
            return mDataset.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.gallery_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_gallery_item);
        Glide.with(mContext).load(IMAGES_BASEURL + mDataset.get(position)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
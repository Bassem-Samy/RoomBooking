package com.bassem.roombooking.imagegallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bassem.roombooking.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GalleryFragment extends Fragment {

    public static final String TAG = "gallery_fragment";
    public static final String ARG_IMAGESURLS = "images_urls";
    public static final String ARG_STARTITEMINDEX = "start_index";
    @BindView(R.id.pager)
    ViewPager galleryViewPager;
    String[] imagesUrls;
    int startIndex;
    GalleryPagerAdapter pagerAdapter;


    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance(String[] imageUrls, int startItemIndex) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_IMAGESURLS, imageUrls);
        args.putInt(ARG_STARTITEMINDEX, startItemIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imagesUrls = getArguments().getStringArray(ARG_IMAGESURLS);
            startIndex = getArguments().getInt(ARG_STARTITEMINDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeGallery();
    }

    private void initializeGallery() {
        pagerAdapter = new GalleryPagerAdapter(getContext(), imagesUrls);
        galleryViewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        galleryViewPager.setCurrentItem(startIndex);
    }
}

package com.bassem.roombooking.imagegallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;

import com.bassem.roombooking.R;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initializeFragment();
    }

    private void initializeFragment() {
        if (getSupportFragmentManager().findFragmentByTag(GalleryFragment.TAG) == null) {
            String[] imagesUrls = getIntent().getStringArrayExtra(GalleryFragment.ARG_IMAGESURLS);
            int startIndex = getIntent().getIntExtra(GalleryFragment.ARG_STARTITEMINDEX, 0);
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_container,
                    GalleryFragment.newInstance(imagesUrls, startIndex),
                    GalleryFragment.TAG).commit();
        }
    }
}

package com.bassem.roombooking.roomdetails;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bassem.roombooking.R;
import com.bassem.roombooking.bookroom.BookRoomActivity;
import com.bassem.roombooking.bookroom.BookRoomFragment;
import com.bassem.roombooking.imagegallery.GalleryActivity;
import com.bassem.roombooking.imagegallery.GalleryFragment;
import com.bassem.roombooking.models.Room;

public class RoomDetailsActivity extends AppCompatActivity implements RoomDetailsFragment.OnFragmentInteractionListener {

    private static final int REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
        initalizeFragment();
    }

    private void initalizeFragment() {
        if (getSupportFragmentManager().findFragmentByTag(RoomDetailsFragment.TAG) == null) {
            Room item = getIntent().getParcelableExtra(RoomDetailsFragment.ARG_ROOMPARAM);
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frm_container,
                    RoomDetailsFragment.newInstance(item),
                    RoomDetailsFragment.TAG
            ).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Room room) {
        Intent bookRoomIntent = new Intent(this, BookRoomActivity.class);
        bookRoomIntent.putExtra(BookRoomFragment.ARG_ROOM, room);
        startActivityForResult(bookRoomIntent, REQUEST_CODE);
    }

    @Override
    public void onGalleryItemClicked(String[] imagesUrls, int startIndex) {
        Intent galleryIntent = new Intent(this, GalleryActivity.class);
        galleryIntent.putExtra(GalleryFragment.ARG_IMAGESURLS, imagesUrls);
        galleryIntent.putExtra(GalleryFragment.ARG_STARTITEMINDEX, startIndex);
        startActivity(galleryIntent);

    }
}

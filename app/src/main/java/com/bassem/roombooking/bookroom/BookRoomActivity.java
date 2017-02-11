package com.bassem.roombooking.bookroom;

import android.database.CursorJoiner;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.bassem.roombooking.R;
import com.bassem.roombooking.models.Room;

public class BookRoomActivity extends AppCompatActivity implements BookRoomFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);
        initializeFragment();
    }

    private void initializeFragment() {
        if (getSupportFragmentManager().findFragmentByTag(BookRoomFragment.TAG) == null) {
            Room item = getIntent().getParcelableExtra(BookRoomFragment.ARG_ROOM);
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_container,
                    BookRoomFragment.newInstance(item),
                    BookRoomFragment.TAG).commit();
        }
    }

    @Override
    public void onFragmentInteraction(int result) {

    }
}

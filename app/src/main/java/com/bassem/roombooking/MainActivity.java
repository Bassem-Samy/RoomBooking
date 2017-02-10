package com.bassem.roombooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bassem.roombooking.helper.ServiceCallResultListener;
import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;
import com.bassem.roombooking.roomdetails.RoomDetailsActivity;
import com.bassem.roombooking.roomdetails.RoomDetailsFragment;
import com.bassem.roombooking.roomslisting.RoomsListingFragment;
import com.bassem.roombooking.services.ServiceConnector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RoomsListingFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFragments();
    }

    private void initializeFragments() {
        if (getSupportFragmentManager().findFragmentByTag(RoomsListingFragment.ROOM_LISTING_FRAGMENT_TAG) == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_container, RoomsListingFragment.newInstance(null, null), RoomsListingFragment.ROOM_LISTING_FRAGMENT_TAG).commit();
        }
    }


    @Override
    public void onRoomClicked(Room room) {
        Intent detailsIntent = new Intent(this, RoomDetailsActivity.class);
        detailsIntent.putExtra(RoomDetailsFragment.ARG_ROOMPARAM, room);
        startActivity(detailsIntent);

    }
}

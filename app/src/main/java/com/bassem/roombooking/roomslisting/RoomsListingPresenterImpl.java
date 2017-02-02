package com.bassem.roombooking.roomslisting;

import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.Room;

import java.util.List;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public class RoomsListingPresenterImpl implements RoomsListingPresenter, ServiceGetRoomsResultListener {
    private RoomsListingView mView;

    public RoomsListingPresenterImpl(RoomsListingView view) {
        this.mView = view;
    }

    @Override
    public void onResponse(List<Room> rooms) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void getRoomsListByTime(long unixTime) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void navigateToNextDay() {

    }

    @Override
    public void navigateToPreviousDay() {

    }
}

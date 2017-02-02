package com.bassem.roombooking.roomslisting;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public interface RoomsListingPresenter {
    void getRoomsListByTime(long unixTime);

    void onDestroy();

    void navigateToNextDay();

    void navigateToPreviousDay();
}

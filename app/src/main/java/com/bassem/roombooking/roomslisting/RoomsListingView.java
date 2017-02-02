package com.bassem.roombooking.roomslisting;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public interface RoomsListingView {
    void showProgress();

    void hideProgress();

    void navigateToNextDay();

    void navigateToPreviousDay();

    void showMessage(String message);
    // add filters and clicks afterwards

}

package com.bassem.roombooking.roomslisting;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public interface RoomsListingPresenter {
    void getRoomsListByTime(long unixTime);

    void onDestroy();

    void navigateToNextDay();

    void navigateToPreviousDay();

    void selectDate(int year,int monthInYear,int dayInMonth);
    void loadInitialData();
}

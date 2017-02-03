package com.bassem.roombooking.roomslisting;

import com.bassem.roombooking.models.Room;

import java.util.Date;
import java.util.List;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public interface RoomsListingView {
    void showProgress();

    void hideProgress();

    void navigateToNextDay();

    void navigateToPreviousDay();

    void showCalendar();

    void showMessage(String message);

    void datePicked(int year, int monthInYear, int dayInMonth);
    // add filters and clicks afterwards
    void updateRoomsList(List<Room> roomList);

}

package com.bassem.roombooking.bookroom;

import com.bassem.roombooking.models.Room;

/**
 * Created by Bassem Samy on 2/11/2017.
 */

public interface BookRoomPresenter {
    void displayData();

    String convertIndexRangeToTime(int value);
}

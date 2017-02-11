package com.bassem.roombooking.bookroom;

import android.view.View;

import com.bassem.roombooking.models.Room;

/**
 * Created by Bassem Samy on 2/11/2017.
 */

public class BookRoomPresenterImpl implements BookRoomPresenter {
    BookRoomView mView;
    Room mRoom;

    public BookRoomPresenterImpl(Room room, BookRoomView view) {
        mRoom = room;
        mView = view;
    }

    @Override
    public void displayData() {
        mView.displayData(mRoom);
    }

    @Override
    public String convertIndexRangeToTime(int value) {
        String hour = Integer.toString(7 + (value / 4));
        String minutes = Integer.toString(15 * (value % 4));
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        return hour + ":" + minutes;
    }


}

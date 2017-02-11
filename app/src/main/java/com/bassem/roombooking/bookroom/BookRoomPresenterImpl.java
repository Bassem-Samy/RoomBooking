package com.bassem.roombooking.bookroom;

import android.view.View;

import com.bassem.roombooking.models.Room;

/**
 * Created by Mina Samy on 2/11/2017.
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
}

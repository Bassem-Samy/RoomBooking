package com.bassem.roombooking.helper;

import com.bassem.roombooking.models.Room;

import java.util.List;

/**
 * Created by Bassem Samy on 1/31/2017.
 */

public interface ServiceGetRoomsResultListener {
    public void onResponse(List<Room> rooms);

    public void onError(Throwable throwable);

    public void onCancelled();
}

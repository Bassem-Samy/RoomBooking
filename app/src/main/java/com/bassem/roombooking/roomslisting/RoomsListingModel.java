package com.bassem.roombooking.roomslisting;

import com.bassem.roombooking.helper.ServiceCallResultListener;
import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public interface RoomsListingModel {
    public void getRoomsList(GetRoomsPostParameters getRoomsPostParameters, ServiceGetRoomsResultListener serviceCallResultListener);

    public void stopGettingRoomsList();
}

package com.bassem.roombooking.roomslisting;

import android.util.Log;

import com.bassem.roombooking.helper.ServiceCallResultListener;
import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;
import com.bassem.roombooking.services.ServiceConnector;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public class RoomsListingModelImpl implements RoomsListingModel {
    private GetRoomsPostParameters mGetRoomsPostParameters;
    private ServiceGetRoomsResultListener mServiceGetRoomsResultListener;
    private Call<List<Room>> getRoomsListCall;


    @Override
    public void getRoomsList(GetRoomsPostParameters getRoomsPostParameters, ServiceGetRoomsResultListener serviceGetRoomsResultListener) {
        this.mGetRoomsPostParameters = getRoomsPostParameters;
        this.mServiceGetRoomsResultListener = serviceGetRoomsResultListener;
        getRoomsListCall = ServiceConnector.getRooms(mGetRoomsPostParameters, mServiceGetRoomsResultListener);
    }

    @Override
    public void stopGettingRoomsList() {
        if (getRoomsListCall != null) {
            getRoomsListCall.cancel();
           // getRoomsListCall = null;
            Log.e("stopGettingRoomsList", "canceled");
        }
    }

}

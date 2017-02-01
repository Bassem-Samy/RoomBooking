package com.bassem.roombooking.services;

import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Bassem Samy on 1/28/2017.
 */

public interface GetRoomsService {
    @POST("getrooms")
    Call<List<Room>> getRooms(@Body GetRoomsPostParameters param);

}

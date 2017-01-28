package com.bassem.roombooking.services;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Bassem Samy on 1/28/2017.
 */

public interface GetRoomsService {
    @POST("getrooms")
    Call<String> getRooms();

}

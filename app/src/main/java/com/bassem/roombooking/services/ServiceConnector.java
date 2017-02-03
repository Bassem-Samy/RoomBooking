package com.bassem.roombooking.services;


import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bassem Samy on 1/28/2017.
 */

public class ServiceConnector {
    static final String API_BASE_URL = "https://challenges.1aim.com/roombooking_app/";

    /**
     * retruns a list of rooms by a certain date
     *
     * @param serviceGetRoomsResultListener
     * @param getRoomsPostParameters
     * @return
     */
    public static Call<List<Room>> getRooms(GetRoomsPostParameters getRoomsPostParameters, final ServiceGetRoomsResultListener serviceGetRoomsResultListener) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRoomsService service = retrofit.create(GetRoomsService.class);
        Call<List<Room>> getRoomsServiceCall = service.getRooms(getRoomsPostParameters);
        getRoomsServiceCall.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (serviceGetRoomsResultListener != null) {
                    serviceGetRoomsResultListener.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                if (serviceGetRoomsResultListener != null) {
                    if (call.isCanceled()) {
                        serviceGetRoomsResultListener.onCancelled();
                    } else {
                        serviceGetRoomsResultListener.onError(t);
                    }

                }
            }
        });
        return getRoomsServiceCall;
    }

}

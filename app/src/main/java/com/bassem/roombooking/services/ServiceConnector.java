package com.bassem.roombooking.services;


import com.bassem.roombooking.helper.ServiceCallResultListener;
import com.bassem.roombooking.helper.ToStringConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Bassem Samy on 1/28/2017.
 */

public class ServiceConnector {
    static final String API_BASE_URL = "https://challenges.1aim.com/roombooking_app/";


    public static Call<String> getRooms(final ServiceCallResultListener serviceCallResultListener) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(new ToStringConverterFactory())
                .build();
        GetRoomsService service = retrofit.create(GetRoomsService.class);
        Call<String> getRoomsServiceCall = service.getRooms();
        getRoomsServiceCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (serviceCallResultListener != null) {
                    serviceCallResultListener.onResponse(response.body());
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (serviceCallResultListener != null) {
                    serviceCallResultListener.onError(t);
                }
            }
        });
        return getRoomsServiceCall;

    }

}

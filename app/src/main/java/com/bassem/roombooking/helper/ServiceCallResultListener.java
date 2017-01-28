package com.bassem.roombooking.helper;

/**
 * Created by Bassem Samy on 1/28/2017.
 */

public interface ServiceCallResultListener {
    public void onResponse(String json);

    public void onError(Throwable throwable);
}

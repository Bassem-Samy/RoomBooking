package com.bassem.roombooking.roomslisting;

import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;

import java.util.Date;
import java.util.List;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public class RoomsListingPresenterImpl implements RoomsListingPresenter, ServiceGetRoomsResultListener {
    private RoomsListingView mView;
    private RoomsListingModelImpl mModel;
    GetRoomsPostParameters getRoomsPostParameters;

    public RoomsListingPresenterImpl(RoomsListingView view) {
        this.mView = view;
        mModel = new RoomsListingModelImpl();
        getRoomsPostParameters = new GetRoomsPostParameters();
    }

    @Override
    public void onResponse(List<Room> rooms) {
        mView.hideProgress();
        mView.updateRoomsList(rooms);
    }

    @Override
    public void onError(Throwable throwable) {
        //mView.showMessage("");
        mView.hideProgress();
    }

    @Override
    public void getRoomsListByTime(long unixTime) {
        getRoomsPostParameters.setDate(unixTime);
        mModel.stopGettingRoomsList();
        mView.showProgress();
        mModel.getRoomsList(getRoomsPostParameters, this);
    }

    @Override
    public void onDestroy() {
        mModel.stopGettingRoomsList();
    }

    @Override
    public void navigateToNextDay() {

    }

    @Override
    public void navigateToPreviousDay() {

    }

    @Override
    public void selectDate(Date date) {

    }
}

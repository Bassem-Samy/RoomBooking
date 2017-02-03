package com.bassem.roombooking.roomslisting;

import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Bassem Samy on 2/2/2017.
 */

public class RoomsListingPresenterImpl implements RoomsListingPresenter, ServiceGetRoomsResultListener {
    private RoomsListingView mView;
    private RoomsListingModelImpl mModel;
    GetRoomsPostParameters getRoomsPostParameters;
    Calendar mCurrentCalendar;

    public Calendar getCurrentCalendar() {
        return mCurrentCalendar;
    }

    public void setCurrentCalendar(Calendar currentDate) {
        this.mCurrentCalendar = currentDate;
    }


    public RoomsListingPresenterImpl(RoomsListingView view) {
        this.mView = view;
        mModel = new RoomsListingModelImpl();
        getRoomsPostParameters = new GetRoomsPostParameters();
        mCurrentCalendar = Calendar.getInstance();

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
        mCurrentCalendar.add(Calendar.DAY_OF_MONTH, 1);
        selectDate(mCurrentCalendar.get(Calendar.YEAR), mCurrentCalendar.get(Calendar.MONTH), mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void navigateToPreviousDay() {
        if (mCurrentCalendar.compareTo(Calendar.getInstance()) > 0) {
            mCurrentCalendar.add(Calendar.DAY_OF_MONTH, -1);
            selectDate(mCurrentCalendar.get(Calendar.YEAR), mCurrentCalendar.get(Calendar.MONTH), mCurrentCalendar.get(Calendar.DAY_OF_MONTH));
        }
        else{
            mView.showMessage("Please select a day in the future");
        }
    }

    @Override
    public void selectDate(int year, int monthInYear, int dayInMonth) {
        mCurrentCalendar.set(year, monthInYear, dayInMonth);
        mView.datePicked(year, monthInYear, dayInMonth);
        getRoomsListByTime(mCurrentCalendar.getTimeInMillis());
    }

    @Override
    public void loadInitialData() {
        getRoomsListByTime(mCurrentCalendar.getTimeInMillis());

    }
}

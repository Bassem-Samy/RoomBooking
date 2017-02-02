package com.bassem.roombooking.roomslisting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bassem.roombooking.R;
import com.bassem.roombooking.models.Room;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RoomsListingFragment extends Fragment implements RoomsListingView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ROOM_LISTING_FRAGMENT_TAG = "RoomsListingFragment";
    private String mParam1;
    private String mParam2;
    static final String MAIN_DATE_FORMAT = "E dd/M/yyy";
    android.text.format.DateFormat dateFormat = new android.text.format.DateFormat();
    RoomsListingPresenterImpl mPresenter;
    @BindView(R.id.txt_selected_date)
    TextView selectedDateTextView;

    public RoomsListingFragment() {

    }

    public static RoomsListingFragment newInstance(String param1, String param2) {
        RoomsListingFragment fragment = new RoomsListingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms_listing, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new RoomsListingPresenterImpl(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void navigateToNextDay() {

    }

    @Override
    public void navigateToPreviousDay() {

    }

    @Override
    public void showCalendar() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void DatePicked(Date date) {
        mPresenter.selectDate(date);
        selectedDateTextView.setText(dateFormat.format(MAIN_DATE_FORMAT, date));
    }

    @Override
    public void updateRoomsList(List<Room> roomList) {

    }
}

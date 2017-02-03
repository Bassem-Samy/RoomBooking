package com.bassem.roombooking.roomslisting;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.roombooking.R;
import com.bassem.roombooking.models.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RoomsListingFragment extends Fragment implements RoomsListingView, View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ROOM_LISTING_FRAGMENT_TAG = "RoomsListingFragment";
    private String mParam1;
    private String mParam2;
    static final String MAIN_DATE_FORMAT = "E dd/M/yyy";
    android.text.format.DateFormat dateFormat = new android.text.format.DateFormat();
    RoomsListingPresenterImpl mPresenter;
    DatePickerDialog datePickerDialog;
    RoomsAdapter mRoomsAdapter;
    ArrayList<Room> roomsArrayList;
    @BindView(R.id.txt_selected_date)
    TextView selectedDateTextView;
    @BindView(R.id.rclr_rooms)
    RecyclerView roomsRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    LinearLayoutManager roomsLinearLayoutManager;

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
        initializeDatePickerDialog();
        roomsLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        roomsRecyclerView.setLayoutManager(roomsLinearLayoutManager);
        mRoomsAdapter = new RoomsAdapter(getContext(), this, roomsArrayList);
        roomsRecyclerView.setAdapter(mRoomsAdapter);
        mPresenter.loadInitialData();
    }

    private void initializeDatePickerDialog() {


        datePickerDialog = new DatePickerDialog(getContext(), onDateSetListener,
                mPresenter.getCurrentCalendar().get(Calendar.YEAR),
                mPresenter.getCurrentCalendar().get(Calendar.MONTH),
                mPresenter.getCurrentCalendar().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePicked(mPresenter.getCurrentCalendar().get(Calendar.YEAR),
                mPresenter.getCurrentCalendar().get(Calendar.MONTH),
                mPresenter.getCurrentCalendar().get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void showProgress() {
        mainProgressBar.setVisibility(View.VISIBLE);
        roomsRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.GONE);
        roomsRecyclerView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.img_btn_previous_date)
    @Override
    public void navigateToNextDay() {

        mPresenter.navigateToPreviousDay();
    }

    @OnClick(R.id.img_btn_next_date)
    @Override
    public void navigateToPreviousDay() {
        mPresenter.navigateToNextDay();
    }

    @Override
    public void showCalendar() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void datePicked(int year, int monthInYear, int dayInMonth) {

        updateSelectedDateText();
    }

    @Override
    public void updateRoomsList(List<Room> roomList) {

        roomsArrayList = new ArrayList<>(roomList);
        mRoomsAdapter.setDataSet(roomsArrayList);

    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthInYear, int dayInMonth) {
            mPresenter.selectDate(year, monthInYear, dayInMonth);

        }
    };

    private void updateSelectedDateText() {
        selectedDateTextView.setText(dateFormat.format(MAIN_DATE_FORMAT, mPresenter.getCurrentCalendar()));
        datePickerDialog.getDatePicker().updateDate(mPresenter.getCurrentCalendar().get(Calendar.YEAR),
                mPresenter.getCurrentCalendar().get(Calendar.MONTH),
                mPresenter.getCurrentCalendar().get(Calendar.DAY_OF_MONTH));
    }

    @OnClick(R.id.frm_selct_date)
    void showDatePicker() {
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        int position = roomsRecyclerView.getChildAdapterPosition(view);
        Room item = mRoomsAdapter.getItemByPosition(position);
        Toast.makeText(getContext(), item.getName(), Toast.LENGTH_SHORT).show();
    }
}

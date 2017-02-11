package com.bassem.roombooking.bookroom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bassem.roombooking.R;
import com.bassem.roombooking.controls.RoomAvailabilityDisplayBar;
import com.bassem.roombooking.models.Room;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.apptik.widget.MultiSlider;


public class BookRoomFragment extends Fragment implements BookRoomView {
    public static final String ARG_ROOM = "room";
    public static final String TAG = "book_room";
    private Room mRoom;
    BookRoomPresenterImpl mPresenter;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.txt_room_name)
    TextView roomNameTextView;
    @BindView(R.id.room_availability_display_bar)
    RoomAvailabilityDisplayBar roomAvailabilityDisplayBar;
    @BindView(R.id.range_slider)
    MultiSlider rangeMultiSlider;
    @BindView(R.id.txt_from)
    TextView fromTimeTextView;
    @BindView(R.id.txt_to)
    TextView toTimeTextView;

    public BookRoomFragment() {
        // Required empty public constructor
    }

    public static BookRoomFragment newInstance(Room room) {
        BookRoomFragment fragment = new BookRoomFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ROOM, room);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRoom = getArguments().getParcelable(ARG_ROOM);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_room, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new BookRoomPresenterImpl(mRoom, this);
        mPresenter.displayData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void displayData(Room room) {
        roomNameTextView.setText(room.getName());
        roomAvailabilityDisplayBar.setData(7, 19, 4, room.getAvail());
        rangeMultiSlider.setMin(0);
        rangeMultiSlider.setMax(48);
        rangeMultiSlider.setOnThumbValueChangeListener(onThumbValueChangeListener);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int resultCode);
    }

    MultiSlider.OnThumbValueChangeListener onThumbValueChangeListener = new MultiSlider.OnThumbValueChangeListener() {
        @Override
        public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
            if (thumbIndex == 0) {
                updateFromText(value);
            } else {
                updateToText(value);
            }
        }

    };

    private void updateFromText(int value) {
        fromTimeTextView.setText(mPresenter.convertIndexRangeToTime(value));
    }

    private void updateToText(int value) {
        toTimeTextView.setText(mPresenter.convertIndexRangeToTime(value));
    }
}

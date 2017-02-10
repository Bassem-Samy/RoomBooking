package com.bassem.roombooking.roomdetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.bassem.roombooking.R;
import com.bassem.roombooking.controls.RoomAvailabilityDisplayBar;
import com.bassem.roombooking.models.Room;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomDetailsFragment extends Fragment implements RoomDetailsView {
    public static final String ARG_ROOMPARAM = "room_param";
    public static final String TAG = "roomdetails_tag";
    private static final int EQUIPMENTS_COLUMNCOUNT = 2;
    private static final int IMAGES_COLUMNCOUNT = 2;
    private Room mRoom;
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.txt_room_name)
    TextView roomNameTextView;
    @BindView(R.id.availability_display_bar)
    RoomAvailabilityDisplayBar roomBar;
    @BindView(R.id.rclr_equipments)
    RecyclerView equipmentsRecyclerView;
    @BindView(R.id.rclr_images)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.txt_size)
    TextView sizeTextView;
    @BindView(R.id.txt_capacity)
    TextView capacityTextView;
    StringRecyclerViewAdapter equipmentsAdapter;
    GridLayoutManager gridLayoutManager;
    RoomImagesRecyclerViewAdapter imagesAdapter;
    GridLayoutManager imagesGridLayoutManager;

    public RoomDetailsFragment() {
        // Required empty public constructor
    }

    public static RoomDetailsFragment newInstance(Room room) {
        RoomDetailsFragment fragment = new RoomDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ROOMPARAM, room);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRoom = getArguments().getParcelable(ARG_ROOMPARAM);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        displayData();
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

    @OnClick(R.id.fab_book)
    @Override
    public void bookRoom() {

    }

    @Override
    public void displayData() {
        roomNameTextView.setText(mRoom.getName());
        roomBar.setData(7, 19, 4, mRoom.getAvail());
        equipmentsAdapter = new StringRecyclerViewAdapter(mRoom.getEquipment(), R.layout.simple_text_item);
        gridLayoutManager = new GridLayoutManager(getContext(), EQUIPMENTS_COLUMNCOUNT);
        equipmentsRecyclerView.setLayoutManager(gridLayoutManager);
        equipmentsRecyclerView.setAdapter(equipmentsAdapter);
        equipmentsAdapter.notifyDataSetChanged();
        sizeTextView.setText(mRoom.getSize());
        capacityTextView.setText(mRoom.getCapacity());


        imagesAdapter = new RoomImagesRecyclerViewAdapter(getContext(), mRoom.getImages(), onImageClickedListener);
        imagesGridLayoutManager = new GridLayoutManager(getContext(), IMAGES_COLUMNCOUNT);
        imagesRecyclerView.setLayoutManager(imagesGridLayoutManager);
        imagesRecyclerView.setAdapter(imagesAdapter);
        imagesAdapter.notifyDataSetChanged();
    }

    View.OnClickListener onImageClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = imagesRecyclerView.getChildAdapterPosition(view);
            if (mListener != null) {
                mListener.onGalleryItemClicked(mRoom.getImages(), position);
            }
        }
    };

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Room room);

        void onGalleryItemClicked(String[] imagesUrls, int startIndex);
    }
}

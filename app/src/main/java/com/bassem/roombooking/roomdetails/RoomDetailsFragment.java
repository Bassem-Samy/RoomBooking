package com.bassem.roombooking.roomdetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bassem.roombooking.R;
import com.bassem.roombooking.models.Room;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RoomDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RoomDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomDetailsFragment extends Fragment {
    private static final String ARG_ROOMPARAM = "room_param";


    private Room mRoom;


    private OnFragmentInteractionListener mListener;

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
        return inflater.inflate(R.layout.fragment_room_details, container, false);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

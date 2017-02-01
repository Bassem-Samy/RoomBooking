package com.bassem.roombooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bassem.roombooking.helper.ServiceCallResultListener;
import com.bassem.roombooking.helper.ServiceGetRoomsResultListener;
import com.bassem.roombooking.models.GetRoomsPostParameters;
import com.bassem.roombooking.models.Room;
import com.bassem.roombooking.services.ServiceConnector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetRoomsPostParameters param = new GetRoomsPostParameters();
        ServiceConnector.getRooms(resultListener, System.currentTimeMillis());
    }

    ServiceGetRoomsResultListener resultListener = new ServiceGetRoomsResultListener() {
        @Override
        public void onResponse(List<Room> rooms) {
            ArrayList<Room> currentRooms = new ArrayList<Room>(rooms);
            Log.e("result", Integer.toString(currentRooms.size()));
        }

        @Override
        public void onError(Throwable throwable) {
            Log.e("error", throwable.getMessage());
        }
    };
}

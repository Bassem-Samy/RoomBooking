package com.bassem.roombooking.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bassem.roombooking.R;

/**
 * Created by Bassem Samy on 2/4/2017.
 */

public class RoomAvailabilityDisplayBar extends FrameLayout {
    private Context mContext;
TextView helloTV;
    public RoomAvailabilityDisplayBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initializeControl(context);
    }

    public RoomAvailabilityDisplayBar(Context context) {
        super(context);
        initializeControl(context);
    }

    void initializeControl(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.availability_display_bar, this);
       // helloTV= (TextView) findViewById(R.id.txt_hello_world);
    }

    public void setHelloText(String text){
       // helloTV.setText(text);
    }
}

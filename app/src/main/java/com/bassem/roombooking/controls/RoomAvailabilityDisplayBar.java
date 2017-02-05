package com.bassem.roombooking.controls;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bassem.roombooking.R;

/**
 * Created by Bassem Samy on 2/4/2017.
 */

public class RoomAvailabilityDisplayBar extends FrameLayout {
    int mStartRange = 7;
    int mEndRange = 17;
    int mSubInterval = 4;
    String[] mAvailableIntervals;
    Context mContext;
    LinearLayout timeBarsLinearLayout;
    LinearLayout separatorsLinearLayout;
    LinearLayout timeSpansLinearLayout;
    int availableColor;
    int busyColor;
    int textColor;
    int separatorColor;
    int orangeColor;
    int blueColor;

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
        timeBarsLinearLayout = (LinearLayout) findViewById(R.id.lnr_time_bars);
        separatorsLinearLayout = (LinearLayout) findViewById(R.id.lnr_separators);
        timeSpansLinearLayout = (LinearLayout) findViewById(R.id.lnr_time_spans);
        availableColor = ContextCompat.getColor(mContext, R.color.colorGreen);
        busyColor = ContextCompat.getColor(mContext, R.color.colorRed);
        textColor = ContextCompat.getColor(mContext, R.color.colorBlack);
        separatorColor = ContextCompat.getColor(mContext, R.color.colorWhite);
        blueColor = ContextCompat.getColor(mContext, R.color.colorBlue);
        orangeColor = ContextCompat.getColor(mContext, R.color.colorOrange);
    }

    public void setData(int startRange, int endRange, int subIntervals, String[] availableIntervals) {
        mStartRange = startRange;
        mEndRange = endRange;
        mSubInterval = subIntervals;
        mAvailableIntervals = availableIntervals;
        //mAvailableIntervals = new String[]{"7:00 - 8:00", "15:00 - 16:00", "17:15 - 19:00"};
        redraw();
    }

    private void redraw() {
        timeBarsLinearLayout.removeAllViews();
        separatorsLinearLayout.removeAllViews();
        timeSpansLinearLayout.removeAllViews();

        LinearLayout.LayoutParams intervalParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int width = (int) ((1 * displayMetrics.density) + 0.5);
        timeBarsLinearLayout.setWeightSum((mStartRange - mEndRange) * 4);
        separatorsLinearLayout.setWeightSum(mEndRange - mStartRange);
        FrameLayout.LayoutParams whiteSeparatorParams = new FrameLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        whiteSeparatorParams.gravity = Gravity.START;
        for (int i = 0; i < (mEndRange - mStartRange) * mSubInterval; i++) {
            FrameLayout frm = new FrameLayout(mContext);
            frm.setLayoutParams(intervalParams);
            if (isIntervalAvailable(i) == true) {
                frm.setBackgroundColor(availableColor);
            } else {
                frm.setBackgroundColor(busyColor);
            }
            timeBarsLinearLayout.addView(frm);
        }
        for (int j = 0; j <= (mEndRange - mStartRange); j++) {
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(intervalParams);
            tv.setGravity(TEXT_ALIGNMENT_CENTER);
            tv.setText(Integer.toString(j + mStartRange));
            tv.setTextColor(textColor);
            timeSpansLinearLayout.addView(tv);


        }
        for (int k = 0; k < (mEndRange - mStartRange); k++) {
            FrameLayout separatorFrameLayout = new FrameLayout(mContext);
            separatorFrameLayout.setLayoutParams(intervalParams);
            FrameLayout whiteSeparatorFrameLayout = new FrameLayout(mContext);
            whiteSeparatorFrameLayout.setBackgroundColor(separatorColor);
            whiteSeparatorFrameLayout.setLayoutParams(whiteSeparatorParams);


            separatorFrameLayout.addView(whiteSeparatorFrameLayout);
            separatorFrameLayout.getLayoutParams().width = 0;
            separatorsLinearLayout.addView(separatorFrameLayout);
        }

    }

    private boolean isIntervalAvailable(int index) {

        for (int j = 0; j < mAvailableIntervals.length; j++) {
            if (findInInterval(index, mAvailableIntervals[j]) == true) {
                return true;
            }
        }

        return false;
    }

    private boolean findInInterval(int time, String availabeInterval) {
        String[] splits = availabeInterval.split("-");
        if (splits.length == 2) {
            String[] startHourMinute = splits[0].trim().split(":");
            int startIndex = ((Integer.parseInt(startHourMinute[0]) - mStartRange) * 4) + (Integer.parseInt(startHourMinute[1]) / 60) * 4;
            String[] endHourMinute = splits[1].trim().split(":");
            int endIndex = ((Integer.parseInt(endHourMinute[0]) - mStartRange) * 4) + (Integer.parseInt(endHourMinute[1]) / 60) * 4;
            if (time >= startIndex && time < endIndex) {
                return true;
            }
        }
        return false;
    }
}

package com.bassem.roombooking.controls;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bassem.roombooking.R;

/**
 * Created by Bassem Samy on 2/4/2017.
 */

public class RoomAvailabilityBar extends LinearLayout {
    int mStartRange = 7;
    int mEndRange = 17;
    int mSubInterval = 1;
    Context mContext;
    FrameLayout mTopContainerFrameLayout;
    LinearLayout mBarsLinearLayout;
    LinearLayout mSeparatorsLinearLayout;
    LinearLayout mHoursLinearLayout;


    public RoomAvailabilityBar(Context context, AttributeSet attr) {
        super(context, attr);
        mContext = context;
        initializeMainLayouts();
        //initializeParentLinearLayout();

        this.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
    }

    private void initializeMainLayouts() {
        mTopContainerFrameLayout = new FrameLayout(mContext);
        mTopContainerFrameLayout.setLayoutParams(new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mBarsLinearLayout = new LinearLayout(mContext);
        mBarsLinearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mBarsLinearLayout.setOrientation(HORIZONTAL);
        mSeparatorsLinearLayout = new LinearLayout(mContext);
        mSeparatorsLinearLayout.setLayoutParams(
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mSeparatorsLinearLayout.setOrientation(HORIZONTAL);
        mHoursLinearLayout = new LinearLayout(mContext);
        mHoursLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mHoursLinearLayout.setOrientation(HORIZONTAL);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        super.onLayout(b, i, i1, i2, i3);
    }

    public RoomAvailabilityBar(Context context, int startRange, int endRange, int subInterval) {
        super(context);
        mStartRange = startRange;
        mEndRange = endRange;
        mSubInterval = subInterval;
        mContext = context;
        //initializeParentLinearLayout();
        initializeMainLayouts();
    }

    public void setProperties(int startRange, int endRange, int subInterval) {
        mStartRange = startRange;
        mEndRange = endRange;
        mSubInterval = subInterval;

        redrawItems();
    }

    void redrawItems() {
        LinearLayout.LayoutParams hoursTextViewLayoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        hoursTextViewLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        for (int i = 0; i < (mEndRange - mStartRange) * mSubInterval; i++) {
            FrameLayout frm = new FrameLayout(mContext);
            frm.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
            if (i % 2 == 0) {
                frm.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorBlue));
            } else {
                frm.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorLemonGreen));
            }

            mBarsLinearLayout.addView(frm);
        }
        for (int i = 0; i < mEndRange - mStartRange; i++) {
            TextView tv = new TextView(mContext);
            tv.setLayoutParams(hoursTextViewLayoutParams);
            tv.setText(Integer.toString(mStartRange + i));
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlack));
        }
//      //  mTopContainerFrameLayout.addView(mBarsLinearLayout);
//    //    mTopContainerFrameLayout.addView(mSeparatorsLinearLayout);
//        mTopContainerFrameLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorGreen));
//        //this.addView(mTopContainerFrameLayout);
//        mHoursLinearLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorOrange));
//        //this.addView(mHoursLinearLayout);
//        this.addView(mBarsLinearLayout);

        mTopContainerFrameLayout.removeAllViews();
        mTopContainerFrameLayout.addView(mBarsLinearLayout);
        this.removeAllViews();
        mTopContainerFrameLayout.getLayoutParams().height = this.getHeight() / 2;
        this.addView(mTopContainerFrameLayout);
    }

    private void initializeParentLinearLayout() {
        mBarsLinearLayout = new LinearLayout(mContext);

        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBarsLinearLayout.setLayoutParams(params);
        mBarsLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mBarsLinearLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorFuscia));
    }

    ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            redrawItems();
            RoomAvailabilityBar.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    };
}

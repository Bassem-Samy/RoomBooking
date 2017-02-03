package com.bassem.roombooking.roomslisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bassem.roombooking.R;
import com.bassem.roombooking.models.Room;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Mina Samy on 2/3/2017.
 */

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    private Context mContext;
    ArrayList<Room> mDataset;
    ArrayList<Room> mFilteredDataset;
    View.OnClickListener mOnItemClickListener;

    public RoomsAdapter(Context context, View.OnClickListener onItemClickListener, ArrayList<Room> rooms) {
        this.mContext = context;
        mOnItemClickListener = onItemClickListener;
        this.mDataset = rooms;
        this.mFilteredDataset = rooms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        v.setOnClickListener(mOnItemClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(mFilteredDataset.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (mFilteredDataset != null) {
            return mFilteredDataset.size();
        }
        return 0;
    }

    public Room getItemByPosition(int position) {
        return mFilteredDataset.get(position);
    }

    public void setDataSet(ArrayList<Room> dataSet) {
        this.mDataset = dataSet;
        this.mFilteredDataset = dataSet;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.txt_room_name);

        }
    }
}

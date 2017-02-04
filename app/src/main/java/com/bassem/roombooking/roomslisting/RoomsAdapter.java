package com.bassem.roombooking.roomslisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bassem.roombooking.R;
import com.bassem.roombooking.controls.RoomAvailabilityBar;
import com.bassem.roombooking.controls.RoomAvailabilityDisplayBar;
import com.bassem.roombooking.models.Room;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Bassem Samy on 2/3/2017.
 */

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {
    private Context mContext;
    ArrayList<Room> mDataset;
    ArrayList<Room> mFilteredDataset;
    View.OnClickListener mOnItemClickListener;
    String andMoreText;
    StringBuilder stringBuilder;
    static final String EQUIPMENT_SEPARATOR = ", ";

    public RoomsAdapter(Context context, View.OnClickListener onItemClickListener, ArrayList<Room> rooms) {
        this.mContext = context;
        mOnItemClickListener = onItemClickListener;
        this.mDataset = rooms;
        this.mFilteredDataset = rooms;
        andMoreText = mContext.getString(R.string.and_more);
        stringBuilder = new StringBuilder();
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
        holder.locationTextView.setText(mFilteredDataset.get(position).getLocation());
        holder.capacityTextView.setText(mFilteredDataset.get(position).getCapacity());
        holder.equipmentsTextView.setText(populateCapacity(mFilteredDataset.get(position).getEquipment()));
        holder.availabilityBar.setHelloText(Integer.toString(position));
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

    private String populateCapacity(String[] equipment) {
        if (equipment != null) {
            stringBuilder.setLength(0);
            if (equipment.length > 0) {
                stringBuilder.append(equipment[0]);
            }
            if (equipment.length > 1) {
                stringBuilder.append(EQUIPMENT_SEPARATOR).append(equipment[1]);
            }
            if (equipment.length > 2) {
                stringBuilder.append(andMoreText);
            }
            return stringBuilder.toString();
        }
        return "";
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;
        TextView capacityTextView;
        TextView equipmentsTextView;
        RoomAvailabilityDisplayBar availabilityBar;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.txt_room_name);
            locationTextView = (TextView) itemView.findViewById(R.id.txt_room_location);
            capacityTextView = (TextView) itemView.findViewById(R.id.txt_room_capacity);
            equipmentsTextView = (TextView) itemView.findViewById(R.id.txt_room_equipments);
            availabilityBar = (RoomAvailabilityDisplayBar) itemView.findViewById(R.id.room_availability_display_bar);
        }
    }
}

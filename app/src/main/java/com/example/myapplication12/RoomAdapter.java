package com.example.myapplication12;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
public class RoomAdapter extends ArrayAdapter<RoomModel> {
    public RoomAdapter(@NonNull Context context, ArrayList<RoomModel> roomModelList) {
        super(context, 0, roomModelList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.room_list, parent, false);
        }
        RoomModel roomModel = getItem(position);

        if (roomModel != null) {
            TextView roomNoTxt = listItemView.findViewById(R.id.roomNoTxt);
            TextView roomTypeTxt = listItemView.findViewById(R.id.roomTypeTxt);
            TextView noOfBedsTxt = listItemView.findViewById(R.id.noOfBedsTxt);
            TextView facility1Txt = listItemView.findViewById(R.id.facility1Txt);
            TextView facility2Txt = listItemView.findViewById(R.id.facility2Txt);

            roomNoTxt.setText(roomModel.getRoomNo());
            roomTypeTxt.setText(roomModel.getRoomType());
            noOfBedsTxt.setText(roomModel.getNoofBeds());
            facility1Txt.setText(roomModel.getFacility1());
            facility2Txt.setText(roomModel.getFacility2());

            listItemView.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), RoomBookActivity.class);
                intent.putExtra("RoomNo", roomModel.getRoomNo());
                intent.putExtra("RoomType", roomModel.getRoomType());
                intent.putExtra("NoofBeds", roomModel.getNoofBeds());
                intent.putExtra("Facility1", roomModel.getFacility1());
                intent.putExtra("Facility2", roomModel.getFacility2());
                getContext().startActivity(intent);
            });
        }
        return listItemView;
    }
}
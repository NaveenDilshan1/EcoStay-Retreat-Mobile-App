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

public class ToursAdapter extends ArrayAdapter<ToursModel> {
    public ToursAdapter(@NonNull Context context, ArrayList<ToursModel> toursModelList) {
        super(context, 0, toursModelList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.tours_list, parent, false);
        }
        ToursModel toursModel = getItem(position);

        if (toursModel != null) {
            TextView tourNameTxt = listItemView.findViewById(R.id.tourNameTxt);
            TextView locationTxt = listItemView.findViewById(R.id.locationTxt);
            TextView durationTxt = listItemView.findViewById(R.id.durationTxt);
            TextView guideTxt = listItemView.findViewById(R.id.guideTxt);
            TextView focusTxt = listItemView.findViewById(R.id.focusTxt);

            tourNameTxt.setText(toursModel.getTourName());
            locationTxt.setText(toursModel.getLocation());
            durationTxt.setText(toursModel.getDuration());
            guideTxt.setText(toursModel.getGuide());
            focusTxt.setText(toursModel.getFocus());

            listItemView.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), ToursBookActivity.class);
                intent.putExtra("TourName", toursModel.getTourName());
                intent.putExtra("Location", toursModel.getLocation());
                intent.putExtra("Duration", toursModel.getDuration());
                intent.putExtra("Guide", toursModel.getGuide());
                intent.putExtra("Focus", toursModel.getFocus());
                getContext().startActivity(intent);
            });
        }
        return listItemView;
    }
}
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

public class BirdsAdapter extends ArrayAdapter<BirdsModel> {
    public BirdsAdapter(@NonNull Context context, ArrayList<BirdsModel> birdsModelList) {
        super(context, 0, birdsModelList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.birds_list, parent, false);
        }
        BirdsModel birdsModel = getItem(position);

        if (birdsModel != null) {

            TextView facilitiesTxt = listItemView.findViewById(R.id.facilitiesTxt);
            TextView timeTxt = listItemView.findViewById(R.id.timeTxt);
            TextView guideTxt = listItemView.findViewById(R.id.guideTxt);
            TextView locationTxt = listItemView.findViewById(R.id.locationTxt);


            facilitiesTxt.setText(birdsModel.getFacilities());
            timeTxt.setText(birdsModel.getTime());
            guideTxt.setText(birdsModel.getGuide());
            locationTxt.setText(birdsModel.getLocation());

            listItemView.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), BirdsBookActivity.class);

                intent.putExtra("Facilities", birdsModel.getFacilities());
                intent.putExtra("Time", birdsModel.getTime());
                intent.putExtra("Guide", birdsModel.getGuide());
                intent.putExtra("Location", birdsModel.getLocation());
                getContext().startActivity(intent);
            });
        }
        return listItemView;
    }
}
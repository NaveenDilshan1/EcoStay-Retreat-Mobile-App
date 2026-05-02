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

public class HikisAdapter extends ArrayAdapter<HikisModel> {
    public HikisAdapter(@NonNull Context context, ArrayList<HikisModel> hikisModelList) {
        super(context, 0, hikisModelList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hikis_list, parent, false);
        }
        HikisModel hikisModel = getItem(position);

        if (hikisModel != null) {
            TextView hikeNameTxt = listItemView.findViewById(R.id.hikeNameTxt);
            TextView difficultyTxt = listItemView.findViewById(R.id.difficultyTxt);
            TextView durationTxt = listItemView.findViewById(R.id.durationTxt);
            TextView facilitiesTxt = listItemView.findViewById(R.id.facilitiesTxt);


            hikeNameTxt.setText(hikisModel.getHikeName());
            difficultyTxt.setText(hikisModel.getDifficulty());
            durationTxt.setText(hikisModel.getDuration());
            facilitiesTxt.setText(hikisModel.getFacilities());


            listItemView.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), HikisBookActivity.class);
                intent.putExtra("HikeName", hikisModel.getHikeName());
                intent.putExtra("Difficulty", hikisModel.getDifficulty());
                intent.putExtra("Duration", hikisModel.getDuration());
                intent.putExtra("Facilities", hikisModel.getFacilities());

                getContext().startActivity(intent);
            });
        }
        return listItemView;
    }
}

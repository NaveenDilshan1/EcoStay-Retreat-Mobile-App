package com.example.myapplication12;

import static com.example.myapplication12.R.id.mealsChk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class AddToursActivity extends AppCompatActivity {

    EditText tourNameTxt, durationTxt;
    Spinner difficultySpn;
    CheckBox transportChk, mealsChk, guideChk;
    Button addTourBtn, cancelTourBtn;

    String selectedDifficulty = "";
    FirebaseFirestore fs;
    private String[] difficulties = {"Easy", "Moderate", "Challenging"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tours);

        tourNameTxt = findViewById(R.id.tourNameTxt);
        durationTxt = findViewById(R.id.durationTxt);
        difficultySpn = findViewById(R.id.difficultySpn);
        transportChk = findViewById(R.id.transportChk);
        mealsChk = findViewById(R.id.mealsChk);
        guideChk = findViewById(R.id.guideChk);
        addTourBtn = findViewById(R.id.addTourBtn);
        cancelTourBtn = findViewById(R.id.cancelTourBtn);

        fs = FirebaseFirestore.getInstance();

        // Spinner setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, difficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpn.setAdapter(adapter);

        difficultySpn.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectedDifficulty = difficulties[position];
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                selectedDifficulty = "";
            }
        });

        // Add button
        addTourBtn.setOnClickListener(v -> {
            String tourName = tourNameTxt.getText().toString().trim();
            String duration = durationTxt.getText().toString().trim();

            StringBuilder facilities = new StringBuilder();
            if (transportChk.isChecked()) facilities.append("[Transport] ");
            if (mealsChk.isChecked()) facilities.append("[Meals] ");
            if (guideChk.isChecked()) facilities.append("[Guide] ");

            HashMap<String, String> tourData = new HashMap<>();
            tourData.put("TourName", tourName);
            tourData.put("Duration", duration);
            tourData.put("Difficulty", selectedDifficulty);
            tourData.put("Facilities", facilities.toString().trim());

            DocumentReference docRef = fs.collection("EcoTours")
                    .document(tourName);
            docRef.set(tourData, SetOptions.merge());
        });

        // Cancel button
        cancelTourBtn.setOnClickListener(v -> {
            tourNameTxt.setText("");
            durationTxt.setText("");
            difficultySpn.setSelection(0);
            transportChk.setChecked(false);
            mealsChk.setChecked(false);
            guideChk.setChecked(false);
        });
    }
}

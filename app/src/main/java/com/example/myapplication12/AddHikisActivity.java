package com.example.myapplication12;

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

public class AddHikisActivity extends AppCompatActivity {

    EditText hikeNameTxt, durationTxt;
    Spinner difficultySpn;
    CheckBox guideChk, mealsChk, firstAidChk;
    Button addHikeBtn, cancelHikeBtn;

    String selectedDifficulty = "";

    FirebaseFirestore fs;
    private String[] difficulties = {"Easy", "Moderate", "Hard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hikis);

        // Initialize views
        hikeNameTxt = findViewById(R.id.hikeNameTxt);
        durationTxt = findViewById(R.id.durationTxt);
        difficultySpn = findViewById(R.id.difficultySpn);
        guideChk = findViewById(R.id.guideChk);
        mealsChk = findViewById(R.id.mealsChk);
        firstAidChk = findViewById(R.id.firstAidChk);
        addHikeBtn = findViewById(R.id.addHikeBtn);
        cancelHikeBtn = findViewById(R.id.cancelHikeBtn);

        fs = FirebaseFirestore.getInstance();

        // Spinner data (Difficulty levels)
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

        // Add button -> Save to Firestore
        addHikeBtn.setOnClickListener(v -> {
            String hikeName = hikeNameTxt.getText().toString().trim();
            String duration = durationTxt.getText().toString().trim();

            // Facilities
            StringBuilder facilities = new StringBuilder();
            if (guideChk.isChecked()) facilities.append("[Guide] ");
            if (mealsChk.isChecked()) facilities.append("[Meals] ");
            if (firstAidChk.isChecked()) facilities.append("[First Aid] ");

            // HashMap to save in Firestore
            HashMap<String, String> hikeData = new HashMap<>();
            hikeData.put("HikeName", hikeName);
            hikeData.put("Duration", duration);
            hikeData.put("Difficulty", selectedDifficulty);
            hikeData.put("Facilities", facilities.toString().trim());

            // Save data (document ID = hike name)
            DocumentReference docRef = fs.collection("Hikis")
                    .document(hikeName);
            docRef.set(hikeData, SetOptions.merge());
        });

        // Cancel button -> Clear all fields
        cancelHikeBtn.setOnClickListener(v -> {
            hikeNameTxt.setText("");
            durationTxt.setText("");
            difficultySpn.setSelection(0);
            guideChk.setChecked(false);
            mealsChk.setChecked(false);
            firstAidChk.setChecked(false);
        });
    }
}

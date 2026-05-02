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

public class AddBirdActivity extends AppCompatActivity {

    EditText birdLocationTxt, timeTxt;
    Spinner guideLevelSpn;
    CheckBox binocularChk, cameraChk, snacksChk;
    Button addBirdBtn, cancelBirdBtn;

    String selectedGuideLevel = "";
    FirebaseFirestore fs;
    private String[] guideLevels = {"Basic Guide", "Intermediate Guide", "Expert Guide"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bird);

        birdLocationTxt = findViewById(R.id.birdLocationTxt);
        timeTxt = findViewById(R.id.timeTxt);
        guideLevelSpn = findViewById(R.id.guideLevelSpn);
        binocularChk = findViewById(R.id.binocularsChk);
        cameraChk = findViewById(R.id.cameraChk);
        snacksChk = findViewById(R.id.snacksChk);
        addBirdBtn = findViewById(R.id.addBirdBtn);
        cancelBirdBtn = findViewById(R.id.cancelBirdBtn);

        fs = FirebaseFirestore.getInstance();

        // Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, guideLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        guideLevelSpn.setAdapter(adapter);

        guideLevelSpn.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectedGuideLevel = guideLevels[position];
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                selectedGuideLevel = "";
            }
        });

        // Add button
        addBirdBtn.setOnClickListener(v -> {
            String location = birdLocationTxt.getText().toString().trim();
            String time = timeTxt.getText().toString().trim();

            StringBuilder facilities = new StringBuilder();
            if (binocularChk.isChecked()) facilities.append("[Binoculars] ");
            if (cameraChk.isChecked()) facilities.append("[Camera] ");
            if (snacksChk.isChecked()) facilities.append("[Snacks] ");

            HashMap<String, String> birdData = new HashMap<>();
            birdData.put("Location", location);
            birdData.put("Time", time);
            birdData.put("GuideLevel", selectedGuideLevel);
            birdData.put("Facilities", facilities.toString().trim());

            DocumentReference docRef = fs.collection("BirdWatching")
                    .document(location);
            docRef.set(birdData, SetOptions.merge());
        });

        // Cancel button
        cancelBirdBtn.setOnClickListener(v -> {
            birdLocationTxt.setText("");
            timeTxt.setText("");
            guideLevelSpn.setSelection(0);
            binocularChk.setChecked(false);
            cameraChk.setChecked(false);
            snacksChk.setChecked(false);
        });
    }
}

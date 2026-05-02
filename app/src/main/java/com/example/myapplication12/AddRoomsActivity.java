package com.example.myapplication12;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class AddRoomsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText roomno, noofbeds;
    RadioGroup facility1Group;
    CheckBox wifi, tv, hotwater;
    Button add, cancel;
    String selectedRoom = "", selectedFacility1 = "", selectedFacility2 = "";

    FirebaseFirestore fs;
    private String[] roomtypes = {"Single Room", "Double Room", "Connected Room", "Family Room", "Deluxe Room"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrooms);

        roomno = findViewById(R.id.roomNotxt);
        noofbeds = findViewById(R.id.noofbedstxt);
        add = findViewById(R.id.addbtn);
        cancel = findViewById(R.id.cancelbtn);
        facility1Group = findViewById(R.id.facility1grp);
        wifi = findViewById(R.id.wifichk);
        tv = findViewById(R.id.tvchk);
        hotwater = findViewById(R.id.hotwaterchk);

        fs = FirebaseFirestore.getInstance();

        // Spinner setup
        Spinner spin = findViewById(R.id.roomtypespn);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomtypes);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);

        // Facility 1 (radio group)
        facility1Group.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            selectedFacility1 = radioButton.getText().toString();
        });

        // Add button click
        add.setOnClickListener(v -> {
            selectedFacility2 = ""; // reset before checking
            if (wifi.isChecked())
                selectedFacility2 += "[Wifi] ";
            if (tv.isChecked())
                selectedFacility2 += "[TV] ";
            if (hotwater.isChecked())
                selectedFacility2 += "[Hot Water]";

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("RoomNo", roomno.getText().toString());
            hashMap.put("RoomType", selectedRoom);
            hashMap.put("Facility1", selectedFacility1);
            hashMap.put("Facility2", selectedFacility2.trim());
            hashMap.put("NoofBeds", noofbeds.getText().toString());

            DocumentReference docRef = fs.collection("Rooms")
                    .document(roomno.getText().toString());
            docRef.set(hashMap, SetOptions.merge());
        });

        // Cancel button just clears fields (optional)
        cancel.setOnClickListener(v -> {
            roomno.setText("");
            noofbeds.setText("");
            facility1Group.clearCheck();
            wifi.setChecked(false);
            tv.setChecked(false);
            hotwater.setChecked(false);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedRoom = roomtypes[position].toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



















package com.example.myapplication12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerHomeActivity extends AppCompatActivity {

    Button addRoomBtn, addHikisBtn, addToursBtn, addBirdBtn, addLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

        // Initialize buttons
        addRoomBtn = findViewById(R.id.addroombtn);
        addHikisBtn = findViewById(R.id.addhikisbtn);
        addToursBtn = findViewById(R.id.addtoursbtn);
        addBirdBtn = findViewById(R.id.addbirdbtn);
        addLogoutBtn = findViewById(R.id.addlogoutbtn);

        // Add Room
        addRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, AddRoomsActivity.class);
                startActivity(intent);
            }
        });

        // Add Hikes
        addHikisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, AddHikisActivity.class);
                startActivity(intent);
            }
        });

        // Add Tours
        addToursBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this,AddToursActivity.class);
                startActivity(intent);
            }
        });

        // Add Bird Watching
        addBirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, AddBirdActivity.class);
                startActivity(intent);
            }
        });

        // Logout
        addLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to login activity
                Intent intent = new Intent(ManagerHomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close Manager Home
            }
        });
    }
}
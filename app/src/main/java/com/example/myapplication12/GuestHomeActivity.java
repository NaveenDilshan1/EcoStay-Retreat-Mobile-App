package com.example.myapplication12;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication12.BirdsBookActivity;
import com.example.myapplication12.HikisBookActivity;
import com.example.myapplication12.MainActivity;
import com.example.myapplication12.R;
import com.example.myapplication12.RoomBookActivity;
import com.example.myapplication12.ToursBookActivity;

public class GuestHomeActivity extends AppCompatActivity {
    Button bookRoomBtn, bookHikisBtn, bookToursBtn, bookBirdBtn, LogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);

        bookRoomBtn = findViewById(R.id.bookroombtn);
        bookHikisBtn = findViewById(R.id.bookhikisbtn);
        bookToursBtn = findViewById(R.id.booktoursbtn);
        bookBirdBtn = findViewById(R.id.bookbirdbtn);
        LogoutBtn = findViewById(R.id.logoutbtn);

        bookRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, RoomBookActivity.class);
                startActivity(intent);
            }
        });
        bookHikisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, HikisBookActivity.class);
                startActivity(intent);
            }
        });
        bookToursBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, ToursBookActivity.class);
                startActivity(intent);
            }
        });
        bookBirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestHomeActivity.this, BirdsBookActivity.class);
                startActivity(intent);
            }
        });
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to login activity
                Intent intent = new Intent(GuestHomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close Manager Home
            }
        });
    }
}
package com.example.myapplication12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity3 extends AppCompatActivity {


    EditText email, password;

    Button register, login, cancel;

    FirebaseFirestore fs;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);


        email = findViewById(R.id.emailtxt);
        password = findViewById(R.id.passwordtxt);
        register = findViewById(R.id.registerbtn);
        login = findViewById(R.id.loginbtn);
        cancel = findViewById(R.id.cancelbtn);

        fs = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")) {
                    Toast.makeText(MainActivity3.this, "Enter your Email", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity3.this, "Enter your Password", Toast.LENGTH_LONG).show();
                } else {
                    loginuser();
                }
            }
        });

    }


    private void loginuser() {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (email.getText().toString().equals("manager@gmail.com")) {
                                Intent i = new Intent(MainActivity3.this, ManagerHomeActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(MainActivity3.this, GuestHomeActivity.class);
                                startActivity(i);
                                finish();
                            }


                        } else {
                            Toast.makeText(MainActivity3.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}







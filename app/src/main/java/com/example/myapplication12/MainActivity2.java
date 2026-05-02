package com.example.myapplication12;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private EditText email, password, cpassword;
    private Button register, login, cancel;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Initialize UI components
        email = findViewById(R.id.emailtxt);
        password = findViewById(R.id.passwordtxt);
        cpassword = findViewById(R.id.cpasswordtxt);
        register = findViewById(R.id.registerbtn);
        login = findViewById(R.id.loginbtn);
        cancel = findViewById(R.id.cancelbtn);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Register button listener
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                String confirmPasswordInput = cpassword.getText().toString().trim();

                if (TextUtils.isEmpty(emailInput)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(passwordInput)) {
                    password.setError("Password is required");
                    return;
                }

                if (TextUtils.isEmpty(confirmPasswordInput)) {
                    cpassword.setError("Please confirm your password");
                    return;
                }

                if (!passwordInput.equals(confirmPasswordInput)) {
                    cpassword.setError("Passwords do not match");
                    return;
                }

                // Create user
                createUser(emailInput, passwordInput);
            }
        });

        // Optional: Login button navigation
        login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class); // Assuming MainActivity is the login screen
            startActivity(intent);
            finish();
        });

        // Optional: Cancel button action
        cancel.setOnClickListener(v -> finish());
    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registration success
                            Toast.makeText(MainActivity2.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity2.this, MainActivity3.class); // Next activity
                            startActivity(i);
                            finish();
                        } else {
                            // Registration failed
                            Toast.makeText(MainActivity2.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
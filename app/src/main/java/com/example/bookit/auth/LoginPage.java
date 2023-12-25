package com.example.bookit.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookit.R;
import com.example.bookit.activity.AdminPanel;
import com.example.bookit.activity.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login_page);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void onRegisterActivity(View view) {
        Intent intent = new Intent(LoginPage.this, RegisterPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void onLoginActivity(View view) {
        validate();
    }

    void validate() {
        String uEmail = email.getText().toString().trim();
        String uPassword = password.getText().toString().trim();

        if (!TextUtils.isEmpty(uEmail) && !TextUtils.isEmpty(uPassword)) {
            signInWithEmailAndPassword(uEmail, uPassword);
        } else {
            if (TextUtils.isEmpty(uEmail)) {
                email.setError("Email is required");
                return;
            }
            if (TextUtils.isEmpty(uPassword)) {
                password.setError("Password is required");
            }
        }
    }

    void signInWithEmailAndPassword(String uEmail, String uPassword) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(uEmail, uPassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        if (email != null && email.equals("admin@gmail.com")) {
                            Toast.makeText(LoginPage.this, "Welcome Back", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginPage.this, AdminPanel.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginPage.this, "Welcome Back", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginPage.this, HomePageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginPage.this, RegisterPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}


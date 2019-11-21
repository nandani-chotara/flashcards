package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText firstName;
    EditText lastNmae;
    FirebaseAuth mAuth;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }

    public void onClickRegister(View view) {
        mAuth = FirebaseAuth.getInstance();

        if (username.length() > 0 && password.length() > 0) {
            mAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(task.getResult().getUser().getUid()).setValue("Empty");
                        Toast toast = Toast.makeText(getApplicationContext(), "Sign up complete!", Toast.LENGTH_SHORT);
                        toast.show();
                        signUp();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password. Please try again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
    }
    public void signUp() {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
    }
}
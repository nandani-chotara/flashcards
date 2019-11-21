package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    //login stuff
    ImageButton btnLogin;
    EditText username;
    EditText password;
    EditText firstName;
    EditText lastNmae;
    FirebaseAuth mAuth;

    //register stuff
    Button btnRegister;
    EditText rUsername;
    EditText rPassword;
    EditText rFirstName;
    EditText rLastNmae;

    Dialog myDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        btnRegister = findViewById(R.id.registerBtn);
        btnLogin = findViewById(R.id.loginBtn);

        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        username.setText(sharedPreferences.getString("username", null));

        myDialog = new Dialog(this);

    }

    public void onClickLogin(View view) {
        mAuth = FirebaseAuth.getInstance();

        if (username.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        logIn();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password. Please try again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        } else {
            Toast toast_2 = Toast.makeText(getApplicationContext(), "Please enter username and/or password", Toast.LENGTH_SHORT);
            toast_2.show();
        }

        String user = username.getText().toString();

        editor.putBoolean("savelogin", true);
        editor.putString("username", user);
        editor.commit();
    }

    public void logIn() {
        Intent myIntent = new Intent(this, DeckActivity.class);
        this.startActivity(myIntent);
    }

    public void signUp() {
        Intent myIntent = new Intent(this, RegisterActivity.class);
        this.startActivity(myIntent);
    }

    public void signUpButton(View view) {
        TextView txtClose;
        myDialog.setContentView(R.layout.activity_register);
        txtClose = myDialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public void onClickRegister(View view) {
        mAuth = FirebaseAuth.getInstance();

        if (rUsername.length() > 0 && rPassword.length() > 0) {
            mAuth.createUserWithEmailAndPassword(rUsername.getText().toString(), rPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(task.getResult().getUser().getUid()).setValue("Empty");
                        Toast toast = Toast.makeText(getApplicationContext(), "Sign up complete!", Toast.LENGTH_SHORT);
                        toast.show();
                        myDialog.dismiss();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid Email or Password. Please try again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity {
    private EditText emailET,passwordET,nameET;
    private Button signUpbBtn;
    SharedPreferences pref;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pref = getSharedPreferences("login_details",MODE_PRIVATE);
        db = new DatabaseHandler(this);
        emailET = findViewById(R.id.signup_email);
        passwordET = findViewById(R.id.signup_password);
        nameET = findViewById(R.id.signup_name);
        signUpbBtn = findViewById(R.id.signup_button);


    }
    public void registration(View v){
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String name = nameET.getText().toString();

        if (!email.isEmpty() && (!password.isEmpty())&&!name.isEmpty()) {
            if(db.checkUserEmailExist(email)==false) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("email", email);
                editor.putInt("status", 0);
                editor.commit();

                db.addUser(new User(name, email, password));

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                }
                else
                Toast.makeText(getApplicationContext(), "Email already exist.", Toast.LENGTH_LONG).show();
            }

        }
        public void login(View v){
            Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }
    }

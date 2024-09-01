package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;


public class activitylogin extends AppCompatActivity {
    private EditText emailET, passwordET;
    private TextView signupRedirectText;
    private TextView signUpTV;
    private Button loginBtn;
    private FirebaseAuth auth;
    TextView forgotPassword;
    //GoogleSignInButton googleBtn;
    Button googleBtn;
    GoogleSignInOptions gOptions;
    GoogleSignInClient gClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences pref = getSharedPreferences("login_details",MODE_PRIVATE);
        emailET = findViewById(R.id.login_email);
        passwordET=findViewById(R.id.login_password);
        loginBtn=findViewById(R.id.login_button);
        signUpTV=findViewById(R.id.signUpRedirectText);
        signUpTV.setEnabled(true);
        if(pref.contains("email")){
            signUpTV.setEnabled(false);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                String correctEmail=pref.getString("email","tip001");
                String correctPassword=pref.getString("password","tip001");
                String name = pref.getString("name",null);
                if (!email.isEmpty() && (!password.isEmpty())) {
                    if (email.equals(correctEmail) && (password.equals(correctPassword))) {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("status",1);
                        editor.commit();

                        Intent intent = new Intent(activitylogin.this, MainActivity.class);
                        intent.putExtra("username", name);
                        startActivity(intent);
                        finish();
                    }

                }


            }

        });
        }
        public void signUp(View v){
            Intent intent = new Intent(activitylogin.this, SignupActivity.class);
            startActivity(intent);
            finish();
        }
    }
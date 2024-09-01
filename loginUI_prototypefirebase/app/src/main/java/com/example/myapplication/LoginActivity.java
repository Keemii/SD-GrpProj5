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


public class LoginActivity extends AppCompatActivity {
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
        SharedPreferences pref = getSharedPreferences("login_details", MODE_PRIVATE);
        DatabaseHandler db = new DatabaseHandler(this);

        emailET = findViewById(R.id.login_email);
        passwordET = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);
        signUpTV = findViewById(R.id.signUpRedirectText);


            if ((pref.getInt("status", -1)) == 1) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }


            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailET.getText().toString();
                    String password = passwordET.getText().toString();

                    if (!email.isEmpty() && (!password.isEmpty())) {
                        if (db.checkCorrectPassword(email, password)==true) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("email", email);
                            editor.putInt("status", 1);
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                            startActivity(intent);
                            finish();
                        }

                    }


                }

            });
        }

    public void signUp(View v){

        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}

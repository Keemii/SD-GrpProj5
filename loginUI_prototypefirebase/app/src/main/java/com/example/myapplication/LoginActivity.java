package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.common.SignInButton;


public class LoginActivity extends AppCompatActivity {
    private EditText emailET, passwordET;
    private TextView signUpTV, forgotPassword;
    private Button loginBtn;
    private FirebaseAuth auth;
    private GoogleSignInOptions gOptions;
    private GoogleSignInClient gClient;
    private SignInButton googleBtn; // Updated to use the correct Google Sign-In button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Initialize Google Sign-In options
        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        gClient = GoogleSignIn.getClient(this, gOptions);

        // Initialize UI elements
        emailET = findViewById(R.id.login_email);
        passwordET = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);
        signUpTV = findViewById(R.id.signUpRedirectText);
        forgotPassword = findViewById(R.id.forgot_password);
        googleBtn = findViewById(R.id.google_sign_in_button);

        // Check if the user is already logged in
        SharedPreferences pref = getSharedPreferences("login_details", MODE_PRIVATE);
        if (pref.getInt("status", -1) == 1) {
            navigateToMainActivity();
        }

        // Set up click listeners
        loginBtn.setOnClickListener(v -> handleLogin());
        signUpTV.setOnClickListener(v -> signUp());
        forgotPassword.setOnClickListener(v -> sendPasswordResetEmail());
        googleBtn.setOnClickListener(v -> signInWithGoogle());
    }

    private void handleLogin() {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            // Local password check (replace with Firebase Auth if possible)
            DatabaseHandler db = new DatabaseHandler(this);
            if (db.checkCorrectPassword(email, password)) {
                SharedPreferences pref = getSharedPreferences("login_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("email", email);
                editor.putInt("status", 1);
                editor.apply();

                navigateToMainActivity();
            } else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUp() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    private void sendPasswordResetEmail() {
        String email = emailET.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "Reset link sent to your email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = gClient.getSignInIntent();
        startActivityForResult(signInIntent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e) {
                // Handle error
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                navigateToMainActivity();
            } else {
                // Handle error
                Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

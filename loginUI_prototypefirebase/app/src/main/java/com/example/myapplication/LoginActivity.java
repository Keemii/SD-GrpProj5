package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
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
    private SignInButton googleBtn;

    private static final String TAG = "LoginActivity";

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
        if (auth.getCurrentUser() != null) {
            Log.d(TAG, "User already logged in, navigating to MainActivity");
            navigateToMainActivity();
        }

        // Set up click listeners
        loginBtn.setOnClickListener(v -> handleLogin());
        signUpTV.setOnClickListener(v -> signUp());
        forgotPassword.setOnClickListener(v -> showForgotPasswordDialog());
        googleBtn.setOnClickListener(v -> signInWithGoogle());
    }

    private void handleLogin() {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            Log.d(TAG, "Attempting login with email: " + email);
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Login successful");
                            updateSharedPreferences(email);
                            navigateToMainActivity();
                        } else {
                            Log.e(TAG, "Login failed", task.getException());
                            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSharedPreferences(String email) {
        SharedPreferences pref = getSharedPreferences("login_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", email);
        editor.putInt("status", 1);
        editor.apply();
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

    private void showForgotPasswordDialog() {
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        final android.view.View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot, null);
        dialogBuilder.setView(dialogView);

        final EditText emailBox = dialogView.findViewById(R.id.emailBox);
        Button btnReset = dialogView.findViewById(R.id.btnReset);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        final androidx.appcompat.app.AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnReset.setOnClickListener(v -> {
            String email = emailBox.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(LoginActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Password reset link sent to your email", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnCancel.setOnClickListener(v -> alertDialog.dismiss());
    }

    private void signInWithGoogle() {
        Log.d(TAG, "Starting Google Sign-In");
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
                    Log.d(TAG, "Google Sign-In successful");
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e) {
                Log.e(TAG, "Google Sign-In failed", e);
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "Authenticating with Firebase using Google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "Firebase Authentication successful");
                updateSharedPreferences(acct.getEmail());
                navigateToMainActivity();
            } else {
                Log.e(TAG, "Firebase Authentication failed", task.getException());
                Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

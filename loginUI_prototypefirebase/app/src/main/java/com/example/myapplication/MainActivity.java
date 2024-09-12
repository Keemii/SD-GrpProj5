package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHandler db;
    private SharedPreferences pref;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ensure that any database initialization or operations happen after setContentView
        DatabaseHandler db = new DatabaseHandler(this);

        // Initialize SharedPreferences
        pref = getSharedPreferences("login_details", MODE_PRIVATE);

        // Check if user is logged in
        int loginStatus = pref.getInt("status", 0); // Default to 0 (logged out)
        Log.d(TAG, "Login status: " + loginStatus);

        if (loginStatus != 1) {
            Log.d(TAG, "User not logged in, redirecting to LoginActivity");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Retrieve email from SharedPreferences
        String email = pref.getString("email", null);
        if (email != null) {
            Log.d(TAG, "Retrieved email: " + email);
            // Check if user exists
            if (!db.checkUserEmailExists(email)) {
                Log.d(TAG, "User not found");
                TextView usernameTV = findViewById(R.id.userName);
                usernameTV.setText("User not found");
            } else {
                User user = db.getUser(email); // Get user by email
                TextView usernameTV = findViewById(R.id.userName);
                if (user != null) {
                    usernameTV.setText(user.getName());
                } else {
                    Log.d(TAG, "User not found");
                    usernameTV.setText("User not found");
                }
            }
        } else {
            Log.d(TAG, "No email found in SharedPreferences");
            TextView usernameTV = findViewById(R.id.userName);
            usernameTV.setText("No email found");
        }


        // Handle button to navigate to HomePage
        Button buttonToHomePage = findViewById(R.id.buttonToHomePage);
        buttonToHomePage.setOnClickListener(v -> {
            Log.d(TAG, "Navigating to HomePage");
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        });
    }

    // Logout function
    public void logout(View v) {
        Log.d(TAG, "Logging out");
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("status", 0);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

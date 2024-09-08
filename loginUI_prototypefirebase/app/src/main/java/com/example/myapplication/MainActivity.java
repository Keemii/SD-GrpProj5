package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        pref = getSharedPreferences("login_details",MODE_PRIVATE);
        User user = new User();
        user=db.getUser(pref.getString("email", null));

        TextView usernameTV = findViewById(R.id.userName);
        usernameTV.setText(user.getName());

        // Find the button by its ID
        Button buttonToHomePage = findViewById(R.id.buttonToHomePage);

        // Set an OnClickListener to handle the button click
        buttonToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to HomePageActivity
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                // Start the HomePageActivity
                startActivity(intent);
            }
        });

    }

    public void logout(View v) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("status",0);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @SuppressLint("CustomSplashScreen")
    public class SplashActivity extends AppCompatActivity {

        private static final int SPLASH_DURATION = 5000; // 5000 milliseconds or 5 seconds

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);  // Set the splash screen layout

            // Handler to delay the transition to the main activity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Start the main activity after the delay
                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent);
                    // Close the splash screen activity so the user can't go back to it
                    finish();
                }
            }, 5000);
        }
    }


}

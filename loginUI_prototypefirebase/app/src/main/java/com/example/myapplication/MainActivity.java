package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

    }

    public void logout(View v) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("status",0);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


}
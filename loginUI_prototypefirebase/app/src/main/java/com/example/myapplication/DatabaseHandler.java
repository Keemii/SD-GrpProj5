package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "labskill2DB";
    private static final String TABLE_NAME = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            cursor.close();
            return user;
        }
        return null;
    }

    User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + "=?", new String[]{email}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            cursor.close();
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PASSWORD, user.getPassword());
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Method to check if an email exists in the database
    public boolean checkUserEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
                        + " WHERE " + KEY_EMAIL + " =? ",
                new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkCorrectPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
                        + " WHERE " + KEY_EMAIL + " =? "
                        + " AND " + KEY_PASSWORD + " =? ",
                new String[]{email, password});
        boolean correct = cursor.getCount() > 0;
        cursor.close();
        return correct;
    }
}

package com.example.ppo_kr2.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ppo_kr2.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDbManager {
    private Context context;
    private UsersDbHelper usersDbHelper;
    private SQLiteDatabase db;

    public UsersDbManager(Context context) {
        this.context = context;
        usersDbHelper = new UsersDbHelper(context);
    }

    public void openDb() {
        db = usersDbHelper.getWritableDatabase();
    }

    public void insertToDb(String username, int score) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.COLUMN_NAME_USERNAME, username);
        cv.put(Constants.COLUMN_NAME_SCORE, score);
        db.insert(Constants.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public User checkDb(String username) {
        int id;
        Cursor cursor = db.query(Constants.TABLE_NAME,null,null,null,
                null, null, null);
        while (cursor.moveToNext()) {
            String thisUsername = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_USERNAME));
            if (username.equals(thisUsername)) {
                id = cursor.getInt(cursor.getColumnIndex(Constants._ID));
                int score = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_NAME_SCORE));
                return new User(id, username, score);
            }
        }
        cursor.close();
        return new User(-1, username, 0);
    }

    @SuppressLint("Range")
    public List<User> readFromDb() {
        List<User> result = new ArrayList<>();
        String selection = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + Constants.COLUMN_NAME_SCORE + " DESC;";
        Cursor cursor = db.rawQuery(selection, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(Constants._ID));
            String username = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_USERNAME));
            int score = cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_NAME_SCORE));
            User order = new User(id, username, score);
            result.add(order);
        }
        cursor.close();
        return result;
    }

    @SuppressLint("Range")
    public void updateDb(int id, String username, int score) {
        Cursor cursor = db.query(Constants.TABLE_NAME,null,null,null,
                null, null, null);
        while (cursor.moveToNext()) {
            String thisUsername = cursor.getString(cursor.getColumnIndex(Constants.COLUMN_NAME_USERNAME));
            if(username.equals(thisUsername)) {
                id = cursor.getInt(cursor.getColumnIndex(Constants._ID));
                ContentValues cv = new ContentValues();
                cv.put(Constants.COLUMN_NAME_SCORE, score);
                db.update(Constants.TABLE_NAME, cv, Constants._ID + "=" + id, null);
                break;
            }
        }
        cursor.close();
        if(id == -1) {
            insertToDb(username, score);
        }
    }

    public void closeDb() {
        usersDbHelper.close();
    }
}

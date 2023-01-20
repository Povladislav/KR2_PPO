package com.example.ppo_kr2.SQLite;

public class Constants {
    public static final String TABLE_NAME = "Users";
    public static final String _ID = "_id";
    public static final String COLUMN_NAME_USERNAME = "_username";
    public static final String COLUMN_NAME_SCORE = "_score";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Constants.TABLE_NAME + " (" +
                    Constants._ID + " INTEGER PRIMARY KEY," +
                    Constants.COLUMN_NAME_USERNAME + " TEXT," +
                    Constants.COLUMN_NAME_SCORE  + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Constants.TABLE_NAME;
}

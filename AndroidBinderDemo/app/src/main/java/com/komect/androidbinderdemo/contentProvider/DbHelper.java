package com.komect.androidbinderdemo.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xieyusheng on 2018/7/16.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";
    public static final String TABLE1_NAME = "test1";
    public static final String TABLE2_NAME = "test2";

    private static final int DB_VERSION = 1;

    private String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS " + TABLE1_NAME
            + "(id integer  primary key autoincrement,text varchar(60))";

    private String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS " + TABLE2_NAME
            + "(id integer  primary key autoincrement,text varchar(60))";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

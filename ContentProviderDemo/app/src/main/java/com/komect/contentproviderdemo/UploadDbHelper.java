package com.komect.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xieyusheng on 2018/6/21.
 */

public class UploadDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "uploadinfo.db";
    public static final String UPLOAD_TABLE_NAME = "upload";
    private static final int DB_VERSION = 1;
    public static final String SESSION_ID = "sessionId";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    private String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + UPLOAD_TABLE_NAME
            + "(id integer  primary key autoincrement," + SESSION_ID + " varchar(60)," +
            "" + LAT + " varchar(60)," + LON + " varchar(60))";

    public UploadDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

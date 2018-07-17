package com.komect.contentproviderdemo;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import static com.komect.contentproviderdemo.UploadDbHelper.LAT;
import static com.komect.contentproviderdemo.UploadDbHelper.LON;
import static com.komect.contentproviderdemo.UploadDbHelper.SESSION_ID;
import static com.komect.contentproviderdemo.UploadInfoProvider.UPLOAD_CONTENT_URI;

/**
 * Created by xieyusheng on 2018/6/21.
 */

public class UploadManager {

    private static final String TAG = "UploadManager";

    public static void insert(Context context) {
        Uri uri = UPLOAD_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(SESSION_ID, "1222312");
        values.put(LAT, "ada");
        values.put(LON, "ava");
        context.getContentResolver().insert(uri, values);
        query(context);
    }

    private static void update(Context context, String sessionId) {
        Uri uri1 = ContentUris.withAppendedId(UPLOAD_CONTENT_URI, 1);
        ContentValues value = new ContentValues();
        value.put(SESSION_ID, sessionId);
        context.getContentResolver().update(uri1, value, null, null);
        query(context);
    }

    private static void update(Context context, String lat, String lon) {
        Uri uri1 = ContentUris.withAppendedId(UPLOAD_CONTENT_URI, 1);
        ContentValues value = new ContentValues();
        value.put(LAT, lat);
        value.put(LON, lon);
        context.getContentResolver().update(uri1, value, null, null);
        query(context);
    }

    private static String[] query(Context context) {
        String[] result = new String[3];
        Cursor bookCursor = context.getContentResolver().query(UPLOAD_CONTENT_URI, new String[]{SESSION_ID, LAT, LON}, null, null, null);
        bookCursor.moveToFirst();
        while (bookCursor.moveToNext()) {
            Log.d(TAG, "query sess--" + bookCursor.getString(bookCursor.getColumnIndex(SESSION_ID)));
            Log.d(TAG, "query lat--" + bookCursor.getString(bookCursor.getColumnIndex(LAT)));
            Log.d(TAG, "query  long--" + bookCursor.getString(bookCursor.getColumnIndex(LON)));
            result[0] = bookCursor.getString(bookCursor.getColumnIndex(SESSION_ID));
            result[1] = bookCursor.getString(bookCursor.getColumnIndex(LAT));
            result[2] = bookCursor.getString(bookCursor.getColumnIndex(LON));
        }
        bookCursor.close();
        return result;
    }


    public static String getSessionId(Context context) {
        return query(context)[0];
    }

    public static void setSessionId(Context context, String sessionId) {
        update(context, sessionId);
    }

    public static void setLatLon(Context context, String lat, String lon) {
        update(context, lat, lon);
    }

    public static String getLat(Context context) {
        return query(context)[1];
    }

    public static String getLon(Context context) {
        return query(context)[2];
    }
}

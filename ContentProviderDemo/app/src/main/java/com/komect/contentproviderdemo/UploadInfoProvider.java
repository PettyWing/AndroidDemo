package com.komect.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.komect.contentproviderdemo.UploadDbHelper.LAT;
import static com.komect.contentproviderdemo.UploadDbHelper.LON;
import static com.komect.contentproviderdemo.UploadDbHelper.SESSION_ID;
import static com.komect.contentproviderdemo.UploadDbHelper.UPLOAD_TABLE_NAME;


/**
 * Created by xieyusheng on 2018/6/21.
 */

public class UploadInfoProvider extends ContentProvider {

    public static final String TAG = "UploadInfoProvider";
    private SQLiteDatabase mDb;
    private Context mContext;
    private String table;
    public static final String AUTHORITY = "com.komect.allinonesdk.upload.UploadInfoProvider";
    public static final Uri UPLOAD_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/upload");
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "upload", USER_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        table = UPLOAD_TABLE_NAME;
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new UploadDbHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + table);
        mDb.execSQL("insert into " + table + "(" + SESSION_ID + "," + LAT + "," + LON + ") values('1','1','1');");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        mDb.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}

package com.komect.androidbinderdemo.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by xieyusheng on 2018/7/16.
 */

public class TestProvider extends ContentProvider {

    private static final String TAG = "TestProvider";
    public static final String AUTHORITY = "com.komect.androidbinderdemo.contentProvider.TestProvider";

    public static final Uri TAB1_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tab1");
    public static final Uri TAB2_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/tab2");

    public static final int TAB1_URI_CODE = 1;
    public static final int TAB2_URI_CODE = 0;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "tab1", TAB1_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "tab2", TAB2_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new DbHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from " + DbHelper.TABLE1_NAME);
        mDb.execSQL("delete from " + DbHelper.TABLE2_NAME);
        mDb.execSQL("insert into test1 values(1,'Android');");
        mDb.execSQL("insert into test1 values(2,'IOS');");
        mDb.execSQL("insert into test2 values(3,'IOS');");
        mDb.execSQL("insert into test2 values(4,'IOS');");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport URI" + uri);
        }
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
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport URI" + uri);
        }
        mDb.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport URI" + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupport URI" + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (sUriMatcher.match(uri)) {
            case TAB1_URI_CODE:
                tableName = DbHelper.TABLE1_NAME;
                break;
            case TAB2_URI_CODE:
                tableName = DbHelper.TABLE2_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }
}

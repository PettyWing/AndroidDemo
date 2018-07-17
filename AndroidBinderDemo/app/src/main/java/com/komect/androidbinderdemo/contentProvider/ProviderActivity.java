package com.komect.androidbinderdemo.contentProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.komect.androidbinderdemo.R;

/**
 * Created by xieyusheng on 2018/7/16.
 */

public class ProviderActivity extends Activity {

    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testInsert();
    }

    private void testInsert() {
        Uri uri1 = TestProvider.TAB1_CONTENT_URI;

        Log.d(TAG, "testInsert: 插入数据");
        ContentValues values = new ContentValues();
        values.put("id", "3");
        values.put("text", "xxx");
        getContentResolver().insert(uri1, values);

        Log.d(TAG, "testInsert: 查询数据");
        Cursor cursor = getContentResolver().query(uri1, new String[]{"id", "text"}, null, null, null);
        while (cursor.moveToNext()) {
            Log.d(TAG, "testInsert: " + cursor.getString(cursor.getColumnIndex("text")));
        }
        cursor.close();
    }
}

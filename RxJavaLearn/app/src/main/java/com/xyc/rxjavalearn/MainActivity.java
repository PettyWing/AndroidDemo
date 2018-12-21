package com.xyc.rxjavalearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String temp = "231123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, SimpleRxTestActivity.class));
        if (savedInstanceState != null) {
            temp = savedInstanceState.getString("temp");
            Log.d(TAG, "onCreate: temp =  + temp");
        }


    }

    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState( saveInstanceState);
        String temp  = saveInstanceState.getString("temp");
        Log.d(TAG,"onRestoreInstanceState: temp = " + temp);

    }

    // 将数据保存到outState对象中, 该对象会在重建activity时传递给onCreate方法和onRestoreInstanceState方法
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putString("temp", temp);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}

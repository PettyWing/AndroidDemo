package com.komect.contentproviderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UploadManager.insert(this);
    }

    public void setSession(View view) {
        UploadManager.setSessionId(this, "12134");
    }

    public void getSessionId(View view) {
        Log.d("MainActivity", "getSessionId: " + UploadManager.getSessionId(this));
    }

    public void setLat(View view) {
        UploadManager.setLatLon(this, "adas", "asdasa");
    }

    public void getLat(View view) {
        Log.d("MainActivity", "getLat: " + UploadManager.getLat(this));
    }
}

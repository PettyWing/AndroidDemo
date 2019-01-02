package com.xyc.smalldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.wequick.small.Small;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGotoClick(View view){
        Small.openUri("main", this); // open bundles.main Launch Activity
    }
}

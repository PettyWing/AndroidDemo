package com.komect.androidfragmentswitchdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;

    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment1)
                .commit();
    }

    public void onSwitchClick1(View view) {
        if (current == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    ).replace(R.id.container, fragment2)
                    .addToBackStack(null)
                    .commit();
            current = 1;
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    ).replace(R.id.container, fragment1)
                    .addToBackStack(null)
                    .commit();
            current = 0;
        }
    }

    public void onSwitchClick2(View view) {
        if (current == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.flip_left_in,
                            R.anim.flip_left_out,
                            R.anim.flip_left_in,
                            R.anim.flip_right_out
                    ).replace(R.id.container, fragment2)
                    .addToBackStack(null)
                    .commit();
            current = 1;
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.flip_right_in,
                            R.anim.flip_left_out,
                            R.anim.flip_left_in,
                            R.anim.flip_right_out
                    ).replace(R.id.container, fragment1)
                    .addToBackStack(null)
                    .commit();
            current = 0;
        }
    }
}

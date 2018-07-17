package com.komect.androidradardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.komect.androidradardemo.bean.PieChartData;
import com.komect.androidradardemo.widget.LoadingView;
import com.komect.androidradardemo.widget.PieChartView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 初始化雷达图
     */
    private void initPieChartView(){
        List<PieChartData> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieChartData data = new PieChartData("项目" + i, i, mColors[i]);
            datas.add(data);
        }

        PieChartView pieChartView = (PieChartView) findViewById(R.id.pie_view);
        pieChartView.setPieChartDataList(datas);

    }

    /**
     * 初始化loading
     */
    private void initLoadingView(){
        loadingView = findViewById(R.id.loading);
    }

    private void initBessel(){

    }

    public void onStart(View view) {
        loadingView.startLoading();
    }

    public void onStop(View view) {
        loadingView.stopLoading();
    }
}

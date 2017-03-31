package com.example.typhus148.shade_graph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout surfaceView = (RelativeLayout)findViewById(R.id.graphView);
        BarChart graphView = new BarChart(this);
        surfaceView.addView(graphView);

        ArrayList<String> graphDate = new ArrayList<>();
        ArrayList<Float> graphUv = new ArrayList<>();

        // Fill out some fake values
        graphDate.add("12/21"); graphUv.add(1.0f);
        graphDate.add("12/22"); graphUv.add(132.0f);
        graphDate.add("12/23"); graphUv.add(80.0f);
        graphDate.add("12/24"); graphUv.add(0.0f);
        graphDate.add("12/25"); graphUv.add(32.0f);
        graphDate.add("12/26"); graphUv.add(74.0f);
        graphDate.add("12/27"); graphUv.add(90.0f);
        graphDate.add("12/28"); graphUv.add(162.0f);
        graphDate.add("12/29"); graphUv.add(62.0f);
        graphDate.add("12/30"); graphUv.add(50.0f);
        graphDate.add("12/31"); graphUv.add(22.0f);
        graphDate.add("1/1"); graphUv.add(158.0f);
        graphView.setChartData(graphDate, graphUv);
        graphView.setGraphMaximum(200.0f);
        graphView.showDangerZone(true, 90.0f);
        graphView.showDailyLimit(true, 43.0f);
        graphView.showSliderButton(true);
        graphView.drawChart();
    }
}

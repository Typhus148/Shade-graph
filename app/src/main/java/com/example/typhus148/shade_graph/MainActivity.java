package com.example.typhus148.shade_graph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout surfaceView = (RelativeLayout)findViewById(R.id.surfaceView);
        BarChart graphView = new BarChart(this);
        surfaceView.addView(graphView);
    }
}

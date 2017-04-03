package com.example.typhus148.shade_graph;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Set which app screen is currently displayed
    private String appScreen = "Dashboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (appScreen.equals("History")) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_layout_dashboardgraph);
        }

        RelativeLayout surfaceView = (RelativeLayout)findViewById(R.id.graphView);
        BarChart graphView = new BarChart(this);
        surfaceView.addView(graphView);


        ArrayList<String> graphDate = new ArrayList<>();
        ArrayList<Float> graphUv = new ArrayList<>();

        // Fill out some fake values
        graphDate.add("7 AM"); graphUv.add(1.0f);
        graphDate.add("12/22"); graphUv.add(132.0f);
        graphDate.add("12/23"); graphUv.add(80.0f);
        graphDate.add("12/24"); graphUv.add(0.0f);
        graphDate.add("11 AM"); graphUv.add(32.0f);
        graphDate.add("12/26"); graphUv.add(74.0f);
        graphDate.add("12/27"); graphUv.add(90.0f);
        graphDate.add("12/28"); graphUv.add(162.0f);
        graphDate.add("3 PM"); graphUv.add(62.0f);
        graphDate.add("12/30"); graphUv.add(50.0f);
        graphDate.add("12/31"); graphUv.add(22.0f);
        graphDate.add("1/1"); graphUv.add(158.0f);
        graphDate.add("7 PM"); graphUv.add(198.0f);

        // passes in the current app screen
        graphView.setAppScreen(appScreen);
        // passes in color for the graph bars
        int darkPurpleShade = ContextCompat.getColor(MainActivity.this, R.color.ShadeDarkPurple);
        graphView.setBarColor(darkPurpleShade);

        // passes in the dats and uv values for the graph
        graphView.setChartData(graphDate, graphUv);

        // passes in the maximum uv value for the graph
        graphView.setGraphMaximum(200.0f);

        // passes in whether to display the dangerZone and the uv value which it begins from
        graphView.showDangerZone(true, 90.0f);

        // passes in whether to display the daily limit and the uv value of the daily limit
        graphView.showDailyLimit(true, 43.0f);

        // passes in whether to display the slider button
        graphView.showSliderButton(false);

        // Calls the method which takes all previously passed in data and displays them
        graphView.drawChart();
    }
}

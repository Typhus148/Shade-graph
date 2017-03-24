package com.example.typhus148.shade_graph;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> graphDate = new ArrayList<>();
    List<Float> graphUv = new ArrayList<>();

    float graphMaximum = 160.0f;
    int numberOfBars = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fill out some fake values
        graphDate.add("12/21"); graphUv.add(5.0f);
        graphDate.add("12/22"); graphUv.add(132.0f);
        graphDate.add("12/23"); graphUv.add(80.0f);
        graphDate.add("12/24"); graphUv.add(0.0f);
        graphDate.add("12/25"); graphUv.add(32.0f);
        graphDate.add("12/26"); graphUv.add(74.0f);
        graphDate.add("12/27"); graphUv.add(90.0f);
        graphDate.add("12/28"); graphUv.add(122.0f);
        graphDate.add("12/29"); graphUv.add(62.0f);
        graphDate.add("12/30"); graphUv.add(44.0f);
        graphDate.add("12/31"); graphUv.add(22.0f);
        graphDate.add("1/1"); graphUv.add(160.0f);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Resources r = getResources();
        float px1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 168, r.getDisplayMetrics());
        float px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
        float dpWidth = displayMetrics.widthPixels;
        float x = dpWidth - (dpWidth * (float)0.13611);
        int screenWidth = (int)x;

        setContentView(R.layout.activity_main);
        RelativeLayout graphView = (RelativeLayout)findViewById(R.id.surfaceView);

        int leftMargin = screenWidth/(numberOfBars+1);
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());

        for (int i=0; i< graphDate.size(); i++) {
            float uvValue = graphUv.get(i);
            float barSize = uvValue/graphMaximum;
            int barHeight = (int)((barSize*px1)+px2);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, barHeight);

            if ((i-1)%3==0) {
                BarTick bar1 = new BarTick(MainActivity.this, (i==numberOfBars-1), graphDate.get(i));
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMarginStart(leftMargin);
                bar1.setLayoutParams(params);
                graphView.addView(bar1);
            } else {
                BarTick bar1 = new BarTick(MainActivity.this, (i == numberOfBars - 1), "");
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMarginStart(leftMargin);
                bar1.setLayoutParams(params);
                graphView.addView(bar1);
            }
            leftMargin += screenWidth / (numberOfBars);
        }

        TextView maxUV = (TextView)findViewById(R.id.maxExposure);
        maxUV.setText(String.format("%s", (int) graphMaximum));

        HorizantalLine line1 = new HorizantalLine(MainActivity.this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, height);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        line1.setLayoutParams(params);
        graphView.addView(line1);
    }
}

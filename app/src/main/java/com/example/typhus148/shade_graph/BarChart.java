package com.example.typhus148.shade_graph;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class BarChart extends RelativeLayout {
    private List<String> graphDate = new ArrayList<>();
    private List<Float> graphUv = new ArrayList<>();

    private float graphMaximum = 160.0f;
    private int numberOfBars = 12;
    private float px1, px2, px3;
    private Context context;

    public BarChart(Context context) {
        super(context);
        this.context = context;
        // Fill out some fake values
        graphDate.add("12/21"); graphUv.add(5.0f);
        graphDate.add("12/22"); graphUv.add(132.0f);
        graphDate.add("12/23"); graphUv.add(80.0f);
        graphDate.add("12/24"); graphUv.add(0.0f);
        graphDate.add("12/25"); graphUv.add(32.0f);
        graphDate.add("12/26"); graphUv.add(74.0f);
        graphDate.add("12/27"); graphUv.add(90.0f);
        graphDate.add("12/28"); graphUv.add(10000.0f);
        graphDate.add("12/29"); graphUv.add(62.0f);
        graphDate.add("12/30"); graphUv.add(44.0f);
        graphDate.add("12/31"); graphUv.add(22.0f);
        graphDate.add("1/1"); graphUv.add(158.0f);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Resources r = context.getResources();
        px1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 168, r.getDisplayMetrics()); // if uv value is greater than max uv use this
        px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics()); // accounts for rest of space not including the graph bar
        px3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,163, r.getDisplayMetrics()); // if uv value is not greater than max uv use this
        float dpWidth = displayMetrics.widthPixels;
        float x = dpWidth - (dpWidth * (float)0.13611);
        int screenWidth = (int)x;

        // Danger zone height is equal to the first value outside the 90th percentile - the total height of the danger zone
        /* 90th percentile of last 30 days
        * 3 highest days are the 90th percentile
        * the 4th highest or 4th highest value is the height to subtract from the danger zone height*/
        int fourthHighestHieght = (int)(((90.0f/graphMaximum)*px3)+px2); // will add some logic to get the bar that is the 4th highest
        int hashHeight = 163 - fourthHighestHieght;
        int hashHeightMax = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, hashHeight, r.getDisplayMetrics());
        int hashHeightMarginBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        // Bottom margin is always the same 1dp above the 30dp margin for the bars
        // since 1dp would be the minimum height of a bar possible
        generateDangerZone(this, (hashHeightMax+hashHeightMarginBottom));

        // Displays the uv graph bars
        int leftMargin = screenWidth/(numberOfBars+1);
        for (int i=0; i< graphDate.size(); i++) {
            generateGraph(this, graphUv.get(i), graphDate.get(i), i, leftMargin);
            leftMargin += screenWidth / (numberOfBars);
        }

        // Sets the values to display on the graph for min max
        generateUvMinMax(this);

        // Displays the top and bottom lines of the graph
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 198, r.getDisplayMetrics());
        generateBarLines(this, height);
    }


    private void generateUvMinMax(RelativeLayout graphView) {
        UvMinMax uvMinMax = new UvMinMax(this.context, (int) graphMaximum);
        graphView.addView(uvMinMax);
    }

    private void generateGraph(RelativeLayout graphView, float uvValue, String uvDate, int i, int leftMargin) {
        float barSize = uvValue/graphMaximum;
        int barHeight;
        boolean overSized;
        if (uvValue>graphMaximum) {
            barHeight = (int)((barSize*px1)+px2);
            overSized =  true;
        } else {
            barHeight = (int)((barSize*px3)+px2);
            overSized = false;
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, barHeight);
        if ((i-1)%3==0) {
            BarTick bar1 = new BarTick(this.context, (i==numberOfBars-1), uvDate, overSized);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.setMarginStart(leftMargin);
            bar1.setLayoutParams(params);
            graphView.addView(bar1);
        } else {
            BarTick bar1 = new BarTick(this.context, (i == numberOfBars - 1), "", overSized);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.setMarginStart(leftMargin);
            bar1.setLayoutParams(params);
            graphView.addView(bar1);
        }
    }

    private void generateBarLines(RelativeLayout graphView, int height) {
        HorizantalLine line1 = new HorizantalLine(this.context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, height);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        line1.setLayoutParams(params);
        graphView.addView(line1);
    }

    private void generateDangerZone(RelativeLayout graphView, int height) {
        DangerZone hashBackground = new DangerZone(this.context, height);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, height);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        hashBackground.setLayoutParams(params);
        graphView.addView(hashBackground);
    }

    // sets graph maximum uv value
    public void setGraphMaximum(float graphMaximum) { }

    // Displays the line that shows the daily limit AKA threshold limit
    public void showDailyLimit(boolean show, float atExposureLevel) { }

    // Takes a color to set the bars to
    public void setBarColor(Color barColor) { }

    // Displays the hash background
    public void showDangerZone(boolean show, float dangerZoneLevel) { }

    // gets the uv values to display the graph bars
    public void setChartData(List<String> xValues, List<Float> yValues) { }

    // Displays the circle button on the daily limit line/bar
    public void showSliderButton(boolean show) { }

    // Deletes current displayed graph
    public void eraseChart() { }

    // Displays the graph
    public void drawChart() { }
}

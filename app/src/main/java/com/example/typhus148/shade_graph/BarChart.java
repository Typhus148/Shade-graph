package com.example.typhus148.shade_graph;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class BarChart extends RelativeLayout {
    private List<String> graphDate = new ArrayList<>();
    private List<Float> graphUv = new ArrayList<>();

    private float graphMaximum;
    private float px1, px2, px3;
    private boolean showDangerZone = false;
    private float dangerZoneLevel;
    private boolean showDailyLimitLine = false;
    private float dailyLimitValue;
    private boolean showSlider = false;
    private ImageView sliderButton;
    private int sliderStartValue;
    private View dailyLimitLine;
    private Context context;
    private Resources r;
    private String shadeRed = "#FB6463";

    public BarChart(Context context) {
        super(context);
        this.context = context;
        r = context.getResources();
        px1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 168, r.getDisplayMetrics()); // if uv value is greater than max uv use this
        px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics()); // accounts for rest of space not including the graph bar
        px3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 163, r.getDisplayMetrics()); // if uv value is not greater than max uv use this
    }

    // sets graph maximum uv value
    public void setGraphMaximum(float graphMaximum) {
        this.graphMaximum = graphMaximum;
    }

    // Displays the line that shows the daily limit AKA threshold limit
    public void showDailyLimit(boolean show, float atExposureLevel) {
        showDailyLimitLine = show;
        dailyLimitValue = atExposureLevel;
    }

    // Returns the current daily limit value
    public float getDailyLimitValue() {
        return dailyLimitValue;
    }

    private void drawDailyLimitLine() {
        if (dailyLimitValue>graphMaximum) {
            dailyLimitValue = graphMaximum;
        }
        int dlHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, r.getDisplayMetrics()); // height of bar (2dp) converted to pixels
        float dlBottomMargin = ((dailyLimitValue/graphMaximum)*px3);
        int dlTopMargin = (int)(px1-(dlBottomMargin));

        if (dailyLimitLine!=null) {
            this.removeView(dailyLimitLine);
            this.removeView(sliderButton);
        }

        sliderButton = new ImageView(context);
        sliderButton.setImageResource(R.drawable.limit_knob);
        LayoutParams buttonParams = new LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 23, r.getDisplayMetrics()), (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 23, r.getDisplayMetrics()));
        int leftMarginB = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 169, r.getDisplayMetrics());
        int rightMarginB = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 168, r.getDisplayMetrics());
        buttonParams.setMargins(leftMarginB, (dlTopMargin-(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 9, r.getDisplayMetrics())), rightMarginB, 0);
        sliderButton.setLayoutParams(buttonParams);

        if (showSlider) {
            sliderButton.setVisibility(VISIBLE);
        } else {
            sliderButton.setVisibility(INVISIBLE);
        }

        dailyLimitLine = new View(context);
        dailyLimitLine.setBackgroundColor(Color.parseColor(shadeRed));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, dlHeight);
        params.setMargins(0, dlTopMargin, 0, params.bottomMargin);
        dailyLimitLine.setLayoutParams(params);

        if (showDailyLimitLine) {
            dailyLimitLine.setVisibility(VISIBLE);
        } else {
            dailyLimitLine.setVisibility(INVISIBLE);
        }

        this.addView(dailyLimitLine);
        this.addView(sliderButton);
    }

    // Takes a color to set the bars to
    public void setBarColor(Color barColor) { }

    // Displays the hash background
    public void showDangerZone(boolean show, float dangerZoneLevel) {
        showDangerZone = show;
        this.dangerZoneLevel = dangerZoneLevel;
    }

    private void drawDangerZone() {
        int topMarginHash = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics()); // Gets metrics for top margin to offset the danger zone by aka the hash background
        int fourthHighestHieght = (int)(((dangerZoneLevel/graphMaximum)*px3)+px2);
        int hashHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 163, r.getDisplayMetrics()) - fourthHighestHieght;
        int hashHeightMarginBottom = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        int height = (hashHeight+hashHeightMarginBottom);

        ImageView hashBackground = new ImageView(context);
        hashBackground.setImageResource(R.drawable.background);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, height);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.setMargins(params.leftMargin, topMarginHash, params.rightMargin, params.bottomMargin);
        hashBackground.setLayoutParams(params);
        hashBackground.setScaleType(ImageView.ScaleType.CENTER);

        if (showDangerZone) {
            if (dangerZoneLevel > graphMaximum) {
                hashBackground.setVisibility(INVISIBLE);
            } else {
                hashBackground.setVisibility(VISIBLE);
            }
        } else {
            hashBackground.setVisibility(INVISIBLE);
        }

        this.addView(hashBackground);
    }

    // gets the uv values to display the graph bars
    public void setChartData(List<String> xValues, List<Float> yValues) {
        for (String x: xValues) {
            graphDate.add(x);
        }
        for (Float y: yValues) {
            graphUv.add(y);
        }
    }

    public void showSliderButton(boolean show) {
        showSlider = show;
    }

    // Displays the circle button on the daily limit line/bar
    private void drawSliderButton() {
        DailyLimitSlider dailyLimitSlider = new DailyLimitSlider(context);
        dailyLimitSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dailyLimitValue = (float)progress / 100.0f * graphMaximum;
                sliderStartValue = progress;
                drawDailyLimitLine();
                Log.d(getClass().getName(), "New progress is:"+dailyLimitValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(getClass().getName(), "Final release value:"+seekBar.getProgress());
            }
        });

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        int leftMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 169, r.getDisplayMetrics());
        int rightMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 168, r.getDisplayMetrics());

        params.setMargins(leftMargin, 0, rightMargin, 0);
        dailyLimitSlider.setLayoutParams(params);
        dailyLimitSlider.setProgressDrawable(null);
        dailyLimitSlider.setProgress(sliderStartValue);

        if (showSlider) {
            dailyLimitSlider.setVisibility(VISIBLE);
        } else {
            dailyLimitSlider.setVisibility(INVISIBLE);
        }

        Log.d(getClass().getName(), "START VALUE IS:"+(dailyLimitSlider.getProgress()));
        this.addView(dailyLimitSlider);
    }

    // Displays the min and max value textViews for the graph
    private void drawBarChartMinMax() {
        UvMinMax uvMinMax = new UvMinMax(this.context, (int) graphMaximum);
        this.addView(uvMinMax);
    }

    // Deletes current displayed graph
    public void eraseChart() {
        RelativeLayout graphLayout = (RelativeLayout)findViewById(R.id.graphView);
        graphLayout.removeAllViews();
    }

    // Displays the graph
    public void drawChart() {
        drawDangerZone();
        drawGraphBars();
        drawDailyLimitLine();
        drawBarChartMinMax();
        drawSliderButton();
    }

    private void drawGraphBars() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels;
        float x = dpWidth-(dpWidth*(float)0.13611);

        // Adds top and bottom horizontal lines
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 198, r.getDisplayMetrics());
        HorizantalLine line1 = new HorizantalLine(this.context);
        RelativeLayout.LayoutParams paramsLines = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, height);
        paramsLines.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        line1.setLayoutParams(paramsLines);
        this.addView(line1);

        // Adds each uv bar to the graph
        int screenWidth = (int)x;
        int numberOfBars = 12;
        int leftMargin = screenWidth/(numberOfBars+1);
        for (int i=0; i<graphDate.size(); i++) {
            float uvValue = graphUv.get(i);
            float barSize = uvValue/graphMaximum;
            int barHeight;
            boolean overSized;
            if (uvValue>=graphMaximum) {
                barHeight = (int)((barSize*px1)+px2);
                overSized =  true;
            } else {
                barHeight = (int)((barSize*px3)+px2);
                overSized = false;
            }
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, barHeight);
            if ((i-1)%3==0) {
                BarTick bar1 = new BarTick(this.context, (i==numberOfBars-1), graphDate.get(i), overSized);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMarginStart(leftMargin);
                bar1.setLayoutParams(params);
                this.addView(bar1);
            } else {
                BarTick bar1 = new BarTick(this.context, (i==numberOfBars-1), "", overSized);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.setMarginStart(leftMargin);
                bar1.setLayoutParams(params);
                this.addView(bar1);
            }
            leftMargin += screenWidth / (numberOfBars+1);
        }
    }
}

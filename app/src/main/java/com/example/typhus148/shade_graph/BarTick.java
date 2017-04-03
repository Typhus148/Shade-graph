package com.example.typhus148.shade_graph;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BarTick extends RelativeLayout {
    public BarTick(Context context, boolean isToday, String xLabel, boolean overSized, int barColor, String appScreen) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bar_tick, this, true);
        ImageView todayMarker = (ImageView)findViewById(R.id.todayMarker);
        View cutoffBlock = findViewById(R.id.cutoffBox);
        View graphBar = findViewById(R.id.graphbar);
        View oversizedBar = findViewById(R.id.oversizedBar);

        // Sets the color of the graph bars
        GradientDrawable graphBarDrawable = (GradientDrawable) graphBar.getBackground().getCurrent();
        graphBarDrawable.setColor(barColor);

        TextView xlabel = (TextView) findViewById(R.id.graphXLabel);

        if (appScreen.equals("History")) {
            if (overSized) {
                cutoffBlock.setVisibility(VISIBLE);
                oversizedBar.setVisibility(VISIBLE);
                graphBar.setVisibility(INVISIBLE);
            }

            if (!isToday) {
                xlabel.setText(xLabel);
                todayMarker.setVisibility(INVISIBLE);
            } else {
                todayMarker.setVisibility(VISIBLE);
            }
        } else {
            cutoffBlock.setVisibility(INVISIBLE);
            oversizedBar.setVisibility(INVISIBLE);
            todayMarker.setVisibility(INVISIBLE);
            xlabel.setText(xLabel);
        }
    }
}

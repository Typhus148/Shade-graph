package com.example.typhus148.shade_graph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BarTick extends RelativeLayout {
    public BarTick(Context context, boolean isToday, String xLabel, boolean overSized) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bar_tick, this, true);
        ImageView todayMarker = (ImageView)findViewById(R.id.todayMarker);
        View cutoffBlock = findViewById(R.id.cutoffBox);
        View graphBar = findViewById(R.id.graphbar);
        View oversizedBar = findViewById(R.id.oversizedBar);

        if (overSized) {
            cutoffBlock.setVisibility(VISIBLE);
            oversizedBar.setVisibility(VISIBLE);
            graphBar.setVisibility(INVISIBLE);
        }

        if (!isToday) {
            TextView xlabel = (TextView)findViewById(R.id.graphXLabel);
            xlabel.setText(xLabel);
            todayMarker.setVisibility(INVISIBLE);
        } else {
            todayMarker.setVisibility(VISIBLE);
        }
    }
}

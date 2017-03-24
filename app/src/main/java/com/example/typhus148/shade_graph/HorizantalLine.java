package com.example.typhus148.shade_graph;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class HorizantalLine extends RelativeLayout {
    public HorizantalLine(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_horizantalline, this, true);
    }
}

package com.example.typhus148.shade_graph;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class DangerZone extends RelativeLayout {
    public DangerZone(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_dangerzone, this, true);
    }
}
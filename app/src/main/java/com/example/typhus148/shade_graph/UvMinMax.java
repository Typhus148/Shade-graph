package com.example.typhus148.shade_graph;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UvMinMax extends RelativeLayout {
    public UvMinMax(Context context, int uvMax) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_uvminmax, this, true);
        TextView maxUV = (TextView)findViewById(R.id.maxExposure);
        maxUV.setText(String.format("%s", (int) uvMax));
    }
}

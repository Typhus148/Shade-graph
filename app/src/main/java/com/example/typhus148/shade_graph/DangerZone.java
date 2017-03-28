package com.example.typhus148.shade_graph;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DangerZone extends RelativeLayout {
    public DangerZone(Context context, int height) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_dangerzone, this, true);
        ImageView hashBackground = (ImageView)findViewById(R.id.dangerZone);
        hashBackground.setMaxHeight(height);
    }
}

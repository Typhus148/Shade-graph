package com.example.typhus148.shade_graph;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class HorizantalLine extends RelativeLayout {
    public HorizantalLine(Context context, String appScreen) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_horizantalline, this, true);

        View topBar = findViewById(R.id.topGraphBar);

        if (appScreen.equals("Dashboard")) {
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
            int marginTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
            int marginSides = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, height);
            params.setMargins(marginSides, marginTop, marginSides, params.bottomMargin);
            topBar.setLayoutParams(params);
        }


    }
}

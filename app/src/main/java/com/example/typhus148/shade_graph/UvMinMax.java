package com.example.typhus148.shade_graph;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UvMinMax extends RelativeLayout {
    public UvMinMax(Context context, int uvMax, String appScreen) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_uvminmax, this, true);
        TextView maxUV = (TextView)findViewById(R.id.maxExposure);
        if (appScreen.equals("History")) {
            maxUV.setText(String.format("%s", (int) uvMax));
        } else {
            int leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 324, getResources().getDisplayMetrics());
            int rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
            int topMarginMax = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            int bottomMarginMax = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 144, getResources().getDisplayMetrics());

            int bottomMarginMin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
            int topMarginMin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());

            RelativeLayout.LayoutParams paramsMax = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            paramsMax.setMargins(leftMargin, topMarginMax, rightMargin, bottomMarginMax);
            maxUV.setLayoutParams(paramsMax);
            maxUV.setText(String.format("%s", (int) uvMax));

            TextView minUv = (TextView)findViewById(R.id.minExposure);
            RelativeLayout.LayoutParams paramsMin = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            paramsMin.setMargins(leftMargin, topMarginMin, rightMargin, bottomMarginMin);
            minUv.setLayoutParams(paramsMin);
        }
    }
}

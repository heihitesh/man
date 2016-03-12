package com.parse.starter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Nilesh Verma on 3/7/2016.
 */
public class PieCharts extends AppCompatActivity {

    FrameLayout PChart;
    PieChart mChart;
    //enterring data
    private float[] ydata;
    private String[] xData = {"LineA", "LineB", "LineC", "LineD", "LineE", "LineF"};
    Float oa, ob, oc, od, oe, of;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);
        setTitle("Production Pie Chart" );

        Intent i = getIntent();
        oa = i.getFloatExtra("OA", 0);
        ob = i.getFloatExtra("OB", 0);
        oc = i.getFloatExtra("OC", 0);
        od = i.getFloatExtra("OD", 0);
        oe = i.getFloatExtra("OE", 0);
        of = i.getFloatExtra("OF", 0);


        ydata = new float[]{oa, ob, oc, od, oe, of};
        PChart = (FrameLayout) findViewById(R.id.flPieChart);
        initilizePieChart();


    }

    public void initilizePieChart() {

        mChart = new PieChart(this);
        //Adding pie Chart to the main Layout
        PChart.addView(mChart);
        PChart.setBackgroundColor(Color.LTGRAY);

        //config pie Chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Total Production Pie Chart");

        mChart.setDescriptionTextSize(20f);
        mChart.setDescriptionTypeface(Typeface.DEFAULT_BOLD);

        //enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(10);
        mChart.setDrawCenterText(true);
        mChart.setCenterText("Production");
        mChart.setCenterTextSize(12f);
        mChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        mChart.setCenterTextRadiusPercent(40f);


        //enabel rotaion of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        //set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display msg
                if (e == null) {
                    return;
                }

                Toast.makeText(PieCharts.this, xData[e.getXIndex()] + " = " +
                        e.getVal() + "%",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });

        //add data
        addData();

        mChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        //customize legends
        Legend i = mChart.getLegend();
        i.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        i.setXEntrySpace(7);
        i.setYEntrySpace(5);
    }

    private void addData() {
        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < ydata.length; i++) {
            yVals.add(new Entry(ydata[i], i));  //add all the value in the list
        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xData.length; i++) {
            xVals.add(xData[i]);
        }

        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals, "Line's");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        //add many colors
        ArrayList<Integer> color = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            color.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            color.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            color.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            color.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            color.add(c);

        color.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(color);

        //instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        data.setValueTextColor(Color.GRAY);


        mChart.setData(data);

        //undo all the higlights
        mChart.highlightValue(null);

        //update pie chart
        mChart.invalidate();


    }

}

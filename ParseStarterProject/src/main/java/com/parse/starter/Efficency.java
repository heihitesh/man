package com.parse.starter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Nilesh Verma on 3/4/2016.
 */
public class Efficency extends AppCompatActivity {


    CombinedChart combinedChart;
    CombinedData data;
    FrameLayout PChart;
    PieChart mChart;
    Float oa, ob, oc, od, oe, of;
    Float ta, tb, tc, td, te, tf;
    Float ea, eb, ec, ed, ee, ef;
    //enterring data
    private float[] ydata;
    private String[] xData = {"LineA", "LineB", "LineC", "LineD", "LineE", "LineF"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efficiency);

        Intent i = getIntent();
        oa = i.getFloatExtra("OA", 0);
        ob = i.getFloatExtra("OB", 0);
        oc = i.getFloatExtra("OC", 0);
        od = i.getFloatExtra("OD", 0);
        oe = i.getFloatExtra("OE", 0);
        of = i.getFloatExtra("OF", 0);

        ta = i.getFloatExtra("TA", 0);
        tb = i.getFloatExtra("TB", 0);
        tc = i.getFloatExtra("TC", 0);
        td = i.getFloatExtra("TD", 0);
        te = i.getFloatExtra("TE", 0);
        tf = i.getFloatExtra("TF", 0);

        ea = i.getFloatExtra("EA", 0);
        eb = i.getFloatExtra("EB", 0);
        ec = i.getFloatExtra("EC", 0);
        ed = i.getFloatExtra("ED", 0);
        ee = i.getFloatExtra("EE", 0);
        ef = i.getFloatExtra("EF", 0);
        setTitle("Efficiency Bar Graph" );

        ydata = new float[]{oa, ob, oc, od, oe, of};

        combinedChart = (CombinedChart) findViewById(R.id.chart);
        combinedChart.animateXY(1000, 1000);
        data = new CombinedData(getXAxisValues());
        data.setData(barData());
        data.setData(lineData());
        combinedChart.setData(data);
        combinedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display msg
                if (e == null) {
                    return;
                }


                switch (e.getXIndex()){

                    case 0:
                        Toast.makeText(Efficency.this, "Line A " + " Total Production = " +
                                e.getVal() , Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(Efficency.this, "Line B " + " Total Production = " +
                                e.getVal() , Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(Efficency.this, "Line C " + " Total Production = " +
                                e.getVal() , Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(Efficency.this, "Line D " + " Total Production = " +
                                e.getVal() , Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(Efficency.this, "Line E " + " Total Production = " +
                                e.getVal() , Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(Efficency.this, "Line F " + " Total Production = " +
                                e.getVal() , Toast.LENGTH_SHORT).show();
                        break;


                }

            }

            @Override
            public void onNothingSelected() {

            }
        });

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
        mChart.setDescription("Production");

        mChart.setDescriptionTextSize(20f);
        mChart.setDescriptionTypeface(Typeface.DEFAULT_BOLD);

        //enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(9);
        mChart.setTransparentCircleRadius(10);

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

                Toast.makeText(Efficency.this, xData[e.getXIndex()] + " = " +
                        e.getVal() ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });

        //add data
        addData();

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
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


    // creating list of x-axis values
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("A");
        labels.add("B");
        labels.add("C");
        labels.add("D");
        labels.add("E");
        labels.add("F");
        return labels;
    }

    // this method is used to create data for line graph
    public LineData lineData() {
        ArrayList<Entry> line = new ArrayList<>();
        line.add(new Entry(ta, 0));
        line.add(new Entry(tb, 1));
        line.add(new Entry(tc, 2));
        line.add(new Entry(td, 3));
        line.add(new Entry(te, 4));
        line.add(new Entry(tf, 5));

        LineDataSet lineDataSet = new LineDataSet(line, "");
        lineDataSet.setCircleRadius(10f);



        LineData lineData = new LineData(getXAxisValues(), lineDataSet);

        return lineData;

    }

    // this method is used to create data for Bar graph
    public BarData barData() {

        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(oa, 0));
        group1.add(new BarEntry(ob, 1));
        group1.add(new BarEntry(oc, 2));
        group1.add(new BarEntry(od, 3));
        group1.add(new BarEntry(oe, 4));
        group1.add(new BarEntry(of, 5));


        BarDataSet barDataSet = new BarDataSet(group1, "");
        barDataSet.setColor(Color.rgb(0, 155, 0));


        BarData barData = new BarData(getXAxisValues(), barDataSet);
        return barData;

    }
}

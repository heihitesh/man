package com.parse.starter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Nilesh Verma on 3/4/2016.
 */
public class BarGraphs extends AppCompatActivity {


    CombinedChart combinedChart;
    CombinedData data;
    Float oa, ob, oc, od, oe, of;
    Float ta, tb, tc, td, te, tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_graphs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer

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

        setTitle("Total Production Bar Graph" );

        combinedChart = (CombinedChart) findViewById(R.id.chart);
        combinedChart.animateY(1200);
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
                switch (e.getXIndex()) {

                    case 0:
                        Toast.makeText(BarGraphs.this, "Line A " + " Total Production = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(BarGraphs.this, "Line B " + " Total Production = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(BarGraphs.this, "Line C " + " Total Production = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(BarGraphs.this, "Line D " + " Total Production = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(BarGraphs.this, "Line E " + " Total Production = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(BarGraphs.this, "Line F " + " Total Production = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;


                }


            }

            @Override
            public void onNothingSelected() {

            }
        });

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
        lineDataSet.setCircleColorHole(Color.BLACK);
        lineDataSet.setColors(new int[]{Color.BLACK});
        lineDataSet.setLineWidth(4f);

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
        //barDataSet.setColor(Color.rgb(0, 155, 0));
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        BarData barData = new BarData(getXAxisValues(), barDataSet);
        return barData;

    }
}

package com.parse.starter;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
 * Created by Nilesh Verma on 3/13/2016.
 */
public class BarGraphForLine extends AppCompatActivity {


    CombinedChart combinedChart;
    CombinedData data;
    Float oa, ob, oc, od, oe, of, og, oh;
    Float ta, tb, tc, td, te, tf, tg, th;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_graphs_for_line);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer

        Intent i = getIntent();
        oa = i.getFloatExtra("p1", 0);
        ob = i.getFloatExtra("p2", 0);
        oc = i.getFloatExtra("p3", 0);
        od = i.getFloatExtra("p4", 0);
        oe = i.getFloatExtra("p5", 0);
        of = i.getFloatExtra("p6", 0);
        og = i.getFloatExtra("p7", 0);
        oh = i.getFloatExtra("p8", 0);

        ta = i.getFloatExtra("t1", 0);
        tb = i.getFloatExtra("t2", 0);
        tc = i.getFloatExtra("t3", 0);
        td = i.getFloatExtra("t4", 0);
        te = i.getFloatExtra("t5", 0);
        tf = i.getFloatExtra("t6", 0);
        tg = i.getFloatExtra("t7", 0);
        th = i.getFloatExtra("t8", 0);


        setTitle("Total Production Line Bar Graph");

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
                        Toast.makeText(BarGraphForLine.this, "8:30 - 9:30 " + " Total Production = " +
                                e.getVal() +"Total Target = " + ta.toString() , Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(BarGraphForLine.this, "9:30 - 10:30 " + " Total Production = " +
                        e.getVal() +"Total Target = " + tb.toString() , Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(BarGraphForLine.this, "10:30 - 11:30 " + " Total Production = " +
                                e.getVal() +"Total Target = " + tc.toString() , Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(BarGraphForLine.this, "11:30 - 12:30 " + " Total Production = " +
                                e.getVal() +"Total Target = " + td.toString() , Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(BarGraphForLine.this, "1:00 - 2:00 " + " Total Production = " +
                                e.getVal() +"Total Target = " + te.toString() , Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(BarGraphForLine.this,  "2:00 - 3:00 " + " Total Production = " +
                                e.getVal() +"Total Target = " + tf.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(BarGraphForLine.this,  "3:00 - 4:00 " + " Total Production = " +
                                e.getVal() +"Total Target = " + tg.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(BarGraphForLine.this,  "4:00 - 5:00 " + " Total Production = " +
                                e.getVal() +"Total Target = " + th.toString(), Toast.LENGTH_SHORT).show();
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
        labels.add("8:30");
        labels.add("9:30");
        labels.add("10:30");
        labels.add("11:30");
        labels.add("1:00");
        labels.add("2:00");
        labels.add("3:00");
        labels.add("4:00");

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
        line.add(new Entry(tg, 6));
        line.add(new Entry(th, 7));



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
        group1.add(new BarEntry(og, 6));
        group1.add(new BarEntry(oh, 7));

        BarDataSet barDataSet = new BarDataSet(group1, "");
        //barDataSet.setColor(Color.rgb(0, 155, 0));
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        BarData barData = new BarData(getXAxisValues(), barDataSet);
        return barData;

    }
}

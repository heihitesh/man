package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Nilesh Verma on 3/9/2016.
 */
public class EfficiencyGraph extends AppCompatActivity {

    com.github.mikephil.charting.charts.CombinedChart combinedChart;
    CombinedData data;
    Float oa, ob, oc, od, oe, of;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efficiency_bargraph);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer

        Intent i = getIntent();
        oa = i.getFloatExtra("EA", 0);
        ob = i.getFloatExtra("EB", 0);
        oc = i.getFloatExtra("EC", 0);
        od = i.getFloatExtra("ED", 0);
        oe = i.getFloatExtra("EE", 0);
        of = i.getFloatExtra("EF", 0);
        setTitle("Efficiency Bar Graph" );

        combinedChart = (com.github.mikephil.charting.charts.CombinedChart) findViewById(R.id.chart);
        combinedChart.animateY(1200);
        data = new CombinedData(getXAxisValues());
        data.setData(barData());
        combinedChart.setData(data);
        combinedChart.setDescription("Efficiency Graph");
        combinedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display msg
                if (e == null) {
                    return;
                }
                switch (e.getXIndex()) {
                    case 0:
                        Toast.makeText(EfficiencyGraph.this, "Line A " + " Total Efficiency = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(EfficiencyGraph.this, "Line B " + " Total Efficiency = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(EfficiencyGraph.this, "Line C " + " Total Efficiency = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(EfficiencyGraph.this, "Line D " + " Total Efficiency = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(EfficiencyGraph.this, "Line E " + " Total Efficiency = " +
                                e.getVal(), Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(EfficiencyGraph.this, "Line F " + " Total Efficiency = " +
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

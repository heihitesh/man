package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Nilesh Verma on 3/4/2016.
 */
public class LineGraph extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_graph);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);


        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(2f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));

        ArrayList<Entry> entrie = new ArrayList<>();
        entrie.add(new Entry(6f, 0));
        entrie.add(new Entry(13f, 1));
        entrie.add(new Entry(2f, 2));
        entrie.add(new Entry(66f, 3));
        entrie.add(new Entry(15f, 4));
        entrie.add(new Entry(94f, 5));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
        LineDataSet dataset1 = new LineDataSet(entrie, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        LineData data = new LineData(labels, dataset);
        LineData data2 = new LineData(labels, dataset1);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data2);
        lineChart.setData(data);

        lineChart.animateY(5000);

    }
}





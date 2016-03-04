package com.parse.starter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserFeed extends AppCompatActivity {
    String Lineno, Date;
    String line;
    TextView P1, P2, P3, P4, P5, P6, P7, P8;
    TextView T1, T2, T3, T4, T5, T6, T7, T8;
    TextView tvDate, Status;
    ProgressDialog pd;
    Button Refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        Refresh = (Button) findViewById(R.id.bRefresh);
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                getDataFormNet();
            }
        });
        loadDate();
        Intent i = getIntent();
        Lineno = i.getStringExtra("lineno");
        line = "Line" + Lineno;
        Date = i.getStringExtra("date");
        setTitle("Line " + Lineno);
        showProgressDialog();
        getDataFormNet();

    }

    public void showProgressDialog() {
        ///for the Progress Bar to get Data
        pd = new ProgressDialog(UserFeed.this);
        pd.setTitle("Getting Data...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
    }

    public void getDataFormNet() {

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(line);

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");

        query.whereEqualTo("date", Date).findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    P1.setText(String.valueOf(objects.get(0).getNumber("P1")));
                                    P2.setText(String.valueOf(objects.get(0).getNumber("P2")));
                                    P3.setText(String.valueOf(objects.get(0).getNumber("P3")));
                                    P4.setText(String.valueOf(objects.get(0).getNumber("P4")));
                                    P5.setText(String.valueOf(objects.get(0).getNumber("P5")));
                                    P6.setText(String.valueOf(objects.get(0).getNumber("P6")));
                                    P7.setText(String.valueOf(objects.get(0).getNumber("P7")));
                                    P8.setText(String.valueOf(objects.get(0).getNumber("P8")));

                                    T1.setText(String.valueOf(objects.get(0).getNumber("T1")));
                                    T2.setText(String.valueOf(objects.get(0).getNumber("T2")));
                                    T3.setText(String.valueOf(objects.get(0).getNumber("T3")));
                                    T4.setText(String.valueOf(objects.get(0).getNumber("T4")));
                                    T5.setText(String.valueOf(objects.get(0).getNumber("T5")));
                                    T6.setText(String.valueOf(objects.get(0).getNumber("T6")));
                                    T7.setText(String.valueOf(objects.get(0).getNumber("T7")));
                                    T8.setText(String.valueOf(objects.get(0).getNumber("T8")));

                                    Status.setText("Updated By " + String.valueOf(objects.get(0).getString("username")));
                                    tvDate.setText(String.valueOf(objects.get(0).getString("date")));
                                    pd.dismiss();


                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "ERROR  ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR in LOading ", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    public void loadDate() {

        Status = (TextView) findViewById(R.id.tvStatus);
        tvDate = (TextView) findViewById(R.id.tvDate);

        P1 = (TextView) findViewById(R.id.tvPro1);
        P2 = (TextView) findViewById(R.id.tvPro2);
        P3 = (TextView) findViewById(R.id.tvPro3);
        P4 = (TextView) findViewById(R.id.tvPro4);
        P5 = (TextView) findViewById(R.id.tvPro5);
        P6 = (TextView) findViewById(R.id.tvPro6);
        P7 = (TextView) findViewById(R.id.tvPro7);
        P8 = (TextView) findViewById(R.id.tvPro8);

        T1 = (TextView) findViewById(R.id.tvTar1);
        T2 = (TextView) findViewById(R.id.tvTar2);
        T3 = (TextView) findViewById(R.id.tvTar3);
        T4 = (TextView) findViewById(R.id.tvTar4);
        T5 = (TextView) findViewById(R.id.tvTar5);
        T6 = (TextView) findViewById(R.id.tvTar6);
        T7 = (TextView) findViewById(R.id.tvTar7);
        T8 = (TextView) findViewById(R.id.tvTar8);
    }

}

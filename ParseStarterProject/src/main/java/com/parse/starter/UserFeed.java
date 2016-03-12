package com.parse.starter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    TextView totalProduction,TotalTarget;
    int p1, p2, p3, p4, p5, p6, p7, p8;
    int t1, t2, t3, t4, t5, t6, t7, t8;
    String style, username;
    TextView StyleNo, Status;
    ProgressDialog pd;


    Boolean loadForProductionActivity;
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer


        cd = new ConnectionDetector(getApplicationContext());

        loadDate();
        Intent i = getIntent();
        Lineno = i.getStringExtra("lineno");
        line = "Line" + Lineno;
        Date = i.getStringExtra("date");
        TotalTarget = (TextView) findViewById(R.id.tvTotalT);
        totalProduction = (TextView) findViewById(R.id.tvTotalP);

        loadForProductionActivity = i.getBooleanExtra("loadData", false);

        if (loadForProductionActivity) {
            isInternetPresent = cd.isConnectingToInternet();
            if (isInternetPresent) {
                getDataFormNet();
            } else {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(UserFeed.this, "Please Check Your Internet Connection and Then Refresh");
            }
        }

        p1 = i.getIntExtra("p1", 0);
        p2 = i.getIntExtra("p2", 0);
        p3 = i.getIntExtra("p3", 0);
        p4 = i.getIntExtra("p4", 0);
        p5 = i.getIntExtra("p5", 0);
        p6 = i.getIntExtra("p6", 0);
        p7 = i.getIntExtra("p7", 0);
        p8 = i.getIntExtra("p8", 0);

        //target
        t1 = i.getIntExtra("t1", 0);
        t2 = i.getIntExtra("t2", 0);
        t3 = i.getIntExtra("t3", 0);
        t4 = i.getIntExtra("t4", 0);
        t5 = i.getIntExtra("t5", 0);
        t6 = i.getIntExtra("t6", 0);
        t7 = i.getIntExtra("t7", 0);
        t8 = i.getIntExtra("t8", 0);

        style = i.getStringExtra("style");
        username = i.getStringExtra("username");

        SettingData();


        setTitle("Line " + Lineno + ": " + "( " + Date + " )");

        //  getDataFormNet();

    }

    public void SettingData() {

        P1.setText(String.valueOf(p1));
        P2.setText(String.valueOf(p2));
        P3.setText(String.valueOf(p3));
        P4.setText(String.valueOf(p4));
        P5.setText(String.valueOf(p5));
        P6.setText(String.valueOf(p6));
        P7.setText(String.valueOf(p7));
        P8.setText(String.valueOf(p8));

        T1.setText(String.valueOf(t1));
        T2.setText(String.valueOf(t2));
        T3.setText(String.valueOf(t3));
        T4.setText(String.valueOf(t4));
        T5.setText(String.valueOf(t5));
        T6.setText(String.valueOf(t6));
        T7.setText(String.valueOf(t7));
        T8.setText(String.valueOf(t8));

        String totalP = String.valueOf(Integer.parseInt(String.valueOf(P1.getText())) + Integer.parseInt(String.valueOf(P2.getText()))
                +Integer.parseInt(String.valueOf(P3.getText()))+Integer.parseInt(String.valueOf(P4.getText()))
                +Integer.parseInt(String.valueOf(P5.getText()))+Integer.parseInt(String.valueOf(P6.getText()))
                +Integer.parseInt(String.valueOf(P7.getText())) + Integer.parseInt(String.valueOf(P8.getText())));
        totalProduction.setText(totalP);

        String totalT = String.valueOf(Integer.parseInt(String.valueOf(T1.getText())) + Integer.parseInt(String.valueOf(T2.getText()))
                +Integer.parseInt(String.valueOf(T3.getText()))+Integer.parseInt(String.valueOf(T4.getText()))
                +Integer.parseInt(String.valueOf(T5.getText()))+Integer.parseInt(String.valueOf(T6.getText()))
                +Integer.parseInt(String.valueOf(T7.getText())) + Integer.parseInt(String.valueOf(T8.getText())));
        TotalTarget.setText(totalT);

        Status.setText("Updated By " + username);
        StyleNo.setText(style);

    }

    public void showProgressDialog() {
        ///for the Progress Bar to get Data
        pd = new ProgressDialog(UserFeed.this);
        pd.setTitle("Getting Data...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        pd.show();
    }

    public void getDataFormNet() {
        showProgressDialog();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");

        query.whereEqualTo("date", Date).whereEqualTo("LineNo", line).findInBackground(
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
                                    String totalP = String.valueOf(Integer.parseInt(String.valueOf(P1.getText())) + Integer.parseInt(String.valueOf(P2.getText()))
                                    +Integer.parseInt(String.valueOf(P3.getText()))+Integer.parseInt(String.valueOf(P4.getText()))
                                    +Integer.parseInt(String.valueOf(P5.getText()))+Integer.parseInt(String.valueOf(P6.getText()))
                                    +Integer.parseInt(String.valueOf(P7.getText())) + Integer.parseInt(String.valueOf(P8.getText())));
                                    totalProduction.setText(totalP);

                                    T1.setText(String.valueOf(objects.get(0).getNumber("T1")));
                                    T2.setText(String.valueOf(objects.get(0).getNumber("T2")));
                                    T3.setText(String.valueOf(objects.get(0).getNumber("T3")));
                                    T4.setText(String.valueOf(objects.get(0).getNumber("T4")));
                                    T5.setText(String.valueOf(objects.get(0).getNumber("T5")));
                                    T6.setText(String.valueOf(objects.get(0).getNumber("T6")));
                                    T7.setText(String.valueOf(objects.get(0).getNumber("T7")));
                                    T8.setText(String.valueOf(objects.get(0).getNumber("T8")));
                                    String totalT = String.valueOf(Integer.parseInt(String.valueOf(T1.getText())) + Integer.parseInt(String.valueOf(T2.getText()))
                                            +Integer.parseInt(String.valueOf(T3.getText()))+Integer.parseInt(String.valueOf(T4.getText()))
                                            +Integer.parseInt(String.valueOf(T5.getText()))+Integer.parseInt(String.valueOf(T6.getText()))
                                            +Integer.parseInt(String.valueOf(T7.getText())) + Integer.parseInt(String.valueOf(T8.getText())));
                                    TotalTarget.setText(totalT);

                                    Status.setText("Updated By " + String.valueOf(objects.get(0).getString("username")));
                                    StyleNo.setText(String.valueOf(objects.get(0).getString("StyleNo")));
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
        StyleNo = (TextView) findViewById(R.id.tvStyleNo);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.get:
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    getDataFormNet();
                } else {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(UserFeed.this, "Please Check Your Internet Connection and Then Refresh");
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.

                        onOptionsItemSelected(item);

        }
    }


}

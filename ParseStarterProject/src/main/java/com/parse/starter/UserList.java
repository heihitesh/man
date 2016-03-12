package com.parse.starter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserList extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView date;
    Spinner Lineno;
    Button Send, LogOut, Production;
    String item;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    ProgressDialog pd;
    // flag for Internet connection status
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;


    String Da;
    //Line A
    public int pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8, totalpa = 0;
    int ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8, totalta = 0;
    String unamea = "-", stylea = "-";
    //Line B
    int pb1, pb2, pb3, pb4, pb5, pb6, pb7, pb8, totalpb = 0;
    int tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8, totaltb = 0;
    String unameb = "-", styleb = "-";
    //Line C
    int pc1, pc2, pc3, pc4, pc5, pc6, pc7, pc8, totalpc = 0;
    int tc1, tc2, tc3, tc4, tc5, tc6, tc7, tc8, totaltc = 0;
    String unamec = "-", stylec = "-";
    //Line D
    int pd1, pd2, pd3, pd4, pd5, pd6, pd7, pd8, totalpd = 0;
    int td1, td2, td3, td4, td5, td6, td7, td8, totaltd = 0;
    String unamed = "-", styled = "-";
    //Line E
    int pe1, pe2, pe3, pe4, pe5, pe6, pe7, pe8, totalpe = 0;
    int te1, te2, te3, te4, te5, te6, te7, te8, totalte = 0;
    String unamee = "-", stylee = "-";
    //Line F
    int pf1, pf2, pf3, pf4, pf5, pf6, pf7, pf8, totalpf = 0;
    int tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, totaltf = 0;
    String unamef = "-", stylef = "-";

    Boolean alltheDataisFetched = true;

    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    Boolean fetchDataAutomatically = false;


    CheckBox switchButton;

    public int sendData() {
        return pa1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer


        //calling the Fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navifation_drawer);

        //self-Defined Method
        drawerFragment.setUp(R.id.fragment_navifation_drawer, (DrawerLayout) findViewById(R.id.Drawer_Layout), toolbar);


        Lineno = (Spinner) findViewById(R.id.spinner);
        Lineno.setOnItemSelectedListener(this);
        setSpinnerItems();
        cd = new ConnectionDetector(getApplicationContext());


        date = (TextView) findViewById(R.id.datePicker);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        Send = (Button) findViewById(R.id.bSend);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent i = new Intent(getApplicationContext(), UserFeed.class);

                    switch (item) {
                        case "A":
                            i.putExtra("date", String.valueOf(date.getText()));
                            i.putExtra("lineno", String.valueOf(item));
                            i.putExtra("p1", pa1);
                            i.putExtra("p2", pa2);
                            i.putExtra("p3", pa3);
                            i.putExtra("p4", pa4);
                            i.putExtra("p5", pa5);
                            i.putExtra("p6", pa6);
                            i.putExtra("p7", pa7);
                            i.putExtra("p8", pa8);

                            //putting Target
                            i.putExtra("t1", ta1);
                            i.putExtra("t2", ta2);
                            i.putExtra("t3", ta3);
                            i.putExtra("t4", ta4);
                            i.putExtra("t5", ta5);
                            i.putExtra("t6", ta6);
                            i.putExtra("t7", ta7);
                            i.putExtra("t8", ta8);
                            //putting Username and Style no
                            i.putExtra("username", unamea);
                            i.putExtra("style", stylea);
                            i.putExtra("loadData", alltheDataisFetched);
                            startActivity(i);
                            break;
                        case "B":
                            i.putExtra("date", String.valueOf(date.getText()));
                            i.putExtra("lineno", String.valueOf(item));
                            i.putExtra("p1", pb1);
                            i.putExtra("p2", pb2);
                            i.putExtra("p3", pb3);
                            i.putExtra("p4", pb4);
                            i.putExtra("p5", pb5);
                            i.putExtra("p6", pb6);
                            i.putExtra("p7", pb7);
                            i.putExtra("p8", pb8);

                            //putting Target
                            i.putExtra("t1", tb1);
                            i.putExtra("t2", tb2);
                            i.putExtra("t3", tb3);
                            i.putExtra("t4", tb4);
                            i.putExtra("t5", tb5);
                            i.putExtra("t6", tb6);
                            i.putExtra("t7", tb7);
                            i.putExtra("t8", tb8);
                            //putting Username and Style no
                            i.putExtra("username", unameb);
                            i.putExtra("style", styleb);
                            i.putExtra("loadData", alltheDataisFetched);

                            startActivity(i);
                            break;

                        case "C":
                            i.putExtra("date", String.valueOf(date.getText()));
                            i.putExtra("lineno", String.valueOf(item));
                            i.putExtra("p1", pc1);
                            i.putExtra("p2", pc2);
                            i.putExtra("p3", pc3);
                            i.putExtra("p4", pc4);
                            i.putExtra("p5", pc5);
                            i.putExtra("p6", pc6);
                            i.putExtra("p7", pc7);
                            i.putExtra("p8", pc8);

                            //putting Target
                            i.putExtra("t1", tc1);
                            i.putExtra("t2", tc2);
                            i.putExtra("t3", tc3);
                            i.putExtra("t4", tc4);
                            i.putExtra("t5", tc5);
                            i.putExtra("t6", tc6);
                            i.putExtra("t7", tc7);
                            i.putExtra("t8", tc8);
                            //putting Username and Style no
                            i.putExtra("username", unamec);
                            i.putExtra("style", stylec);
                            i.putExtra("loadData", alltheDataisFetched);

                            startActivity(i);
                            break;
                        case "D":
                            i.putExtra("date", String.valueOf(date.getText()));
                            i.putExtra("lineno", String.valueOf(item));
                            i.putExtra("p1", pd1);
                            i.putExtra("p2", pd2);
                            i.putExtra("p3", pd3);
                            i.putExtra("p4", pd4);
                            i.putExtra("p5", pd5);
                            i.putExtra("p6", pd6);
                            i.putExtra("p7", pd7);
                            i.putExtra("p8", pd8);

                            //putting Target
                            i.putExtra("t1", td1);
                            i.putExtra("t2", td2);
                            i.putExtra("t3", td3);
                            i.putExtra("t4", td4);
                            i.putExtra("t5", td5);
                            i.putExtra("t6", td6);
                            i.putExtra("t7", td7);
                            i.putExtra("t8", td8);
                            //putting Username and Style no
                            i.putExtra("username", unamed);
                            i.putExtra("style", styled);
                            i.putExtra("loadData", alltheDataisFetched);

                            startActivity(i);
                            break;
                        case "E":
                            i.putExtra("date", String.valueOf(date.getText()));
                            i.putExtra("lineno", String.valueOf(item));
                            i.putExtra("p1", pe1);
                            i.putExtra("p2", pe2);
                            i.putExtra("p3", pe3);
                            i.putExtra("p4", pe4);
                            i.putExtra("p5", pe5);
                            i.putExtra("p6", pe6);
                            i.putExtra("p7", pe7);
                            i.putExtra("p8", pe8);

                            //putting Target
                            i.putExtra("t1", te1);
                            i.putExtra("t2", te2);
                            i.putExtra("t3", te3);
                            i.putExtra("t4", te4);
                            i.putExtra("t5", te5);
                            i.putExtra("t6", te6);
                            i.putExtra("t7", te7);
                            i.putExtra("t8", te8);
                            //putting Username and Style no
                            i.putExtra("username", unamee);
                            i.putExtra("style", stylee);
                            i.putExtra("loadData", alltheDataisFetched);
                            startActivity(i);
                            break;
                        case "F":
                            i.putExtra("date", String.valueOf(date.getText()));
                            i.putExtra("lineno", String.valueOf(item));
                            i.putExtra("p1", pf1);
                            i.putExtra("p2", pf2);
                            i.putExtra("p3", pf3);
                            i.putExtra("p4", pf4);
                            i.putExtra("p5", pf5);
                            i.putExtra("p6", pf6);
                            i.putExtra("p7", pf7);
                            i.putExtra("p8", pf8);

                            //putting Target
                            i.putExtra("t1", tf1);
                            i.putExtra("t2", tf2);
                            i.putExtra("t3", tf3);
                            i.putExtra("t4", tf4);
                            i.putExtra("t5", tf5);
                            i.putExtra("t6", tf6);
                            i.putExtra("t7", tf7);
                            i.putExtra("t8", tf8);

                            //putting Username and Style no
                            i.putExtra("username", unamef);
                            i.putExtra("style", stylef);
                            i.putExtra("loadData", alltheDataisFetched);

                            startActivity(i);
                            break;
                    }


                }
            }
        });
        LogOut = (Button) findViewById(R.id.bLogOut);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You are Loged Out", Toast.LENGTH_LONG).show();
                ParseUser.logOut();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


      /*  Production = (Button) findViewById(R.id.bProductionDetails);
        Production.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserList.this, Production.class);
                i.putExtra("date", String.valueOf(date.getText()));
                i.putExtra("tpa", totalpa);
                i.putExtra("tpb", totalpb);
                i.putExtra("tpc", totalpc);
                i.putExtra("tpd", totalpd);
                i.putExtra("tpe", totalpe);
                i.putExtra("tpf", totalpf);

                i.putExtra("tta", totalta);
                i.putExtra("ttb", totaltb);
                i.putExtra("ttc", totaltc);
                i.putExtra("ttd", totaltd);
                i.putExtra("tte", totalte);
                i.putExtra("ttf", totaltf);

                i.putExtra("stylea", stylea);
                i.putExtra("styleb", styleb);
                i.putExtra("stylec", stylec);
                i.putExtra("styled", styled);
                i.putExtra("stylee", stylee);
                i.putExtra("stylef", stylef);


                startActivity(i);
            }
        });*/

        switchButton = (CheckBox) findViewById(R.id.swgetData);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fetchDataAutomatically = isChecked;
            }
        });

        //checking for the switch button

        prefs = getPreferences(MODE_PRIVATE);
        Boolean restoredStatus = prefs.getBoolean("fetchDataAutomatically", false);
        if (restoredStatus == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                switchButton.setChecked(true);
            }
            isInternetPresent = cd.isConnectingToInternet();
            if (isInternetPresent) {
                Da = String.valueOf(date.getText());
                alltheDataisFetched = false;
                FetchdataofLineA("LineA");
                FetchdataofLineB("LineB");
                FetchdataofLineC("LineC");
                FetchdataofLineD("LineD");
                FetchdataofLineE("LineE");
                FetchdataofLineF("LineF");
            } else {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(UserList.this, "Please Check Your Internet Connection and Then Refresh");

            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                switchButton.setChecked(false);
            }

        }

    }

    private void FetchdataofLineA(String line) {

        final ProgressDialog progressDialoga = new ProgressDialog(UserList.this);
        progressDialoga.setTitle("Please wait..");
        progressDialoga.setMessage("Getting data of: " + line);
        progressDialoga.setCancelable(false);
        progressDialoga.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");
        //
        query.whereEqualTo("date", Da).whereEqualTo("LineNo", line).findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {

                                    totalpa = (int) objects.get(0).getNumber("TotalProduction");
                                    pa1 = (int) objects.get(0).getNumber("P1");
                                    pa2 = (int) objects.get(0).getNumber("P2");
                                    pa3 = (int) objects.get(0).getNumber("P3");
                                    pa4 = (int) objects.get(0).getNumber("P4");
                                    pa5 = (int) objects.get(0).getNumber("P5");
                                    pa6 = (int) objects.get(0).getNumber("P6");
                                    pa7 = (int) objects.get(0).getNumber("P7");
                                    pa8 = (int) objects.get(0).getNumber("P8");

                                    totalta = (int) objects.get(0).getNumber("TotalTarget");
                                    ta1 = (int) objects.get(0).getNumber("T1");
                                    ta2 = (int) objects.get(0).getNumber("T2");
                                    ta3 = (int) objects.get(0).getNumber("T3");
                                    ta4 = (int) objects.get(0).getNumber("T4");
                                    ta5 = (int) objects.get(0).getNumber("T5");
                                    ta6 = (int) objects.get(0).getNumber("T6");
                                    ta7 = (int) objects.get(0).getNumber("T7");
                                    ta8 = (int) objects.get(0).getNumber("T8");
                                    unamea = String.valueOf(objects.get(0).getString("username"));
                                    stylea = String.valueOf(objects.get(0).getString("StyleNo"));
                                    Toast.makeText(UserList.this, "Getting Data From Line A Success!  ", Toast.LENGTH_SHORT).show();

                                    progressDialoga.dismiss();

                                }

                            } else {
                                Toast.makeText(UserList.this, "Cant get the Data of Line A ", Toast.LENGTH_LONG).show();
                                progressDialoga.dismiss();
                            }
                        } else {
                            Toast.makeText(UserList.this, "Cant get the Data of Line A", Toast.LENGTH_LONG).show();
                            progressDialoga.dismiss();

                        }
                    }
                });

    }

    private void FetchdataofLineB(String line) {
        final ProgressDialog progressDialogb = new ProgressDialog(UserList.this);
        progressDialogb.setTitle("Please wait..");
        progressDialogb.setMessage("Getting data...");
        progressDialogb.setCancelable(false);
        progressDialogb.show();
        //  showProgressDialog(line);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");

        query.whereEqualTo("date", Da).whereEqualTo("LineNo", line).findInBackground(

                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    totalpb = (int) objects.get(0).getNumber("TotalProduction");
                                    pb1 = (int) objects.get(0).getNumber("P1");
                                    pb2 = (int) objects.get(0).getNumber("P2");
                                    pb3 = (int) objects.get(0).getNumber("P3");
                                    pb4 = (int) objects.get(0).getNumber("P4");
                                    pb5 = (int) objects.get(0).getNumber("P5");
                                    pb6 = (int) objects.get(0).getNumber("P6");
                                    pb7 = (int) objects.get(0).getNumber("P7");
                                    pb8 = (int) objects.get(0).getNumber("P8");

                                    totaltb = (int) objects.get(0).getNumber("TotalTarget");
                                    tb1 = (int) objects.get(0).getNumber("T1");
                                    tb2 = (int) objects.get(0).getNumber("T2");
                                    tb3 = (int) objects.get(0).getNumber("T3");
                                    tb4 = (int) objects.get(0).getNumber("T4");
                                    tb5 = (int) objects.get(0).getNumber("T5");
                                    tb6 = (int) objects.get(0).getNumber("T6");
                                    tb7 = (int) objects.get(0).getNumber("T7");
                                    tb8 = (int) objects.get(0).getNumber("T8");
                                    unameb = String.valueOf(objects.get(0).getString("username"));
                                    styleb = String.valueOf(objects.get(0).getString("StyleNo"));
                                    Toast.makeText(UserList.this, "Getting Data From Line C Succes !", Toast.LENGTH_SHORT).show();
                                    progressDialogb.dismiss();
                                    //   pd.dismiss();


                                }

                            } else {
                                Toast.makeText(UserList.this, "Cant get the Data of Line B ", Toast.LENGTH_LONG).show();
                                progressDialogb.dismiss();
                                // pd.dismiss();
                            }
                        } else {
                            Toast.makeText(UserList.this, "Cant get the Data", Toast.LENGTH_LONG).show();
                            progressDialogb.dismiss();
                            //  pd.dismiss();

                        }
                    }
                });


    }

    private void FetchdataofLineC(String line) {
        final ProgressDialog progressDialogc = new ProgressDialog(UserList.this);
        progressDialogc.setTitle("Please wait..");
        progressDialogc.setMessage("Getting data...");
        progressDialogc.setCancelable(false);
        progressDialogc.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");

        query.whereEqualTo("date", Da).whereEqualTo("LineNo", line).findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    totalpc = (int) objects.get(0).getNumber("TotalProduction");
                                    pc1 = (int) objects.get(0).getNumber("P1");
                                    pc2 = (int) objects.get(0).getNumber("P2");
                                    pc3 = (int) objects.get(0).getNumber("P3");
                                    pc4 = (int) objects.get(0).getNumber("P4");
                                    pc5 = (int) objects.get(0).getNumber("P5");
                                    pc6 = (int) objects.get(0).getNumber("P6");
                                    pc7 = (int) objects.get(0).getNumber("P7");
                                    pc8 = (int) objects.get(0).getNumber("P8");

                                    totaltc = (int) objects.get(0).getNumber("TotalTarget");
                                    tc1 = (int) objects.get(0).getNumber("T1");
                                    tc2 = (int) objects.get(0).getNumber("T2");
                                    tc3 = (int) objects.get(0).getNumber("T3");
                                    tc4 = (int) objects.get(0).getNumber("T4");
                                    tc5 = (int) objects.get(0).getNumber("T5");
                                    tc6 = (int) objects.get(0).getNumber("T6");
                                    tc7 = (int) objects.get(0).getNumber("T7");
                                    tc8 = (int) objects.get(0).getNumber("T8");
                                    unamec = String.valueOf(objects.get(0).getString("username"));
                                    stylec = String.valueOf(objects.get(0).getString("StyleNo"));

                                    Toast.makeText(UserList.this, "Getting Data From Line C Succes !", Toast.LENGTH_SHORT).show();
                                    progressDialogc.dismiss();


                                }

                            } else {
                                Toast.makeText(UserList.this, "Cant get the Data of Line C ", Toast.LENGTH_LONG).show();
                                progressDialogc.dismiss();
                            }
                        } else {
                            Toast.makeText(UserList.this, "Cant get the Data of Line C", Toast.LENGTH_LONG).show();
                            progressDialogc.dismiss();

                        }
                    }
                });
    }

    private void FetchdataofLineD(String line) {
        final ProgressDialog progressDialogd = new ProgressDialog(UserList.this);
        progressDialogd.setTitle("Please wait..");
        progressDialogd.setMessage("Getting data ...");
        progressDialogd.setCancelable(false);
        progressDialogd.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");
        query.whereEqualTo("date", Da).whereEqualTo("LineNo", line).findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    totalpd = (int) objects.get(0).getNumber("TotalProduction");
                                    pd1 = (int) objects.get(0).getNumber("P1");
                                    pd2 = (int) objects.get(0).getNumber("P2");
                                    pd3 = (int) objects.get(0).getNumber("P3");
                                    pd4 = (int) objects.get(0).getNumber("P4");
                                    pd5 = (int) objects.get(0).getNumber("P5");
                                    pd6 = (int) objects.get(0).getNumber("P6");
                                    pd7 = (int) objects.get(0).getNumber("P7");
                                    pd8 = (int) objects.get(0).getNumber("P8");

                                    totaltd = (int) objects.get(0).getNumber("TotalTarget");
                                    td1 = (int) objects.get(0).getNumber("T1");
                                    td2 = (int) objects.get(0).getNumber("T2");
                                    td3 = (int) objects.get(0).getNumber("T3");
                                    td4 = (int) objects.get(0).getNumber("T4");
                                    td5 = (int) objects.get(0).getNumber("T5");
                                    td6 = (int) objects.get(0).getNumber("T6");
                                    td7 = (int) objects.get(0).getNumber("T7");
                                    td8 = (int) objects.get(0).getNumber("T8");
                                    unamed = String.valueOf(objects.get(0).getString("username"));
                                    styled = String.valueOf(objects.get(0).getString("StyleNo"));
                                    Toast.makeText(UserList.this, "Getting Data From Line D Succes !", Toast.LENGTH_SHORT).show();
                                    progressDialogd.dismiss();


                                }

                            } else {
                                Toast.makeText(UserList.this, "Cant get the Data of Line D ", Toast.LENGTH_LONG).show();
                                progressDialogd.dismiss();
                            }
                        } else {
                            Toast.makeText(UserList.this, "Cant get the Data of Line D", Toast.LENGTH_LONG).show();
                            progressDialogd.dismiss();

                        }
                    }
                });

    }

    private void FetchdataofLineE(String line) {
        final ProgressDialog progressDialoge = new ProgressDialog(UserList.this);
        progressDialoge.setTitle("Please wait..");
        progressDialoge.setMessage("Getting data...");
        progressDialoge.setCancelable(false);
        progressDialoge.show();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");
        query.whereEqualTo("date", Da).whereEqualTo("LineNo", line).findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    totalpe = (int) objects.get(0).getNumber("TotalProduction");
                                    pe1 = (int) objects.get(0).getNumber("P1");
                                    pe2 = (int) objects.get(0).getNumber("P2");
                                    pe3 = (int) objects.get(0).getNumber("P3");
                                    pe4 = (int) objects.get(0).getNumber("P4");
                                    pe5 = (int) objects.get(0).getNumber("P5");
                                    pe6 = (int) objects.get(0).getNumber("P6");
                                    pe7 = (int) objects.get(0).getNumber("P7");
                                    pe8 = (int) objects.get(0).getNumber("P8");

                                    totalte = (int) objects.get(0).getNumber("TotalTarget");
                                    te1 = (int) objects.get(0).getNumber("T1");
                                    te2 = (int) objects.get(0).getNumber("T2");
                                    te3 = (int) objects.get(0).getNumber("T3");
                                    te4 = (int) objects.get(0).getNumber("T4");
                                    te5 = (int) objects.get(0).getNumber("T5");
                                    te6 = (int) objects.get(0).getNumber("T6");
                                    te7 = (int) objects.get(0).getNumber("T7");
                                    te8 = (int) objects.get(0).getNumber("T8");
                                    unamee = String.valueOf(objects.get(0).getString("username"));
                                    stylee = String.valueOf(objects.get(0).getString("StyleNo"));

                                    Toast.makeText(UserList.this, "Getting Data From Line E Succes !", Toast.LENGTH_SHORT).show();
                                    progressDialoge.dismiss();


                                }

                            } else {
                                Toast.makeText(UserList.this, "Cant get the Data of Line E ", Toast.LENGTH_LONG).show();
                                progressDialoge.dismiss();
                            }
                        } else {
                            Toast.makeText(UserList.this, "Cant get the Data of Line E", Toast.LENGTH_LONG).show();
                            progressDialoge.dismiss();

                        }
                    }
                });
    }

    private void FetchdataofLineF(String line) {
        final ProgressDialog progressDialogf = new ProgressDialog(UserList.this);
        progressDialogf.setTitle("Please wait..");
        progressDialogf.setMessage("Getting data...");
        progressDialogf.setCancelable(false);
        progressDialogf.show();
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");
        query.whereEqualTo("date", Da).whereEqualTo("LineNo", line).findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    totalpf = (int) objects.get(0).getNumber("TotalProduction");
                                    pf1 = (int) objects.get(0).getNumber("P1");
                                    pf2 = (int) objects.get(0).getNumber("P2");
                                    pf3 = (int) objects.get(0).getNumber("P3");
                                    pf4 = (int) objects.get(0).getNumber("P4");
                                    pf5 = (int) objects.get(0).getNumber("P5");
                                    pf6 = (int) objects.get(0).getNumber("P6");
                                    pf7 = (int) objects.get(0).getNumber("P7");
                                    pf8 = (int) objects.get(0).getNumber("P8");

                                    totaltf = (int) objects.get(0).getNumber("TotalTarget");
                                    tf1 = (int) objects.get(0).getNumber("T1");
                                    tf2 = (int) objects.get(0).getNumber("T2");
                                    tf3 = (int) objects.get(0).getNumber("T3");
                                    tf4 = (int) objects.get(0).getNumber("T4");
                                    tf5 = (int) objects.get(0).getNumber("T5");
                                    tf6 = (int) objects.get(0).getNumber("T6");
                                    tf7 = (int) objects.get(0).getNumber("T7");
                                    tf8 = (int) objects.get(0).getNumber("T8");
                                    unamef = String.valueOf(objects.get(0).getString("username"));
                                    stylef = String.valueOf(objects.get(0).getString("StyleNo"));
                                    Toast.makeText(UserList.this, "Getting Data From Line F Succes !", Toast.LENGTH_SHORT).show();
                                    progressDialogf.dismiss();

                                }

                            } else {
                                Toast.makeText(UserList.this, "Cant get the Data of Line F ", Toast.LENGTH_LONG).show();
                                progressDialogf.dismiss();
                            }
                        } else {
                            Toast.makeText(UserList.this, "Cant get the Data of Line F", Toast.LENGTH_LONG).show();
                            progressDialogf.dismiss();

                        }
                    }
                });
    }


    public void setSpinnerItems() {
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("A");
        categories.add("B");
        categories.add("C");
        categories.add("D");
        categories.add("E");
        categories.add("F");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Lineno.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showDate(int year, int month, int day) {
        date.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }


    ///for date

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(UserList.this, "Please Download the Data Now ", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };


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
                Intent i = new Intent(UserList.this,About.class);
                startActivity(i);
                return true;

            case R.id.get:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    Da = String.valueOf(date.getText());
                    alltheDataisFetched = false;
                    FetchdataofLineA("LineA");
                    FetchdataofLineB("LineB");
                    FetchdataofLineC("LineC");
                    FetchdataofLineD("LineD");
                    FetchdataofLineE("LineE");
                    FetchdataofLineF("LineF");
                } else {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(UserList.this, "Please Check Your Internet Connection and Then Refresh");

                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean("fetchDataAutomatically", fetchDataAutomatically);
        editor.commit();


    }

}

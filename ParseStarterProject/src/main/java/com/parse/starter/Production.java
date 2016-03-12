package com.parse.starter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Nilesh Verma on 3/5/2016.
 */
public class Production extends AppCompatActivity {
    String Date;
    ParseQuery<ParseObject> query;
    TextView OA, OB, OC, OD, OE, OF;
    TextView TA, TB, TC, TD, TE, TF;
    TextView EA, EB, EC, ED, EE, EF;
    TextView BA, BB, BC, BD, BE, BF;
    TextView LA, LB, LC, LD, LE, LF;
    TextView SA, SB, SC, SD, SE, SF;
    int tpa = 0, tpb = 0, tpc = 0, tpd = 0, tpe = 0, tpf = 0;
    int tta = 0, ttb = 0, ttc = 0, ttd = 0, tte = 0, ttf = 0;
    int efa = 0, efb = 0, efc = 0, efd = 0, efe = 0, eff = 0;
    String sta="-",stb="-",stc="-",std="-",ste="-",stf="-";
    ProgressDialog pd;
    Button Efficiency, Refresh;
    ConnectionDetector cd;

    // flag for Internet connection status
    Boolean isInternetPresent = false;
    Boolean isallDataFetched;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.production);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer




        cd = new ConnectionDetector(getApplicationContext());
        initilizeViews();
        Intent i = getIntent();

         isallDataFetched = i.getBooleanExtra("loadData",false);

        if(isallDataFetched){
            checkandUpdate();
        }

        Date = i.getStringExtra("date");
        tpa = i.getIntExtra("tpa", 0);
        tpb = i.getIntExtra("tpb", 0);
        tpc = i.getIntExtra("tpc", 0);
        tpd = i.getIntExtra("tpd", 0);
        tpe = i.getIntExtra("tpe", 0);
        tpf = i.getIntExtra("tpf", 0);

        tta = i.getIntExtra("tta", 0);
        ttb = i.getIntExtra("ttb", 0);
        ttc = i.getIntExtra("ttc", 0);
        ttd = i.getIntExtra("ttd", 0);
        tte = i.getIntExtra("tte", 0);
        ttf = i.getIntExtra("ttf", 0);

        sta = i.getStringExtra("stylea");
        stb = i.getStringExtra("styleb");
        stc = i.getStringExtra("stylec");
        std = i.getStringExtra("styled");
        ste = i.getStringExtra("stylee");
        stf = i.getStringExtra("stylef");


        OA.setText(String.valueOf(tpa));
        OB.setText(String.valueOf(tpb));
        OC.setText(String.valueOf(tpc));
        OD.setText(String.valueOf(tpd));
        OE.setText(String.valueOf(tpe));
        OF.setText(String.valueOf(tpf));

        TA.setText(String.valueOf(tta));
        TB.setText(String.valueOf(ttb));
        TC.setText(String.valueOf(ttc));
        TD.setText(String.valueOf(ttd));
        TE.setText(String.valueOf(tte));
        TF.setText(String.valueOf(ttf));

        SA.setText(sta);
        SB.setText(stb);
        SC.setText(stc);
        SD.setText(std);
        SE.setText(ste);
        SF.setText(stf);

        setTitle("Production" + ": " + "( " + Date + " )");

        int bala = tta - tpa;
        int balb = ttb - tpb;
        int balc = ttc - tpc;
        int bald = ttd - tpd;
        int bale = tte - tpe;
        int balf = ttf - tpf;

        if (tta != 0) {
            efa = (tpa * 100 / tta);
        }
        if (ttb != 0) {
            efb = (tpb * 100 / ttb);
        }
        if (ttc != 0) {
            efc = (tpc * 100 / ttc);
        }
        if (ttd != 0) {
            efd = (tpd * 100 / ttd);
        }
        if (tte != 0) {
            efe = (tpe * 100 / tte);
        }
        if (ttf != 0) {
            eff = (tpf * 100 / ttf);
        }
        BA.setText(String.valueOf(bala));
        BB.setText(String.valueOf(balb));
        BC.setText(String.valueOf(balc));
        BD.setText(String.valueOf(bald));
        BE.setText(String.valueOf(bale));
        BF.setText(String.valueOf(balf));


        EA.setText(String.valueOf(efa));
        EB.setText(String.valueOf(efb));
        EC.setText(String.valueOf(efc));
        ED.setText(String.valueOf(efd));
        EE.setText(String.valueOf(efe));
        EF.setText(String.valueOf(eff));


        //

    }

    public void DataUpdated() {
        ///for the Progress Bar to get Data
        pd = new ProgressDialog(Production.this);
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
        query = new ParseQuery<ParseObject>("LineA");

        //  query.whereExists("P1");
        //  query.whereExists("Asd");
        query.orderByDescending("createdAt");
        query.whereEqualTo("date", Date).whereEqualTo("LineNo", "LineA").findInBackground(
                new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {
                                {
                                    Toast.makeText(getApplicationContext(), "Getting Data from Line A", Toast.LENGTH_SHORT).show();

                                    queryLineB(objects);
                                }

                            } else {

                                Toast.makeText(getApplicationContext(), "Can't get Data of Line A", Toast.LENGTH_SHORT).show();
                                //  queryLineB(objects);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Can't get Data of Line A", Toast.LENGTH_SHORT).show();
                            //   queryLineB(objects);
                        }
                    }

                    public void queryLineB(List<ParseObject> objects) {
                        final String TotalProductiionA = String.valueOf(objects.get(0).getNumber("TotalProduction"));
                        final String TotalTargetA = String.valueOf(objects.get(0).getNumber("TotalTarget"));
                        final String StyleA = objects.get(0).getString("StyleNo");

                        query.whereEqualTo("date", Date).whereEqualTo("LineNo", "LineB").findInBackground(
                                new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e) {
                                        if (e == null) {
                                            if (objects.size() > 0) {
                                                {
                                                    Toast.makeText(getApplicationContext(), "Getting Data from Line B", Toast.LENGTH_SHORT).show();

                                                    querryLineC(objects);

                                                }

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Can't get Data of Line B", Toast.LENGTH_SHORT).show();
                                                querryLineC(objects);
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Can't get Data of Line B", Toast.LENGTH_SHORT).show();
                                            querryLineC(objects);
                                        }
                                    }

                                    public void querryLineC(List<ParseObject> objects) {

                                        final String TotalProductiionB = String.valueOf(objects.get(0).getNumber("TotalProduction"));
                                        final String TotalTargetB = String.valueOf(objects.get(0).getNumber("TotalTarget"));
                                        final String StyleB = objects.get(0).getString("StyleNo");

                                        query.whereEqualTo("date", Date).whereEqualTo("LineNo", "LineC").findInBackground(
                                                new FindCallback<ParseObject>() {
                                                    @Override
                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                        if (e == null) {
                                                            if (objects.size() > 0) {
                                                                {
                                                                    Toast.makeText(getApplicationContext(), "Getting Data from Line C", Toast.LENGTH_SHORT).show();

                                                                    querryLineD(objects);

                                                                }

                                                            } else {
                                                                Toast.makeText(getApplicationContext(), "Can't get Data of Line C", Toast.LENGTH_SHORT).show();
                                                                querryLineD(objects);
                                                            }
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Can't get Data of Line C", Toast.LENGTH_SHORT).show();
                                                            querryLineD(objects);
                                                        }
                                                    }

                                                    public void querryLineD(List<ParseObject> objects) {

                                                        final String TotalProductiionC = String.valueOf(objects.get(0).getNumber("TotalProduction"));
                                                        final String TotalTargetC = String.valueOf(objects.get(0).getNumber("TotalTarget"));
                                                        final String StyleC = objects.get(0).getString("StyleNo");

                                                        query.whereEqualTo("date", Date).whereEqualTo("LineNo", "LineD").findInBackground(
                                                                new FindCallback<ParseObject>() {
                                                                    @Override
                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                        if (e == null) {
                                                                            if (objects.size() > 0) {
                                                                                {
                                                                                    Toast.makeText(getApplicationContext(), "Getting Data from Line D", Toast.LENGTH_SHORT).show();

                                                                                    queryLineE(objects);

                                                                                }

                                                                            } else {
                                                                                Toast.makeText(getApplicationContext(), "Can't get Data of Line D", Toast.LENGTH_SHORT).show();
                                                                                queryLineE(objects);
                                                                            }
                                                                        } else {
                                                                            Toast.makeText(getApplicationContext(), "Can't get Data of Line D", Toast.LENGTH_SHORT).show();
                                                                            queryLineE(objects);
                                                                        }
                                                                    }

                                                                    public void queryLineE(List<ParseObject> objects) {

                                                                        final String TotalProductiionD = String.valueOf(objects.get(0).getNumber("TotalProduction"));
                                                                        final String TotalTargetD = String.valueOf(objects.get(0).getNumber("TotalTarget"));
                                                                        final String StyleD = objects.get(0).getString("StyleNo");


                                                                        query.whereEqualTo("date", Date).whereEqualTo("LineNo", "LineE").findInBackground(
                                                                                new FindCallback<ParseObject>() {
                                                                                    @Override
                                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                                        if (e == null) {
                                                                                            if (objects.size() > 0) {
                                                                                                {
                                                                                                    Toast.makeText(getApplicationContext(), "Getting Data from Line E", Toast.LENGTH_SHORT).show();

                                                                                                    queryLineF(objects);
                                                                                                }

                                                                                            } else {
                                                                                                Toast.makeText(getApplicationContext(), "Can't get Data of Line E", Toast.LENGTH_SHORT).show();
                                                                                                queryLineF(objects);
                                                                                            }
                                                                                        } else {
                                                                                            Toast.makeText(getApplicationContext(), "Can't get Data of Line E", Toast.LENGTH_SHORT).show();
                                                                                            queryLineF(objects);
                                                                                        }
                                                                                    }

                                                                                    public void queryLineF(List<ParseObject> objects) {
                                                                                        final String TotalProductiionE = String.valueOf(objects.get(0).getNumber("TotalProduction"));
                                                                                        final String TotalTargetE = String.valueOf(objects.get(0).getNumber("TotalTarget"));
                                                                                        final String StyleE = objects.get(0).getString("StyleNo");

                                                                                        query.whereEqualTo("date", Date).whereEqualTo("LineNo", "LineF").findInBackground(
                                                                                                new FindCallback<ParseObject>() {
                                                                                                    @Override
                                                                                                    public void done(List<ParseObject> objects, ParseException e) {
                                                                                                        if (e == null) {
                                                                                                            if (objects.size() > 0) {
                                                                                                                {
                                                                                                                    String TotalProductiionF = String.valueOf(objects.get(0).getNumber("TotalProduction"));
                                                                                                                    String TotalTargetF = String.valueOf(objects.get(0).getNumber("TotalTarget"));
                                                                                                                    final String StyleF = objects.get(0).getString("StyleNo");

                                                                                                                    OA.setText(TotalProductiionA);
                                                                                                                    OB.setText(TotalProductiionB);
                                                                                                                    OC.setText(TotalProductiionC);
                                                                                                                    OD.setText(TotalProductiionD);
                                                                                                                    OE.setText(TotalProductiionE);
                                                                                                                    OF.setText(TotalProductiionF);

                                                                                                                    TA.setText(TotalTargetA);
                                                                                                                    TB.setText(TotalTargetB);
                                                                                                                    TC.setText(TotalTargetC);
                                                                                                                    TD.setText(TotalTargetD);
                                                                                                                    TE.setText(TotalTargetE);
                                                                                                                    TF.setText(TotalTargetF);

                                                                                                                    SA.setText(StyleA);
                                                                                                                    SB.setText(StyleB);
                                                                                                                    SC.setText(StyleC);
                                                                                                                    SD.setText(StyleD);
                                                                                                                    SE.setText(StyleE);
                                                                                                                    SF.setText(StyleF);


                                                                                                                    String ba = String.valueOf(Integer.parseInt(String.valueOf(TA.getText())) - Integer.parseInt(String.valueOf(OA.getText())));
                                                                                                                    String bb = String.valueOf(Integer.parseInt(String.valueOf(TB.getText())) - Integer.parseInt(String.valueOf(OB.getText())));
                                                                                                                    String bc = String.valueOf(Integer.parseInt(String.valueOf(TC.getText())) - Integer.parseInt(String.valueOf(OC.getText())));
                                                                                                                    String bd = String.valueOf(Integer.parseInt(String.valueOf(TD.getText())) - Integer.parseInt(String.valueOf(OD.getText())));
                                                                                                                    String be = String.valueOf(Integer.parseInt(String.valueOf(TE.getText())) - Integer.parseInt(String.valueOf(OE.getText())));
                                                                                                                    String bf = String.valueOf(Integer.parseInt(String.valueOf(TF.getText())) - Integer.parseInt(String.valueOf(OF.getText())));

                                                                                                                    ///for efficiency
                                                                                                                    int ea = 0, eb = 0, ec = 0, ed = 0, ee = 0, ef = 0;

                                                                                                                    if (Integer.parseInt(String.valueOf(TA.getText())) != 0) {
                                                                                                                        ea = Integer.parseInt(String.valueOf(OA.getText())) * 100 / Integer.parseInt(String.valueOf(TA.getText()));
                                                                                                                    }
                                                                                                                    if (Integer.parseInt(String.valueOf(TB.getText())) != 0) {
                                                                                                                        eb = (Integer.parseInt(String.valueOf(OB.getText())) * 100 / Integer.parseInt(String.valueOf(TB.getText())));
                                                                                                                    }
                                                                                                                    if (Integer.parseInt(String.valueOf(TC.getText())) != 0) {
                                                                                                                        ec = (Integer.parseInt(String.valueOf(OC.getText())) * 100 / Integer.parseInt(String.valueOf(TC.getText())));
                                                                                                                    }
                                                                                                                    if (Integer.parseInt(String.valueOf(TD.getText())) != 0) {

                                                                                                                        ed = (Integer.parseInt(String.valueOf(OD.getText())) * 100 / Integer.parseInt(String.valueOf(TD.getText())));
                                                                                                                    }
                                                                                                                    if (Integer.parseInt(String.valueOf(TE.getText())) != 0) {
                                                                                                                        ee = (Integer.parseInt(String.valueOf(OE.getText())) * 100 / Integer.parseInt(String.valueOf(TE.getText())));
                                                                                                                    }
                                                                                                                    if (Integer.parseInt(String.valueOf(TF.getText())) != 0) {
                                                                                                                        ef = (Integer.parseInt(String.valueOf(OF.getText())) * 100 / Integer.parseInt(String.valueOf(TF.getText())));
                                                                                                                    }

                                                                                                                    BA.setText(ba);
                                                                                                                    BB.setText(bb);
                                                                                                                    BC.setText(bc);
                                                                                                                    BD.setText(bd);
                                                                                                                    BE.setText(be);
                                                                                                                    BF.setText(bf);

                                                                                                                    EA.setText(String.valueOf(ea));
                                                                                                                    EB.setText(String.valueOf(eb));
                                                                                                                    EC.setText(String.valueOf(ec));
                                                                                                                    ED.setText(String.valueOf(ed));
                                                                                                                    EE.setText(String.valueOf(ee));
                                                                                                                    EF.setText(String.valueOf(ef));


                                                                                                                    pd.dismiss();


                                                                                                                }

                                                                                                            } else {
                                                                                                                Toast.makeText(getApplicationContext(), "Can't get Data of Line F", Toast.LENGTH_SHORT).show();
                                                                                                            }
                                                                                                        } else {
                                                                                                            Toast.makeText(getApplicationContext(), "Can't get Data of Line F", Toast.LENGTH_SHORT).show();
                                                                                                        }
                                                                                                    }
                                                                                                });
                                                                                    }
                                                                                });
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });


                    }
                });
    }

    public void initilizeViews() {

        OA = (TextView) findViewById(R.id.tvoa);
        OB = (TextView) findViewById(R.id.tvob);
        OC = (TextView) findViewById(R.id.tvoc);
        OD = (TextView) findViewById(R.id.tvod);
        OE = (TextView) findViewById(R.id.tvoe);
        OF = (TextView) findViewById(R.id.tvof);

        TA = (TextView) findViewById(R.id.tvta);
        TB = (TextView) findViewById(R.id.tvtb);
        TC = (TextView) findViewById(R.id.tvtc);
        TD = (TextView) findViewById(R.id.tvtd);
        TE = (TextView) findViewById(R.id.tvte);
        TF = (TextView) findViewById(R.id.tvtf);

        BA = (TextView) findViewById(R.id.tvba);
        BB = (TextView) findViewById(R.id.tvbb);
        BC = (TextView) findViewById(R.id.tvbc);
        BD = (TextView) findViewById(R.id.tvbd);
        BE = (TextView) findViewById(R.id.tvbe);
        BF = (TextView) findViewById(R.id.tvbf);

        EA = (TextView) findViewById(R.id.tvea);
        EB = (TextView) findViewById(R.id.tveb);
        EC = (TextView) findViewById(R.id.tvec);
        ED = (TextView) findViewById(R.id.tved);
        EE = (TextView) findViewById(R.id.tvee);
        EF = (TextView) findViewById(R.id.tvef);

        SA = (TextView) findViewById(R.id.tvsa);
        SB = (TextView) findViewById(R.id.tvsb);
        SC = (TextView) findViewById(R.id.tvsc);
        SD = (TextView) findViewById(R.id.tvsd);
        SE = (TextView) findViewById(R.id.tvse);
        SF = (TextView) findViewById(R.id.tvsf);


        LA = (TextView) findViewById(R.id.tvla);
        LA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheLine("A");
            }
        });


        LB = (TextView) findViewById(R.id.tvlb);
        LB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheLine("B");
            }
        });

        LC = (TextView) findViewById(R.id.tvlc);
        LC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheLine("C");
            }
        });

        LD = (TextView) findViewById(R.id.tvld);
        LD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTheLine("D");
            }
        });

        LE = (TextView) findViewById(R.id.tvle);
        LE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTheLine("E");
            }
        });

        LF = (TextView) findViewById(R.id.tvlf);
        LF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTheLine("F");
            }
        });


        Efficiency = (Button) findViewById(R.id.bEfficiency);
        Efficiency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Production.this, Efficency.class);
                i.putExtra("OA", Float.parseFloat(String.valueOf(OA.getText())));
                i.putExtra("OB", Float.parseFloat(String.valueOf(OB.getText())));
                i.putExtra("OC", Float.parseFloat(String.valueOf(OC.getText())));
                i.putExtra("OD", Float.parseFloat(String.valueOf(OD.getText())));
                i.putExtra("OE", Float.parseFloat(String.valueOf(OE.getText())));
                i.putExtra("OF", Float.parseFloat(String.valueOf(OF.getText())));

                i.putExtra("TA", Float.parseFloat(String.valueOf(TA.getText())));
                i.putExtra("TB", Float.parseFloat(String.valueOf(TB.getText())));
                i.putExtra("TC", Float.parseFloat(String.valueOf(TC.getText())));
                i.putExtra("TD", Float.parseFloat(String.valueOf(TD.getText())));
                i.putExtra("TE", Float.parseFloat(String.valueOf(TE.getText())));
                i.putExtra("TF", Float.parseFloat(String.valueOf(TF.getText())));

                i.putExtra("EA", Float.parseFloat(String.valueOf(EA.getText())));
                i.putExtra("EB", Float.parseFloat(String.valueOf(EB.getText())));
                i.putExtra("EC", Float.parseFloat(String.valueOf(EC.getText())));
                i.putExtra("ED", Float.parseFloat(String.valueOf(ED.getText())));
                i.putExtra("EE", Float.parseFloat(String.valueOf(EE.getText())));
                i.putExtra("EF", Float.parseFloat(String.valueOf(EF.getText())));


                startActivity(i);

            }
        });

        Refresh = (Button) findViewById(R.id.bRefresh);
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkandUpdate();
            }
        });


    }

    private void checkandUpdate() {
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent == true ) {
            DataUpdated();
        } else {
            ViewDialog alert = new ViewDialog();
            alert.showDialog(Production.this, "Please Check Your Internet Connection and Then Refresh");
        }
    }

    private void openTheLine(String a) {
        Intent i = new Intent(Production.this, UserFeed.class);
        i.putExtra("date", Date);
        i.putExtra("lineno", a);
        i.putExtra("loadData", true);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.production_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.get) {
            checkandUpdate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.parse.starter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
///$$$$ always import import android.support.v4.app.Fragment; this fragment package
//$$ and for toolbar  import android.support.v7.widget.Toolbar;

public class NavigationDrawerFragment extends Fragment implements HitAdapter.ClickListner {


    private RecyclerView recyclerView;

    private static final String PREF_FILE_NAME = "test";
    private static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mdrawerToogle;
    private DrawerLayout mDrawerlayout;
    private boolean mUserLearnedDrawer;  //indicates weather the user is aware of the drawer or not
    private boolean mFromSavedInstanceState; //indicates wheater the fragment is started for the very first time or orientation is changed
    private HitAdapter adapter;
    private View ContainerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reading the value form the preferences
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        //1)context ,2)Name of the Pref ,3)default value

        if (savedInstanceState != null) { // this means that the app is comming back from a roatation
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        //adding the recycler view to the java code to manupulate it
        //recycler view needs a layoutmanager
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new HitAdapter(getActivity(), getData());
        adapter.setClickListner(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  //very important this sets the layout of the list to be vertical Linear layout


        return layout;
        // return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

    }


    public List<Information> getData() {
        //load only static data inside a drawer
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.bar, R.drawable.grid, R.drawable.pie, R.drawable.totalproa};
        String[] titles = {"Efficiency Graphs", "Production Bar Graph", "Production Pie Chart", "Total Production Table"};
        for (int i = 0; i < titles.length; i++) {
            Information information = new Information();
            information.title = titles[i];
            information.iconId = icons[i];
            data.add(information);
        }
        return data;
    }


    public void setUp(int FragmentID, DrawerLayout drawerLayout, final Toolbar toolbar) {
        ContainerView = getActivity().findViewById(FragmentID);

        mDrawerlayout = drawerLayout;
        mdrawerToogle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) { // if the user has never seen the navigationdrawer
                    mUserLearnedDrawer = true;
                    savedToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");

                }
                getActivity().invalidateOptionsMenu(); // it will partially hide the actionbar

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu(); // it will partially hide the actionbar
            }
            // this will change the alpha of the action bar acc to the slide

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        //takes the parameter Activity , Layout, toolbar , open and close

        if (!mUserLearnedDrawer) // the user has never seen the drawer and if it isthe veryfirst time in that case display the drawer
        {
            mDrawerlayout.openDrawer(ContainerView);
        }

        //implemnt the listner veryImportant
        mDrawerlayout.setDrawerListener(mdrawerToogle);

        mDrawerlayout.post(new Runnable() { // this method is for the HAMBURGER SYMBOL

            @Override
            public void run() {
                mdrawerToogle.syncState();
            }
        });
    }

    public static void savedToPreferences(Context context, String preferencesName, String preferenceValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(preferencesName, preferenceValue);
        editor.apply(); // using apply() instead of commit(); ,because its much more fast
    }

    public static String readFromPreferences(Context context, String preferencesName, String preferenceValue) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        return sharedPref.getString(preferencesName, preferenceValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        UserList activity = (UserList) getActivity();

        String date = String.valueOf(activity.date.getText());
        float pa = activity.totalpa;
        float pb = activity.totalpb;
        float pc = activity.totalpc;
        float pd = activity.totalpd;
        float pe = activity.totalpe;
        float pf = activity.totalpf;

        float ta = activity.totalta;
        float tb = activity.totaltb;
        float tc = activity.totaltc;
        float td = activity.totaltd;
        float te = activity.totalte;
        float tf = activity.totaltf;
        Boolean isDataFetched = activity.alltheDataisFetched;

        if (position == 0) {
            if (isDataFetched) {
                Intent i = new Intent(getActivity(), DataNotFetched.class);
                startActivity(i);
            } else {
                Intent i = new Intent(getActivity(), EfficiencyGraph.class);
                float ea = 0;
                float eb = 0;
                float ec = 0;
                float ed = 0;
                float ee = 0;
                float ef = 0;

                if (ta != 0) {
                    ea = pa * 100 / ta;
                }
                if (tb != 0) {
                    eb = pb * 100 / tb;
                }
                if (tc != 0) {
                    ec = pc * 100 / tc;
                }
                if (td != 0) {
                    ed = pd * 100 / td;
                }
                if (te != 0) {
                    ee = pe * 100 / te;
                }
                if (tf != 0) {
                    ef = pf * 100 / tf;
                }


                i.putExtra("EA", ea);
                i.putExtra("EB", eb);
                i.putExtra("EC", ec);
                i.putExtra("ED", ed);
                i.putExtra("EE", ee);
                i.putExtra("EF", ef);

                startActivity(i);
            }
        }
        if (position == 1) {
            if (isDataFetched) {
                Intent i = new Intent(getActivity(), DataNotFetched.class);
                startActivity(i);
            } else {

                Intent i = new Intent(getActivity(), BarGraphs.class);
                i.putExtra("OA", pa);
                i.putExtra("OB", pb);
                i.putExtra("OC", pc);
                i.putExtra("OD", pd);
                i.putExtra("OE", pe);
                i.putExtra("OF", pf);
                i.putExtra("TA", ta);
                i.putExtra("TB", tb);
                i.putExtra("TC", tc);
                i.putExtra("TD", td);
                i.putExtra("TE", te);
                i.putExtra("TF", tf);


                startActivity(i);
            }
        }

        if (position == 2) {
            if (isDataFetched) {
                Intent i = new Intent(getActivity(), DataNotFetched.class);
                startActivity(i);
            } else {
                Intent i = new Intent(getActivity(), PieCharts.class);
                i.putExtra("OA", pa);
                i.putExtra("OB", pb);
                i.putExtra("OC", pc);
                i.putExtra("OD", pd);
                i.putExtra("OE", pe);
                i.putExtra("OF", pf);
                startActivity(i);
            }

        }

        if (position == 3) {
            if (isDataFetched) {
                Intent i = new Intent(getActivity(), DataNotFetched.class);
                startActivity(i);
            } else {
                Intent i = new Intent(getActivity(), Production.class);

                String stylea = activity.stylea;
                String styleb = activity.styleb;
                String stylec = activity.stylec;
                String styled = activity.styled;
                String stylee = activity.stylee;
                String stylef = activity.stylef;

                i.putExtra("tpa", (int) pa);
                i.putExtra("tpb", (int) pb);
                i.putExtra("tpc", (int) pc);
                i.putExtra("tpd", (int) pd);
                i.putExtra("tpe", (int) pe);
                i.putExtra("tpf", (int) pf);

                i.putExtra("tta", (int) ta);
                i.putExtra("ttb", (int) tb);
                i.putExtra("ttc", (int) tc);
                i.putExtra("ttd", (int) td);
                i.putExtra("tte", (int) te);
                i.putExtra("ttf", (int) tf);
                i.putExtra("date", date);

                i.putExtra("stylea", stylea);
                i.putExtra("styleb", styleb);
                i.putExtra("stylec", stylec);
                i.putExtra("styled", styled);
                i.putExtra("stylee", stylee);
                i.putExtra("stylef", stylef);

                i.putExtra("loadData", isDataFetched);

                startActivity(i);
            }

        }
    }
}

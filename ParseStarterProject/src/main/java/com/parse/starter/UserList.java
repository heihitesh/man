package com.parse.starter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserList extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    TextView date;
    Spinner Lineno;
    Button Send, LogOut;
    String item;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
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
                isInternetPresent = cd.isConnectingToInternet();

                if (isInternetPresent) {
                    Intent i = new Intent(getApplicationContext(), UserFeed.class);
                    i.putExtra("date", String.valueOf(date.getText()));
                    i.putExtra("lineno", String.valueOf(item));
                    startActivity(i);
                }else{
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(UserList.this, "Please Check Your Internet Connection ");
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
        categories.add("G");

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

}

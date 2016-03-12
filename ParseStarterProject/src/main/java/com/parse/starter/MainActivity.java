/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

    EditText Username, Password;
    Boolean signUpModeActive;
    Button SignUp, Login;
    LinearLayout layout;
    ImageView logo;
    // flag for Internet connection status
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;


    void education() {
        //put in Oncreate() method


    /* -----------------------FOR CREATING OBJECTS----------------------
    ParseObject score = new ParseObject("Score");
    score.put("useername","nil");
    score.put("score", 10);
    //````saveEventually(); ````//save the data in local base and when the user is online it saves it to parse
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {

        if (e == null){
          Toast.makeText(MainActivity.this,"Saved Succesfully",Toast.LENGTH_LONG).show();
        }
        else{
          Toast.makeText(MainActivity.this,"Error: "+e,Toast.LENGTH_LONG).show();

        }
      }
    });
*/
  /*----------------------- FOR UPDATING ANY ENRTY ----------------------
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

    query.getInBackground("Pim6tOMwyK", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
       if(e == null ){
         object.put("score",203);
         object.saveInBackground();
       }
      }
    });

*/

  /*  //---------------------- FOR FINDING ANYTHING ----------------------
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

    //<<<<<<<<<<<<<<
           //query.whereEqualTo("useername","nilesh");  //finds a specifically cell data(score in this case)
           //query.setLimit(10); //set the limit to view only some users
    //>>>>>>>>>>>>>>>

    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if(e == null){
          Toast.makeText(MainActivity.this,"Retrived " + objects.size() +" results",Toast.LENGTH_SHORT).show();

        for(ParseObject object : objects ){
          Log.i("HIT",String.valueOf(object.get("useername"))); //$
        }
      }}
    });
    */

    /*-----------------------FINDING A USER BY ITS USERNAME AND THEN UPDATE IT SCORE ----------
    ParseQuery<ParseObject> query= ParseQuery.getQuery("Score");
    query.whereEqualTo("useername","nilesh");
    query.setLimit(1);
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {

        if(e == null){
          if(objects.size() >0){
            objects.get(0).put("score", 1130);
            objects.get(0).saveInBackground();
          }
        }

      }
    });
    */

    /* ------------- SIGNING NEW USER ----------------------
    ParseUser user = new ParseUser();
    user.setUsername("Hitesh Verma");
    user.setPassword("heloo");
    user.setEmail("itshiteshverma@gmail.com");

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
       if (e == null){
         Toast.makeText(MainActivity.this,"User Signed Up Successfully",Toast.LENGTH_LONG).show();
       }
        else{
         Toast.makeText(MainActivity.this,"Error :" +e,Toast.LENGTH_LONG).show();

       }
      }
    });
    */

    /*-------------------LOGING IN THE USER ---------------------------
    //Once Loged in it will be saved when it is run again (saved form now)
    ParseUser.logInInBackground("Hitesh Verma", "heloo", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {

        if(user != null){
          Toast.makeText(MainActivity.this,"Login Successful ",Toast.LENGTH_LONG).show();
        }else{
          Toast.makeText(MainActivity.this,"Login UnSuccessful ",Toast.LENGTH_LONG).show();

        }

      }
    });
    */

    /*-----------------CHECK WHEATHER THE USER IS LOGED IN OR NOT -------------
    if(ParseUser.getCurrentUser() != null){
      Toast.makeText(MainActivity.this,"User Loged In",Toast.LENGTH_SHORT).show();
    }else{
      Toast.makeText(MainActivity.this,"User NotLoged In",Toast.LENGTH_SHORT).show();

    }*/

    /*----------------LOGING-OUT THE USER --------------------
      ParseUser.LogOut();
     */

    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBar);
        // now we have to say android that we are not using ur toolbar
        // we are making our own toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true); // this will add a home button to the navigation drawer
        cd = new ConnectionDetector(getApplicationContext());


        setTitle("SmarTrack");
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        if (ParseUser.getCurrentUser() != null) {
            showUserList();
        }

        signUpModeActive = true;

        Username = (EditText) findViewById(R.id.etUserName);
        Password = (EditText) findViewById(R.id.etPassword);
        SignUp = (Button) findViewById(R.id.bSignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    final ProgressDialog progressDialoga = new ProgressDialog(MainActivity.this);
                    progressDialoga.setTitle("Please wait..");
                    progressDialoga.setMessage("Signing Up");
                    progressDialoga.setCancelable(false);
                    progressDialoga.show();
                    ParseUser user = new ParseUser();
                    user.setUsername(String.valueOf(Username.getText()));
                    user.setPassword(String.valueOf(Password.getText()));
                    user.signUpInBackground(new SignUpCallback() {

                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(MainActivity.this, "Sign Up Success Please Login in Now ", Toast.LENGTH_LONG).show();
                                //showUserList();
                                progressDialoga.dismiss();

                            } else {
                                Toast.makeText(MainActivity.this, "Login Un-Successfull " + "\n" +
                                        e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                                progressDialoga.dismiss();

                            }
                        }
                    });
                } else {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(MainActivity.this, "Please Check Your Internet Connection and Then Retry");

                }


            }
        });
        Login = (Button) findViewById(R.id.bLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    final ProgressDialog progressDialoga = new ProgressDialog(MainActivity.this);
                    progressDialoga.setTitle("Please wait..");
                    progressDialoga.setMessage("Logging In");
                    progressDialoga.setCancelable(false);
                    progressDialoga.show();
                    ParseUser.logInInBackground(String.valueOf(Username.getText()), String.valueOf(Password.getText()),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null) {
                                        Toast.makeText(getApplicationContext(), "Login Successfull",
                                                Toast.LENGTH_LONG).show();
                                        progressDialoga.dismiss();
                                        showUserList();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Login Un-Successfull " + "\n" +
                                                e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_LONG).show();
                                        progressDialoga.dismiss();


                                    }
                                }
                            });
                } else {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(MainActivity.this, "Please Check Your Internet Connection and Then Retry");

                }


            }
        });

        logo = (ImageView) findViewById(R.id.ivLogo);
        layout = (LinearLayout) findViewById(R.id.lLayout);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makes the keyborad disappear
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makes the keyborad disappear
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showUserList() {
        Intent i = new Intent(getApplicationContext(), SplashScreen.class);
        startActivity(i);
    }
}

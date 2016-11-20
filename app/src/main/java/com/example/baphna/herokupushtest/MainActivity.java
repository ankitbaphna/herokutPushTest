package com.example.baphna.herokupushtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (ParseUser.getCurrentUser() != null) {
            startWithCurrentUser();
        } else {
            login();
        }
        ParsePush.subscribeInBackground("channelName");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*HashMap<String, String> payload = new HashMap<>();
                payload.put("customData", "My message");
                ParseCloud.callFunctionInBackground("pushChannelTest", payload);*/
                    HashMap<String, String> data = new HashMap<>();
                    data.put("customData", "check");
                    data.put("channel", "channelName");
                    // The code that processes this function is listed at:
                    // https://github.com/rogerhu/parse-server-push-marker-example/blob/master/cloud/main.js
                    ParseCloud.callFunctionInBackground("pushToChannel", data);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e("DEBUG", "Anonymous login failed: ", e);
                } else {
                    startWithCurrentUser();
                }
            }
        });
    }

    private void startWithCurrentUser() {

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
}

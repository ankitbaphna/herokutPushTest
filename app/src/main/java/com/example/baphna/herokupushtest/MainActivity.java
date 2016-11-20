package com.example.baphna.herokupushtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

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

        ParsePush.subscribeInBackground("android-2016");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*HashMap<String, String> payload = new HashMap<>();
                payload.put("customData", "My message");
                ParseCloud.callFunctionInBackground("pushChannelTest", payload);*/

                try {
                    JSONObject payload = getPayloadFromMarker();
                    HashMap<String, String> data = new HashMap<>();
                    data.put("customData", payload.toString());
                    data.put("channel", "android-2016");
                    // The code that processes this function is listed at:
                    // https://github.com/rogerhu/parse-server-push-marker-example/blob/master/cloud/main.js
                    ParseCloud.callFunctionInBackground("pushToChannel", data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
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

    public JSONObject getPayloadFromMarker()  throws JSONException{
        JSONObject payload = new JSONObject();
        payload.put("location", "location");
        payload.put("title","title");
        payload.put("snippet", "Snippet");


        payload.put("userId", ParseUser.getCurrentUser().getObjectId());
        return payload;
    }
}

package com.example.baphna.herokupushtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by baphna on 11/19/2016.
 */
public class MyCustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Ankit", "onrecieve");
    }
}

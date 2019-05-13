package com.example.batterylevel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by michaelbui on 24/3/18.
 */

public class PomodoroReceiver extends BroadcastReceiver {
    public int out=-10;

    @Override
    public void onReceive(final Context context, Intent intent) {
        out=Integer.parseInt(intent.getDataString());
    }

    public int getOut(){
        out++;
        return out;
    }
}

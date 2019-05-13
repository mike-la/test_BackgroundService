package com.example.batterylevel;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugins.GeneratedPluginRegistrant;


public class MainActivity extends FlutterActivity  {
  private static final String CHANNEL = "samples.flutter.io/battery";


  private int counter=0;
  private boolean run=true; ///needful??
  PomodoroReceiver pomRec=new PomodoroReceiver();

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
            new MethodCallHandler() {
                @Override
                public void onMethodCall(MethodCall call, Result result) {

                    if (call.method.equals("getBatteryLevel")) {
                        int output = getBatteryLevel();

                        result.success(output);
                    } else {
                        result.notImplemented();
                    }
                }

            });
  }


    private int getBatteryLevel() {
        int batteryLevel = -1;
        /*if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }*/
        return pomRec.getOut();
    }

    @TargetApi(VERSION_CODES.CUPCAKE)
    private void repeatNotification(Context context, String text) {

        AlarmManager alarmManager = getAlarmManager(context);
        Intent notificationIntent = new Intent(context, pomRec.getClass());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, counter, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        long repeatInterval = 1000; //every second

        // ensure that start time is in the future
        long currentTime = System.currentTimeMillis();

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, currentTime, repeatInterval, pendingIntent);

    }

    private static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

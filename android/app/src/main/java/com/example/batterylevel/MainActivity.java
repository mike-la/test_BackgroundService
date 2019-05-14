package com.example.batterylevel;

import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugins.GeneratedPluginRegistrant;


public class MainActivity extends FlutterActivity  {
  private static final String CHANNEL = "counterChannel";
  private long counter;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
    counter=0;

    new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
            new MethodCallHandler() {
                @Override
                public void onMethodCall(MethodCall call, Result result) {
                    if(call.method.equals("getCounter")){
                        counter++;
                        result.success(counter);
                    }
                    else{
                        result.notImplemented();
                    }
                }

            });
  }


  protected void onDestroy(){
      super.onDestroy();
  }

    @Override
    protected void onSaveInstanceState(Bundle outState){
      super.onSaveInstanceState(outState);
      outState.putLong("counter", counter);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
      super.onRestoreInstanceState(savedInstanceState);

      counter=savedInstanceState.getLong("counter");

    }

}

import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

void main() {
  runApp(new MaterialApp(
    home: new Scaffold(
      body: new MyHomePage(),
    ),
  ));
}


class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() {
    return new _MyHomePageState();
  }
}


class _MyHomePageState extends State<MyHomePage> {
  static const  _channel = const MethodChannel('counterChannel');

  int counter=0;

  String startStopBtnText="Start";
  ColorSwatch startStopBtnColor=Colors.green;
  //notifications notification;


  @override
  Widget build(BuildContext context) {
    return new Align(
      alignment: Alignment.center,
      child: Container(
        child: Column(
          children: <Widget>[
            SizedBox(
                width: MediaQuery.of(context).size.width, //screen width
                height: 100.0,
                child:
                RaisedButton(
                  color: startStopBtnColor,
                  onPressed: () {startStopButtonClicked();},
                  child: Text(
                      '$startStopBtnText',
                      style: TextStyle(fontSize: 40)
                  ),
                )
            ),

            Container(
              color: Colors.blue,
              width: MediaQuery.of(context).size.width, //screen width
              height: 200.0,
              child: FittedBox(
                  fit: BoxFit.contain,
                  child: Text(
                    "$counter",
                    textScaleFactor: 0.8,
                    style: TextStyle(fontSize: 20.0, letterSpacing: 2.0, fontWeight: FontWeight.bold),
                    textAlign: TextAlign.center,
                  )
              ),
            ),
          ],
        ),
      ),
    );
  }

  void startStopButtonClicked() async{
    counter = await _channel.invokeMethod('getCounter');
    this.setState((){
      counter;
    });
  }

}
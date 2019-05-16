import 'package:flutter/material.dart';
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

  ColorSwatch startStopBtnColor=Colors.green;


  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text('counter via channel'),
      ),
      body: Center(
        child: Container(
          child: Column(
            children: <Widget>[
              SizedBox(
                  width: MediaQuery.of(context).size.width, //screen width
                  height: 100.0,
                  child: RaisedButton(
                    color: startStopBtnColor,
                    onPressed: () {
                      incrementClicked();
                    },
                    child: Text('increment',
                        style: TextStyle(fontSize: 40)),
                  )),
              Container(
                // color: Colors.blue,
                width: MediaQuery.of(context).size.width, //screen width
                height: 200.0,
                child: FittedBox(
                    fit: BoxFit.contain,
                    child: Text(
                      "$counter",
                      textScaleFactor: 0.8,
                      style: TextStyle(
                          fontSize: 20.0,
                          letterSpacing: 2.0,
                          fontWeight: FontWeight.bold),
                      textAlign: TextAlign.center,
                    )),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void incrementClicked() async{
    counter = await _channel.invokeMethod('getCounter');
    this.setState((){
      counter;
    });
  }

}
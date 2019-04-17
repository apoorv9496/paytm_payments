import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:paytm_payments/paytm_payments.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();

    initPayment();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPayment() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {

      await PaytmPayments.makePaytmPayment(
        "[YOUR_MERCHANT_ID]",
        "[YOUR_CHECKSUM_URL]",
        customerId: "[UNIQUE_ID_FOR_YOUR_CUSTOMER]",
        orderId: "[UNIQUE_ID_FOR_YOUR_ORDER]",
        staging: false,
      );
    } on Exception {

    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Paytm Payment Plugin'),
        ),
        body: Center(
          child: Text('Exmaple App'),
        ),
      ),
    );
  }
}

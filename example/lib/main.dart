import 'dart:async';
import 'package:flutter/material.dart';
import 'package:paytm_payments/paytm_payments.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

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
        "rxazcv89315285244163", // [YOUR_MERCHANT_ID] (required field)
        "https://ajax8732.000webhostapp.com/generateChecksum.php", // [YOUR_CHECKSUM_URL] (required field)
        customerId: "56789", // [UNIQUE_ID_FOR_YOUR_CUSTOMER] (auto generated if not specified)
        orderId: "51234", // [UNIQUE_ID_FOR_YOUR_ORDER] (auto generated if not specified)
        txnAmount: "10.0", // default: 10.0
        channelId: "WAP", // default: WAP (STAGING value)
        industryTypeId: "Retail", // default: Retail (STAGING value)
        website: "APPSTAGING", // default: APPSTAGING (STAGING value)
        staging: true, // default: true (by default paytm staging environment is used)
      );
    } on Exception {

      print("Some error occurred");
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

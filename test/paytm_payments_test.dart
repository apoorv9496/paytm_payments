import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:paytm_payments/paytm_payments.dart';

void main() {
  const MethodChannel channel = MethodChannel('paytm_payments');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await PaytmPayments.platformVersion, '42');
  });
}

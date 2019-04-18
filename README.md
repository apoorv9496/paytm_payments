# paytm_payments

Paytm Payments Flutter SDK

## Usage

[Example](https://github.com/apoorv9496/paytm_payments/blob/master/example/lib/main.dart)

To use this package :

* add the dependency to your pubspec.yaml file.

```yaml
  dependencies:
    flutter:
      sdk: flutter
    paytm_payments:
```

* add following permissions to you AndroidManifest.xml file.

```xml
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        package="xyz.ajax.paytm_payments_example">
    
        <!-- Include these permissions in your manifest file -->
        <uses-permission android:name="android.permission.INTERNET"/>
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
        <uses-permission android:name="android.permission.READ_SMS"/>
        <uses-permission android:name="android.permission.RECEIVE_SMS"/>
        
        <application></application>
    </manifest>
```

* Host a checksum generation file which will become your checksum URL which is one of the 2 required fields.

See Step- 5 of [Paytm Developer Generate Checksum Url](https://developer.paytm.com/docs/v1/android-sdk)
For any assistance with this step mail me at the give email address.

You may use the url with MID provided in example app to test the working of paytm staging environment.
Note that you are supposed to use test credentials with the staging setup for loggin into paytm.

Test Credentials:

    Number: 7777777777
    OTP: 489871

# License

    Copyright 2019 Apoorv Jain

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.io/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our 
[online documentation](https://flutter.io/docs), which offers tutorials, 
samples, guidance on mobile development, and a full API reference.

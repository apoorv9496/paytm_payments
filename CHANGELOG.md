## 0.0.1

* Pass Paytm Staging/Production user specific data and setup paytm payments within your app in a minute.

## 0.1.0

* Tested Staging Environment.
* Hosted php based checksum Urls. Example call to 'makePaytmPayment' works with default values too.

## 0.2.0

* Production environment fixed and tested
* Now generate random customer and order IDs too.

## 0.2.1

* Option to disable Transaction Response Toast message added.
* Method Channel call fixed.

## 0.3.0

* Now you can listen for the transaction status response using 'PaytmPayments.responseStream.listen'.

## 0.3.1

* Receive complete response object on 'PaytmPayments.responseStream.listen'.
* Bad State error on setting stream listener fixed.
* Example code more clear and easy to follow.
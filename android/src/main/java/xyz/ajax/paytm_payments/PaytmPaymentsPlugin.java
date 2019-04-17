package xyz.ajax.paytm_payments;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import static android.content.ContentValues.TAG;

/** PaytmPaymentsPlugin */
public class PaytmPaymentsPlugin implements MethodCallHandler {

    private MethodChannel channel;
    private Activity activity;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "paytm_payments");
    channel.setMethodCallHandler(new PaytmPaymentsPlugin(registrar.activity(), channel));
  }

  private PaytmPaymentsPlugin(Activity activity, MethodChannel channel){
      this.activity = activity;
      this.channel = channel;
      this.channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if(call.method.equals("paytmPayment")){

      HashMap<String, String> orderData = call.argument("order_data");
      boolean staging = call.argument("staging");

      // instance of service
      PaytmPGService Service;
      if(staging)
          Service = PaytmPGService.getStagingService();
      else
          Service = PaytmPGService.getProductionService();

      Log.i(TAG,"orderData: " + orderData.toString());

      // set and initialise order
      PaytmOrder Order = new PaytmOrder(orderData);
      Service.initialize(Order, null);
      Service.startPaymentTransaction(activity, true, true, new PaytmPaymentTransactionCallback() {
          /*Call Backs*/
          public void someUIErrorOccurred(String inErrorMessage) {
              /*Display the error message as below */
              Toast.makeText(activity, "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();
          }

          public void onTransactionResponse(Bundle inResponse) {
              /*Display the message as below */
              Log.i(TAG, "onTransactionResponse: " + inResponse.toString());
              Toast.makeText(activity, "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
          }

          public void networkNotAvailable() {
              /*Display the message as below */
              Toast.makeText(activity, "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
          }

          public void clientAuthenticationFailed(String inErrorMessage) {
              /*Display the message as below */
              Toast.makeText(activity, "Authentication failed: Server error", Toast.LENGTH_LONG).show();
          }

          public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
              /*Display the message as below */
              Toast.makeText(activity, "Unable to load webpage ", Toast.LENGTH_LONG).show();
          }

          public void onBackPressedCancelTransaction() {
              /*Display the message as below */
              Toast.makeText(activity, "Transaction cancelled" , Toast.LENGTH_LONG).show();
          }

          public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
              /*Display the message as below */
              Toast.makeText(activity, "Transaction Cancelled" + inResponse.toString(), Toast.LENGTH_LONG).show();
          }
      });
    }
    else {
      result.notImplemented();
    }
  }
}

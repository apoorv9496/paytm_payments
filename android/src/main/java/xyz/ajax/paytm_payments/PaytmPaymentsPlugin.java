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
import java.util.Set;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import static android.content.ContentValues.TAG;

/** PaytmPaymentsPlugin */
public class PaytmPaymentsPlugin implements MethodCallHandler {

    static private MethodChannel channel;
    private Activity activity;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    channel = new MethodChannel(registrar.messenger(), "paytm_payments");
    channel.setMethodCallHandler(new PaytmPaymentsPlugin(registrar.activity(), channel));
  }

  private PaytmPaymentsPlugin(Activity activity, MethodChannel channel){
      this.activity = activity;
      PaytmPaymentsPlugin.channel = channel;
      PaytmPaymentsPlugin.channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(MethodCall call, final Result result) {
    if(call.method.equals("paytmPayment")){

      HashMap<String, String> orderData = call.argument("order_data");
      boolean staging = call.argument("staging");
      final boolean showToast = call.argument("show_toast");

      // instance of service
      final PaytmPGService Service;
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

              HashMap<String, String> res = new HashMap<String, String>();
              res.put("RESPMSG", "UI Error");

              try {
                  result.success(res);
              } catch (Exception e){
                  System.out.println(e);
              }
          }

          public void onTransactionResponse(Bundle inResponse) {
              /*Display the message as below */
              Log.i(TAG, "onTransactionResponse: " + inResponse.toString());

              if(showToast)
                  Toast.makeText(activity, "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();

              HashMap<String, String> res = new HashMap<String, String>();

              Set<String> keys = inResponse.keySet();
              for (String key : keys) {
                  try {
                      res.put(key, inResponse.getString(key));
                  } catch(Exception e) {
                      //Handle exception here
                  }
              }

              try {
                  result.success(res);
              } catch (Exception e){
                  System.out.println(e);
              }
          }

          public void networkNotAvailable() {

              /*Display the message as below */
              if(showToast)
                  Toast.makeText(activity, "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();

              HashMap<String, String> res = new HashMap<String, String>();
              res.put("RESPMSG", "Network connection error: Check your internet connectivity");
          }

          public void clientAuthenticationFailed(String inErrorMessage) {
              /*Display the message as below */
              if(showToast){
                  Toast.makeText(activity, "Authentication failed: Server error", Toast.LENGTH_LONG).show();
              }

              HashMap<String, String> res = new HashMap<String, String>();
              res.put("RESPMSG", "Authentication failed: Server error");
          }

          public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
              /*Display the message as below */
              if(showToast)
                  Toast.makeText(activity, "Unable to load webpage ", Toast.LENGTH_LONG).show();

              HashMap<String, String> res = new HashMap<String, String>();
              res.put("RESPMSG", "Unable to load webpage ");
          }

          public void onBackPressedCancelTransaction() {
              /*Display the message as below */
              if(showToast)
                  Toast.makeText(activity, "Transaction cancelled" , Toast.LENGTH_LONG).show();

              HashMap<String, String> res = new HashMap<String, String>();
              res.put("RESPMSG", "Transaction Cancelled");
          }

          public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
              /*Display the message as below */
              if(showToast)
                  Toast.makeText(activity, "Transaction Cancelled" + inResponse.toString(), Toast.LENGTH_LONG).show();

              HashMap<String, String> res = new HashMap<String, String>();
              res.put("RESPMSG", "Transaction Cancelled");
          }
      });
    }
    else {
      result.notImplemented();
    }
  }
}

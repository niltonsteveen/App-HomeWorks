package prototipo.udea.edu.co.homeworks.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

/**
 * Created by AW 13 on 15/05/2017.
 */

public class NotificationSender {

    private static final String LEGACY_SERVER_KEY= "AAAAjkLP5n8:APA91bHrxdg-lVzs70D-alvrT_HjQwMlKX4YSg4gLY1sg6P-x7cfsv8Lo0ovDsjUrNvRnYbg8c6PcoX6r7JBAkI9xTNz38RkBEqsjGswZsRFBWdiPNI9xpx4sVvhwKZVVqyc-gBP54DN";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static void sendNotification(final String reg_token, final String message) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject json = new JSONObject();

                    JSONObject dataJson = new JSONObject();
                    dataJson.put("Duke", "Duke");
                    dataJson.put("sound", "default");
                    dataJson.put("body", message);
                    dataJson.put("title", "HomeWorks Notification");
                    JSONObject notificationJson = new JSONObject();
                    notificationJson.put("body", message);
                    notificationJson.put("title", "HomeWorks Notification");
                    json.put("data", dataJson);
                    //json.put("notification",notificationJson);
                    json.put("to", reg_token);
                    RequestBody body = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization", "key=" + LEGACY_SERVER_KEY)
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();
                    Log.e("Response", finalResponse);
                } catch (Exception e) {
                    Log.d("Error", e + "");
                }
                return null;
            }
        }.execute();
    }
}

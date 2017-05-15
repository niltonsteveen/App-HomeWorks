package prototipo.udea.edu.co.homeworks.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import prototipo.udea.edu.co.homeworks.Activities.MainActivity;
import prototipo.udea.edu.co.homeworks.R;

/**
 * Created by AW 13 on 15/05/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String mensaje = remoteMessage.getNotification().getBody();
        ShowNotification(mensaje);
        Log.e("mensaje",""+ mensaje);

    }

    private void ShowNotification(String mensaje) {

        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setAutoCancel(true).setContentTitle("HomeWorksNotifications")
                .setContentText(mensaje)
                .setSmallIcon(R.drawable.logo_final).setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}

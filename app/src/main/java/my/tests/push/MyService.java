package my.tests.push;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyService extends FirebaseMessagingService {

    public static final String ACTION_MESSAGE_RECEIVED = "ACTION_MESSAGE_RECEIVED";

    public MyService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String message = "";
        for (Map.Entry<String, String> entry : data.entrySet()) {
            //System.out.println("Key : " + entry.getKey() + ", Value: " + entry.getValue());
            message += "\nKey : " + entry.getKey() + ", Value: " + entry.getValue();
        }
        message.substring(0, message.length()-1);
        Intent intent = new Intent(ACTION_MESSAGE_RECEIVED);
        intent.putExtra("title", remoteMessage.getNotification().getTitle());
        intent.putExtra("body", remoteMessage.getNotification().getBody());
        intent.putExtra("message", message);
        boolean delivered = LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    @Override
    public void onNewToken(String token) {

    }
}

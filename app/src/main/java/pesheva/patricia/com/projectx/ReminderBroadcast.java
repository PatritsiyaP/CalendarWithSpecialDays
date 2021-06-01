package pesheva.patricia.com.projectx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import pesheva.patricia.com.projectx.R;

class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder =  new NotificationCompat.Builder(context, "notifyYpp")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Remind pp")
                .setContentText("hi...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200,builder.build());
    }
}

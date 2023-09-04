package com.example.safty;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyForeGroundService extends Service {
    private static final int NOTIFICATION_ID = 100;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @SuppressLint("NewApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            Log.e("Service", "service is running...");
                            try{
                                Thread.sleep(2000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }

                        }
                    }
                }
        ).start();

        String CHANNEL_ID = "ForegroundServiceChannel";

        NotificationChannel channel= new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);

        @SuppressLint({"NewApi", "LocalSuppress"})
        Notification.Builder notification=new Notification.Builder(this, CHANNEL_ID)
                .setContentText("Apps is running background")
                .setContentTitle("Service Enabled")
                .setSmallIcon(R.drawable.notification_icon);
        startForeground(NOTIFICATION_ID,notification.build());

        return super.onStartCommand(intent, flags, startId);
//        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

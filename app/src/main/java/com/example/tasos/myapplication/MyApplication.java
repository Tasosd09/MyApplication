package com.example.tasos.myapplication;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.tasos.myapplication.api.WebServiceCalls;
import com.example.tasos.myapplication.model.BeaconDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MyApplication extends Application {
    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("xaxaxa", "app created");
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new BeaconRegion(
                        "monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        null,
                        null
                ));
            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> beacons) {

                ArrayList<BeaconDAO> beaconList = new ArrayList<BeaconDAO>();
                for(int i = 0; i <= beacons.size(); i++){
                    //beaconList.add(new BeaconDAO(beacons.get(i).getRssi(),beacons.get(i).getUniqueKey(), beacons.get(i).getMacAddress() ));
                    WebServiceCalls.getURL();
                }
                showNotification("Entered beacon area", "Fuck yeah, beacons!");
                Log.e("XA", "Beacon recognized");
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                showNotification("Exited beacon area", "Fuck you, beacon");
                Log.e("XA", "Beacon lost");
            }
        });
    }


    public void showNotification(String title, String message){
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}

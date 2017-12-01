package org.androidtown.hansungclass.Class;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import org.androidtown.hansungclass.Activity.MainActivity;
import org.androidtown.hansungclass.R;

public class NotificationService extends Service {
    NotificationManager notificationManager;
    ServiceThread thread;
    Notification notification;


    public NotificationService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        String course = intent.getStringExtra("course");
        String location = intent.getStringExtra("location");
        String time = intent.getStringExtra("time");
        Log.i("yunjae", "onStartCommand()");
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        MyServiceHandler handler = new MyServiceHandler(course, location);
        Log.i("yunjae", "onStartCommand()" + course + "location = " + location);
        thread = new ServiceThread(handler, time);
        thread.start();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업
    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.stopForever();
        thread = null;
    }

    class MyServiceHandler extends Handler {
        private String course;
        private String location;

        public MyServiceHandler(String course, String location) {
            this.course = course;
            this.location = location;
        }

        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(NotificationService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(NotificationService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            notification = new Notification.Builder(getApplicationContext()).setContentTitle(course + "수업 시작하기 10분 전").setContentText(location).setSmallIcon(R.drawable.hansungclass).setTicker("Ticker").setContentIntent(pendingIntent).build();

            notification.defaults = Notification.DEFAULT_SOUND;

            notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            notificationManager.notify(777, notification);

        }
    }
}

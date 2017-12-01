package org.androidtown.hansungclass.Class;

import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hscom006 on 2017-12-01.
 */

public class ServiceThread extends Thread {
    Handler handler;
    boolean isRun = true;
    String time;

    public ServiceThread(Handler handler, String time) {
        this.handler = handler;
        this.time = time;
    }

    public void stopForever() {
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run() {
        while(isRun) {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
            String strDate = dateFormat.format(date);

            switch (time) {
                case "0":
                    String eight = "07:50";
                    if(strDate.equals(eight)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "1":
                    String nine = "08:50";
                    if(strDate.equals(nine)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "2":
                    String ten = "09:50";
                    if(strDate.equals(ten)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "3":
                    String eleven = "10:50";
                    if(strDate.equals(eleven)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "4":
                    String twelve = "11:50";
                    if(strDate.equals(twelve)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "5":
                    String thirteen = "12:50";
                    if(strDate.equals(thirteen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "6":
                    String fourteen = "13:50";
                    if(strDate.equals(fourteen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "7":
                    String fifteen = "14:50";
                    if(strDate.equals(fifteen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "8":
                    String sixteen = "15:50";
                    if(strDate.equals(sixteen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "9":
                    String seventeen = "16:50";
                    if(strDate.equals(seventeen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "10":
                    String eighteen = "17:50";
                    if(strDate.equals(eighteen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "11":
                    String nineteen = "18:50";
                    if(strDate.equals(nineteen)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
                case "12":
                    String twenty = "19:50";
                    if(strDate.equals(twenty)) {
                        handler.sendEmptyMessage(0);
                    }
                    break;
            }
        }
    }
}

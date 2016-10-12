package com.example.tick.myapplication;

import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tick on 2016/9/8.
 */
public class Utils {
    private static Boolean isExit = false;
    public static final void showToast(Context context,String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
    public static final void clickTwic(Context context){
        Timer timer = null;
        if(isExit == false){
            isExit = true;
            showToast(context,"双击退出应用");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
        }else {
            System.exit(0);
        }
    }
}

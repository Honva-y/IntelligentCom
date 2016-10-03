package com.example.tick.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tick on 2016/9/8.
 */
public class Utils {
    public static final void showToast(Context context,String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
}

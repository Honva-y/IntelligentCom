package com.example.tick.myapplication.MyView;

import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tick on 2016/10/3.
 */
public class ActivityTask implements Serializable{
    public static List<Activity> activities = new ArrayList<>();

    private static ActivityTask instance=null;
    private ActivityTask(){
    }
    public static ActivityTask getInstance(){
        if(instance==null){
            instance = new ActivityTask();
        }
        return instance;
    }
    public static void addActivity(Activity activity) {//activity加入list
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {//移除指定的activity
        activities.remove(activity);
    }

    public static void removeAll() {//移除全部activity
        for (Activity activity :
                activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

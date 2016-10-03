package com.example.tick.myapplication.User.Entity;

/**
 * Created by Tick on 2016/9/19.
 */
public class UserId {
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public UserId setUser_id(int user_id) {
        this.user_id = user_id;
        return null;
    }

    private static UserId instance=null;
    private UserId(){
    }
    public static UserId getInstance(){
        if(instance==null){
            instance = new UserId();
        }
        return instance;
    }
}

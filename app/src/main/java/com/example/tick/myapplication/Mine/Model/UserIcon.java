package com.example.tick.myapplication.Mine.Model;

import android.net.Uri;

/**
 * Created by Tick on 2016/9/7.
 */
public class UserIcon {
    //图片uri
    public Uri imageUri;
    //图片的名字
    public String imageName = "usericon.jpg";
    //图片裁剪过后的名字
    private static UserIcon instance = null;

    private UserIcon() {
    }

    public static UserIcon getInstance() {
        if (instance == null) {
            instance = new UserIcon();
        }
        return instance;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}

package com.example.tick.myapplication.Topic.View;

/**
 * Created by Tick on 2016/9/28.
 */
public interface TopicView<T> {
    void onSuccess(T t);
    void onShowMess(T t);
    void showDialog();
    void hideDialog();
    void doZanList(T t);
}

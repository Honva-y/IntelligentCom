package com.example.tick.myapplication.Topic.Presenter;

/**
 * Created by Tick on 2016/9/28.
 */
public interface TopicListeren<T> {
    void onSuccess(T t);
    void onFailed(String mess);
}

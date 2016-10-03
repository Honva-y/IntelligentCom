package com.example.tick.myapplication.Propery.Presenter;

/**
 * Created by Tick on 2016/9/21.
 */
public interface PropertyListener<T> {
    void onSuccess(T t);
    void OnFailed(String mess);
}

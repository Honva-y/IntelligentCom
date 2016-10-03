package com.example.tick.myapplication.Propery.View;

/**
 * Created by Tick on 2016/9/21.
 */
public interface PropertyView<T> {
    void showDialog();
    void hideDialog();
    void onSuccess(T t);
    void onFaile(String mess);
}

package com.example.tick.myapplication.Mine.Presenter;

/**
 * Created by Tick on 2016/9/19.
 */
public interface MineListener<T,V> {
    void onSuccess(T t);
    void onFailed(String mess);
    void backDate(T t,V v);
}

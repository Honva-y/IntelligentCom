package com.example.tick.myapplication.User.Presenter;

/**
 * Created by Tick on 2016/9/27.
 */
public interface UserListeren<T,V> {
    void onSuccess(T t,V v);
    void onFailed();
}

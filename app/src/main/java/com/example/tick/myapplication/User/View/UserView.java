package com.example.tick.myapplication.User.View;

/**
 * Created by Tick on 2016/9/27.
 */
public interface UserView<T,V> {
    void showDialog(String mess);
    void hideDialog();
    void onPersenter(T t,V v);
    void onShowMess(T t,V v);
    void onBackCode();
}

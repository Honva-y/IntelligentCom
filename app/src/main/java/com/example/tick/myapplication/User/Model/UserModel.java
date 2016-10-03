package com.example.tick.myapplication.User.Model;

import com.example.tick.myapplication.User.Presenter.UserListeren;

/**
 * Created by Tick on 2016/9/27.
 */
public interface UserModel<T,V> {
    void doPost(T t,V v, UserListeren listeren);
    void doGet(T t);
}

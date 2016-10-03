package com.example.tick.myapplication.Mine.View;

/**
 * Created by Tick on 2016/9/19.
 */
public interface MineView <T,V>{
    void showDialog();
    void hideDialog();
    void showError();
    void showSuccess(T t);
    void doPresenter(V v);
}

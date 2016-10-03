package com.example.tick.myapplication.Home.Presenter;

/**
 * Created by Tick on 2016/9/8.
 */
public interface HomePresenter<T> {
    void postBack(T t);//将数据传递到model处理
}

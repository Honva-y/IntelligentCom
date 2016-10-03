package com.example.tick.myapplication.Home.Presenter;

/**
 * Created by Tick on 2016/9/26.
 */
public interface HomeListeren<T> {
    void onSuccess(T t);//成功后传递数据到view中
    void onFailed(String mess);//传递错误原因到view中显示
}

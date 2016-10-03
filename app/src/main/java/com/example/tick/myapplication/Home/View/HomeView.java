package com.example.tick.myapplication.Home.View;

/**
 * Created by Tick on 2016/9/22.
 */
public interface HomeView<T,V> {
    void showDialog(String mess);//显示加载对话框
    void hideDialog();//隐藏加载对话框
    void showMess(T t);//从后台获取数据用于显示,获取数据对象T
    void postPresenter(V v);//传递参数到后台进行查询
}

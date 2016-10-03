package com.example.tick.myapplication.Mine.Model;

import com.example.tick.myapplication.Mine.Presenter.MineListener;

/**
 * Created by Tick on 2016/9/19.
 */
public interface MineModel<T> {
    void doCheck(T t,MineListener listener);
}

package com.example.tick.myapplication.Propery.Model;

import com.example.tick.myapplication.Propery.Presenter.PropertyListener;

/**
 * Created by Tick on 2016/9/21.
 */
public interface PropertyModel<T> {
    void modelPost(T t, PropertyListener listener);
}

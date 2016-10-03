package com.example.tick.myapplication.Propery.Presenter.Imple;

import com.example.tick.myapplication.Propery.Model.Impl.SuggestModel;
import com.example.tick.myapplication.Propery.Model.PropertyModel;
import com.example.tick.myapplication.Propery.Presenter.PropertyListener;
import com.example.tick.myapplication.Propery.Presenter.PropertyPersenter;
import com.example.tick.myapplication.Propery.View.PropertyView;

/**
 * Created by Tick on 2016/9/21.
 */
public class SuggestProperty implements PropertyPersenter,PropertyListener{
    PropertyView view;
    PropertyModel model;
    public SuggestProperty(PropertyView view) {
        this.view = view;
        model = new SuggestModel();
    }


    @Override
    public void onSuccess(Object o) {
        view.hideDialog();
        view.onSuccess(o);
    }

    @Override
    public void OnFailed(String mess) {
        view.hideDialog();
        view.onFaile(mess);
    }

    @Override
    public void postMessage(Object sparseArray) {
        view.showDialog();
        model.modelPost(sparseArray,this);
    }
}

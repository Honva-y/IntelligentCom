package com.example.tick.myapplication.Mine.Presenter.Impl;

import com.example.tick.myapplication.Mine.Model.Imple.SuggestModel;
import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.MineView;

/**
 * Created by Tick on 2016/9/26.
 */
public class SuggestPresenter implements MineListener, MinePresenter {
    private MineView view;
    private MineModel model;

    public SuggestPresenter(MineView view) {
        this.view = view;
        model = new SuggestModel();

    }

    @Override
    public void onSuccess(Object o) {
        view.hideDialog();
        view.showSuccess(o);
    }

    @Override
    public void onFailed(String mess) {
        view.hideDialog();
    }

    @Override
    public void backDate(Object o, Object o2) {

    }

    @Override
    public void postUser(Object o) {
        model.doCheck(o, this);
//        view.showDialog();
    }



}

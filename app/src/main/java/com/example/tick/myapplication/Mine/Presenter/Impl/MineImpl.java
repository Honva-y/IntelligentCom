package com.example.tick.myapplication.Mine.Presenter.Impl;

import com.example.tick.myapplication.Mine.Model.Imple.MineModelImpl;
import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.MineView;

/**
 * Created by Tick on 2016/9/20.
 */
        public class MineImpl implements MineListener,MinePresenter{
    private MineView view;
    private MineModel model;

    public MineImpl(MineView view) {
        this.view = view;
        model = new MineModelImpl();
    }

    @Override
    public void onSuccess(Object o) {
        view.hideDialog();
        view.showSuccess(o);
    }

    @Override
    public void onFailed(String mess) {
        view.hideDialog();
        view.showError();
    }

    @Override
    public void backDate(Object o, Object o2) {
        view.hideDialog();
        view.BackData(o,o2);
    }

    @Override
    public void postUser(Object object) {
        view.showDialog();
        model.doCheck(object,this);
    }



}

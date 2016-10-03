package com.example.tick.myapplication.Mine.Presenter.Impl;

import com.example.tick.myapplication.Mine.Model.Imple.RepairModel;
import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.MineView;

/**
 * Created by Tick on 2016/9/27.
 */
public class RepairPresenter implements MineListener,MinePresenter {
    private MineView view;
    private MineModel model;
    public RepairPresenter(MineView view) {
        this.view = view;
        model = new RepairModel();
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
    public void postUser(Object o) {
        model.doCheck(o,this);
    }
}

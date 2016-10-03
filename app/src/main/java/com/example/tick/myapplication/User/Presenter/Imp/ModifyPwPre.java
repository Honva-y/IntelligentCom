package com.example.tick.myapplication.User.Presenter.Imp;

import com.example.tick.myapplication.User.Model.Imp.ModifyPwMod;
import com.example.tick.myapplication.User.Model.UserModel;
import com.example.tick.myapplication.User.Presenter.UserListeren;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.UserView;

/**
 * Created by Tick on 2016/9/28.
 */
public class ModifyPwPre implements UserListeren,UserPresenter {
    private UserView view;
    private UserModel model;
    public ModifyPwPre(UserView view) {
        this.view = view;
        model = new ModifyPwMod();
    }

    @Override
    public void onSuccess(Object o, Object o2) {
        view.hideDialog();
        view.onShowMess(o,o2);
    }

    @Override
    public void onFailed() {
        view.hideDialog();
    }

    @Override
    public void doModel(Object o, Object o2) {
        view.showDialog("加载中...");
        model.doPost(o,o2,this);
    }
}

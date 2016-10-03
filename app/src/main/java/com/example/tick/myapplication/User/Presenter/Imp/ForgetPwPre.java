package com.example.tick.myapplication.User.Presenter.Imp;

import com.example.tick.myapplication.User.Model.Imp.ForgetPwMod;
import com.example.tick.myapplication.User.Model.UserModel;
import com.example.tick.myapplication.User.Presenter.UserListeren;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.UserView;

/**
 * Created by Tick on 2016/9/28.
 */
public class ForgetPwPre implements UserListeren,UserPresenter {
    private UserView view;
    private UserModel model;
    private static final int GETCODE = 0;
    private static final int GETPW = 1;
    public ForgetPwPre(UserView view) {
        this.view = view;
        model = new ForgetPwMod();
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

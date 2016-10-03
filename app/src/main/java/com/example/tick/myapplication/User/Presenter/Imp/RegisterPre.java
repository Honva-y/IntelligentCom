package com.example.tick.myapplication.User.Presenter.Imp;

import com.example.tick.myapplication.User.Model.Imp.RegisterMod;
import com.example.tick.myapplication.User.Model.UserModel;
import com.example.tick.myapplication.User.Presenter.UserListeren;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.UserView;

/**
 * Created by Tick on 2016/9/27.
 */
public class RegisterPre implements UserListeren,UserPresenter {
    private UserView view;
    private UserModel model;
    public RegisterPre(UserView view) {
        this.view = view;
        model = new RegisterMod();
    }

    @Override
    public void onSuccess(Object o1, Object o2) {
        view.hideDialog();
        view.onShowMess(o1,o2);
    }

    @Override
    public void onFailed() {
        view.hideDialog();
    }


    @Override
    public void doModel(Object o1, Object o2) {
        view.showDialog("加载中...");
        model.doPost(o1,o2,this);
    }
}

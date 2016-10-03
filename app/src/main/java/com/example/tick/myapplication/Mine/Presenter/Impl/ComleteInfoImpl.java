package com.example.tick.myapplication.Mine.Presenter.Impl;

import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.Impl.CompleteInfoActivity;
import com.example.tick.myapplication.Mine.Entity.UserInfo;
import com.example.tick.myapplication.Mine.Model.ComleteInfoModel;
import com.example.tick.myapplication.Mine.View.MineView;

/**
 * Created by Tick on 2016/9/8.
 */
public class ComleteInfoImpl implements MineListener<UserInfo>,MinePresenter<UserInfo>{
    CompleteInfoActivity myview;
    MineModel model;
    MineView view;
    public ComleteInfoImpl(MineView view) {
        this.view = view;
        model = new ComleteInfoModel(this);
    }

    @Override
    public void onSuccess(UserInfo userInfo) {

    }

    @Override
    public void onFailed(String mess) {

    }

    @Override
    public void postUser(UserInfo userInfo) {
        model.doCheck(userInfo,this);
    }
}

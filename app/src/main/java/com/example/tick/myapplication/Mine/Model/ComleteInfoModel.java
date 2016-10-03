package com.example.tick.myapplication.Mine.Model;

import com.example.tick.myapplication.Mine.Entity.UserInfo;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;

/**
 * Created by Tick on 2016/9/8.
 */
public class ComleteInfoModel implements MineModel<UserInfo>{
    MinePresenter presenter;
    public ComleteInfoModel(MinePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void doCheck(UserInfo userInfo, MineListener listener) {

    }

}

package com.example.tick.myapplication.Home.Presenter.Imple;

import com.example.tick.myapplication.Home.Model.HomeModel;
import com.example.tick.myapplication.Home.Model.Impl.HomeModelImp;
import com.example.tick.myapplication.Home.Presenter.HomeListeren;
import com.example.tick.myapplication.Home.Presenter.HomePresenter;
import com.example.tick.myapplication.Home.View.HomeView;

/**
 * Created by Tick on 2016/9/26.
 */
public class HomePresenterImp implements HomePresenter,HomeListeren {
    private HomeView view;
    private HomeModel model;
    public HomePresenterImp(HomeView view) {
        this.view = view;
        model = new HomeModelImp();
    }

    @Override
    public void postBack(Object o) {
        model.doRequest(o,this);
    }

    @Override
    public void onSuccess(Object o) {
        view.hideDialog();
        view.showMess(o);
    }

    @Override
    public void onFailed(String mess) {
        view.hideDialog();
    }
}

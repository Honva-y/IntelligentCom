package com.example.tick.myapplication.Topic.Presenter.Imp;

import android.util.Log;

import com.example.tick.myapplication.Topic.Entity.ZanList;
import com.example.tick.myapplication.Topic.Model.Imp.TopicMod;
import com.example.tick.myapplication.Topic.Model.TopicModel;
import com.example.tick.myapplication.Topic.Presenter.TopicListeren;
import com.example.tick.myapplication.Topic.Presenter.TopicPresenter;
import com.example.tick.myapplication.Topic.View.TopicView;

/**
 * Created by Tick on 2016/9/28.
 */
public class TopicPre implements TopicListeren, TopicPresenter {
    private TopicView view;
    private TopicModel model;

    public TopicPre(TopicView view) {
        this.view = view;
        model = new TopicMod();
    }

    @Override
    public void onSuccess(Object o) {
        view.hideDialog();
        if (o != null)
        view.onShowMess(o);

    }

    @Override
    public void onFailed(String mess) {
        view.hideDialog();
        view.onShowMess(mess);
    }

//    @Override
//    public void onZanAndComment(Object o) {//没有用上，后续优化除去
//        if(o!=null) {
//            view.hideDialog();
//            view.doZanList(o);
//        }
//        else
//            view.hideDialog();
//    }



    @Override
    public void doRequest(int user_id, Object o) {
        view.showDialog();
        model.onRequest(user_id, o, this);
    }


    @Override
    public void doClickZan(int user_id, int topic_id, int state) {
        view.showDialog();//考虑需不要需？？？？？
        model.onClickZan(user_id,topic_id,state,this);
    }

    @Override
    public void doComment(int user_id,int topic_id, String mess) {
        view.showDialog();
        model.onComment(user_id,topic_id,mess,this);
    }

    @Override
    public void doDelete(int user_id,int topic_id) {
        model.onDelete(user_id,topic_id,this);
    }

}

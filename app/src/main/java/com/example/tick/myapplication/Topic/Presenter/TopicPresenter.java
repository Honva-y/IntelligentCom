package com.example.tick.myapplication.Topic.Presenter;

import com.example.tick.myapplication.Topic.Entity.ZanList;

/**
 * Created by Tick on 2016/9/28.
 */
public interface TopicPresenter<T,V> {
    void doRequest(int user_id,V v);//请求数据
    void doClickZan(int user_id, int topci_id, int state);//点赞，传入Id，话题id，赞状态
    void doComment(int user_id,int topic_id,String mess);//评论，传入id，评论内容
}

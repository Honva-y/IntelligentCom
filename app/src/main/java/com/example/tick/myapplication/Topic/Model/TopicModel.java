package com.example.tick.myapplication.Topic.Model;

import com.example.tick.myapplication.Topic.Entity.ZanList;
import com.example.tick.myapplication.Topic.Presenter.TopicListeren;

/**
 * Created by Tick on 2016/9/28.
 */
public interface TopicModel<T,V>  {
    void onRequest(int user_id, V v, TopicListeren listeren);//请求数据
    void onClickZan(int user_id, int topic_id, int state, TopicListeren listeren);//点赞，传入Id，赞状态
    void onComment(int user_id,int topic_id,String mess,TopicListeren listeren);//评论，传入id，评论内容
}

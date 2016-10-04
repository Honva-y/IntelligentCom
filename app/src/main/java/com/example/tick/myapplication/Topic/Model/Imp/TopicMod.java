package com.example.tick.myapplication.Topic.Model.Imp;

import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Topic.Entity.TopicEntity;
import com.example.tick.myapplication.Topic.Entity.ZanList;
import com.example.tick.myapplication.Topic.Model.TopicModel;
import com.example.tick.myapplication.Topic.Presenter.TopicListeren;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tick on 2016/9/28.
 */
public class TopicMod implements TopicModel {
    private OkHttpClient client;
    private static final int CANCLEZAN=0;
    private static final int CLICKZAN=1;
    public TopicMod() {
        client = new OkHttpClient();
    }

    @Override
    public void onRequest(final int user_id, Object o, final TopicListeren listeren) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody body = new FormBody.Builder().add("user_id",user_id+"").build();
                Request request = new Request.Builder().url(new MyData().getTopicURL()).post(body).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listeren.onFailed("服务器出错");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string().toString();
//                        Log.d("aaaa", "onResponse: " + json);
                            try {
                                TopicEntity entity = new Gson().fromJson(json, TopicEntity.class);
                                listeren.onSuccess(entity);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                listeren.onSuccess(null);
                            }
                    }
                });
            }
        }).start();

    }


    @Override
    public void onClickZan(int user_id, int topic_id,int state,final TopicListeren listeren) {
        RequestBody body = new FormBody.Builder().add("user_id",user_id+"").build();//URL构造，后台便于接受T.T
        Request request = null;
        if (state==CLICKZAN) {//点赞
            request = new Request.Builder().url(new MyData().getTopicClickZan()+topic_id+"/praiseTopic").post(body).build();

        } else if(state==CANCLEZAN) {//取消赞
            request = new Request.Builder().url(new MyData().getTopicCancleZan()+topic_id+"/deletePraise").post(body).build();
        }
       doPost(user_id,request,listeren);
    }


    @Override
    public void onComment(final int user_id, final int topic_id, final String mess, final TopicListeren listeren) {//评论后重新调用onRequest方法，重新获取消息
        Log.d("aaaaa", "onResponse: in");
//        new Thread(new Runnable() {
//            public void run() {
//                RequestBody body = new FormBody.Builder().add("user_id",user_id+"").add("topic_id",topic_id+"").add("comment_content",mess).build();
//                Request request = new Request.Builder().url(new MyData().getTopicComment()).post(body).build();
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                            listeren.onFailed("服务器出错");
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
////                        onRequest(user_id,null,listeren);
//                    }
//                });
//            }
//        }).start();
    }


    private void doPost(final int user_id,final Request request, final TopicListeren listeren) {//点赞后条用onRequest，重新获取消息
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listeren.onFailed("服务器出错");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        onRequest(user_id,null,listeren);
                    }
                });
            }
        }).start();
    }
}

package com.example.tick.myapplication.Home.Model.Impl;
import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Home.Entity.NotifyEntity;
import com.example.tick.myapplication.Home.Model.HomeModel;
import com.example.tick.myapplication.Home.Presenter.HomeListeren;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tick on 2016/9/26.
 */
public class HomeModelImp implements HomeModel<Object,HomeListeren> {
    private OkHttpClient client;
    public HomeModelImp() {
        client = new OkHttpClient();
    }
    @Override
    public void doRequest(Object o, final HomeListeren listeren) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(new MyData().getNotify()).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        try {
                            listeren.onFailed("网络错误");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String mess = response.body().string().toString();
//                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//                        Log.d("aaaa", "onResponse: "+mess);
                        NotifyEntity notify =new Gson().fromJson(mess,NotifyEntity.class);
                        if(notify.getSendCode().getCode()==0){
                            listeren.onFailed("请求失败..");
                        }else {
                            listeren.onSuccess(notify);
                        }
                    }
                });
            }
        }).start();

    }
}

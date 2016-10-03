package com.example.tick.myapplication.Mine.Model.Imple;

import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackSuggest;
import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tick on 2016/9/26.
 */
public class SuggestModel implements MineModel{
    private OkHttpClient client;

    public SuggestModel() {
        client = new OkHttpClient();
    }

    @Override
    public void doCheck(Object o, final MineListener listener) {
        RequestBody body = new FormBody.Builder().add("user_id", String.valueOf(o)).build();
        Request request = new Request.Builder().url(new MyData().getMySuggest()).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    listener.onFailed("网络出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String suggest = response.body().string().toString();
//                Log.d("aaaa1", "onResponse: "+suggest);
                BackSuggest backSuggest = new Gson().fromJson(suggest,BackSuggest.class);
                //将数据转化成对象数组
//                Log.d("aaaa2", "onResponse: "+backSuggest.getComplains().size());
                listener.onSuccess(backSuggest);
            }
        });
    }
}

package com.example.tick.myapplication.Propery.Model.Impl;

import android.util.Log;
import android.util.SparseArray;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.Propery.Model.PropertyModel;
import com.example.tick.myapplication.Propery.Presenter.PropertyListener;
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
 * Created by Tick on 2016/9/21.
 */
public class SuggestModel implements PropertyModel<SparseArray> {
    private OkHttpClient client;

    @Override
    public void modelPost(final SparseArray sparseArray, final PropertyListener listener) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder().add("user_id", sparseArray.get(0).toString())
                            .add("complains_title","1")
                            .add("usertype_id",1+"")
                            .add("complains_content", sparseArray.get(1).toString()).build();
                    Request request = new Request.Builder().url(new MyData().getSuggestUrl()).post(body).build();
//                    Log.d("aaaaa", "投诉建议信息:user_id: "+sparseArray.get(0).toString()+",complains_content:"+sparseArray.get(1).toString());
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                listener.OnFailed("网络出错");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String mess = response.body().string().toString();
                            Log.d("aaaaa", "投诉建议信息: "+mess);
                            listener.onSuccess( new Gson().fromJson(mess, BackCode.class));
                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

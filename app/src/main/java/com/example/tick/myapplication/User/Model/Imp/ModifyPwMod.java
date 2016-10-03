package com.example.tick.myapplication.User.Model.Imp;

import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.User.Model.UserModel;
import com.example.tick.myapplication.User.Presenter.UserListeren;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

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
public class ModifyPwMod implements UserModel {
    private OkHttpClient client;

    public ModifyPwMod() {
        client = new OkHttpClient();
    }

    @Override
    public void doPost(final Object o,final Object o2, final UserListeren listeren) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String account = (String) ((HashMap) o).get("user_account");
                String password = (String) ((HashMap) o).get("user_password");
                RequestBody body = new FormBody.Builder().add("user_account", account).add("user_password", password).build();
                Request request = new Request.Builder().url(new MyData().getModifyPwUrl()).post(body).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listeren.onFailed();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string().toString();
                        Log.d("aaaa", "onResponse: "+json);
//                        response.close();

//                        BackCode backCode = new Gson().fromJson(json,BackCode.class);
//                        listeren.onSuccess(backCode, null);
                    }
                });
            }
        }).start();

    }

    @Override
    public void doGet(Object o) {

    }
}

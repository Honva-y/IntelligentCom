package com.example.tick.myapplication.User.Model.Imp;

import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.User.Model.UserModel;
import com.example.tick.myapplication.User.Presenter.UserListeren;
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
 * Created by Tick on 2016/9/27.
 */
public class RegisterMod implements UserModel {
    private OkHttpClient client;
    private final static int GET_CODE = 0;
    private final static int REGISTER = 1;
    private  Request request;
    public RegisterMod() {
        client = new OkHttpClient();
    }

    @Override
    public void doPost(Object o1, Object o2, final UserListeren listeren) {
        if ((int) o2 == GET_CODE) {
            request = new Request.Builder().url(new MyData().getGetCode()).build();
        } else if ((int) o2 == REGISTER) {
            String registerInfo = new Gson().toJson(o1);
            Log.d("aaaa", "doPost: " + registerInfo);
            RequestBody body = new FormBody.Builder().add("registInfo", registerInfo).build();
            request = new Request.Builder().url(new MyData().getRegisterUrl()).post(body).build();
        }
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listeren.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string().toString();
                response.close();
                Log.d("aaaa", "onResponse: "+json);
                BackCode backCode = new Gson().fromJson(json,BackCode.class);
                listeren.onSuccess(backCode,null);
            }
        });
    }

    @Override
    public void doGet(Object o) {

    }
}

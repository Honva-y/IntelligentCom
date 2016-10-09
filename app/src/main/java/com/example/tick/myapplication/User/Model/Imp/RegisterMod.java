package com.example.tick.myapplication.User.Model.Imp;

import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.User.Entity.MessageCode;
import com.example.tick.myapplication.User.Entity.RegisterEntity;
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
            String photo = (String) o1;
            RequestBody body = new FormBody.Builder().add("user_account",photo).build();
            request = new Request.Builder().url(new MyData().getGetCode()).post(body).build();
            getCode(request,listeren);
        } else if ((int) o2 == REGISTER) {
            RegisterEntity entity = (RegisterEntity) o1;
            RequestBody body = new FormBody.Builder().add("user_account", entity.getUser_account()).add("user_password",entity.getUser_password()).build();
            request = new Request.Builder().url(new MyData().getRegisterUrl()).post(body).build();
            registerUser(request,listeren);
        }

    }

    private void registerUser(final Request request, final UserListeren listeren) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listeren.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myjson = response.body().string().toString();
//                Log.d("bbbb1", "onResponse: "+myjson);
                response.close();
                BackCode backCode = new Gson().fromJson(myjson,BackCode.class);
//                Log.d("bbbb3", "onResponse: "+backCode.getCode()+","+backCode.getMessage());
                listeren.onSuccess(backCode,REGISTER);
            }
        });
    }

    private void getCode(final Request request,final UserListeren listeren) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listeren.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string().toString();
                Log.d("getcode", "onResponse: "+json);
                response.close();
                MessageCode messageCode = new Gson().fromJson(json,MessageCode.class);
                Log.d("getcode", "onResponse: "+messageCode.getNumber());

                listeren.onSuccess(messageCode,GET_CODE);
            }
        });
    }

    @Override
    public void doGet(Object o) {

    }
}

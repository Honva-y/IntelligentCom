package com.example.tick.myapplication.User.Model.Imp;

import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.User.Entity.MessageCode;
import com.example.tick.myapplication.User.Model.UserModel;
import com.example.tick.myapplication.User.Presenter.UserListeren;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Cache;
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
public class ForgetPwMod implements UserModel {
    private OkHttpClient client;
    private static final int GETCODE = 0;
    private static final int GETPW = 1;
    private Request request;

    public ForgetPwMod() {
        client = new OkHttpClient();
    }

    @Override
    public void doPost(final Object o, final Object o2, final UserListeren listeren) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if((int)o2==GETCODE){//获取验证码
                    request = new Request.Builder().url(new MyData().getGetCode()).build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            listeren.onFailed();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string().toString();
                            Log.d("aaaa", "json: "+json);
                            MessageCode backCode = new Gson().fromJson(json,MessageCode.class);
//                            Log.d("aaaa", "onResponse: "+backCode.getSendCode().getCode());
                            listeren.onSuccess(backCode,GETCODE);
                        }
                    });
                }else if((int)o2==GETPW){//提交新密码
                  new Thread(new Runnable() {
                      @Override
                      public void run() {
                          RequestBody body = new FormBody.Builder().add("user_account", String.valueOf(((HashMap)o).get("user_account"))).add("user_password",String.valueOf(((HashMap)o).get("user_password"))).build();//手机号码，验证码
                          request = new Request.Builder().url(new MyData().getForgetPwUrl()).post(body).build();
                          Call call = client.newCall(request);
                          call.enqueue(new Callback() {
                              @Override
                              public void onFailure(Call call, IOException e) {
                                  listeren.onFailed();
                              }

                              @Override
                              public void onResponse(Call call, Response response) throws IOException {
                                  String json = response.body().string().toString();
                                  Log.d("aaaa", "json: "+json);
                                  BackCode backCode = new Gson().fromJson(json,BackCode.class);
                                  Log.d("aaaa", "onResponse: "+backCode.getCode());
                                  listeren.onSuccess(backCode,GETPW);
//                        response.close();
                              }
                          });
                      }
                  }).start();

                }

            }
        }).start();

    }

    @Override
    public void doGet(Object o) {

    }
}

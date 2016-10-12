package com.example.tick.myapplication.Mine.Model.Imple;

import android.util.Log;
import android.util.SparseArray;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.Mine.Entity.BackUserhead;
import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tick on 2016/9/20.
 */
public class MineModelImpl implements MineModel<SparseArray> {
    private OkHttpClient client;

    @Override
    public void doCheck(final SparseArray array, final MineListener listener) {//修改头像
        new Thread(new Runnable() {
            @Override
            public void run() {
                client = new OkHttpClient();
                try {
                    Request request = null;
                    RequestBody body = null;
                    Call call = null;
                    if (Integer.parseInt(String.valueOf(array.get(0))) == 0) {//修改昵称
                        body = new FormBody.Builder().add("user_id", String.valueOf(array.get(1))).add("user_nickname", (String) array.get(2)).build();
                        request = new Request.Builder().url(new MyData().getNickUrl()).post(body).build();
                        call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                try {
                                    listener.onFailed("网络异常");
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                BackCode backCode = new Gson().fromJson(response.body().string().toString(), BackCode.class);
                                try {
                                    listener.onSuccess(backCode);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else if (Integer.parseInt(String.valueOf(array.get(0))) == 1) {//修改头像
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        File file = new File(String.valueOf(array.get(2)));//转换成文件
                        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file)).build();
                        RequestBody requestBody = builder.addFormDataPart("user_id", array.get(1) + "").addFormDataPart("user_head", array.get(3) + "").build();
                        request = new Request.Builder().url(new MyData().getUserHead()).post(requestBody).build();

                        call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                try {
                                    listener.onFailed("网络异常");
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String json = response.body().string().toString();
                                Log.d("aaaaaaaa", "onResponse: " + json);
                                BackUserhead backCode = new Gson().fromJson(json, BackUserhead.class);
//                            if(backCode!=null) {
                                listener.backDate(backCode, "");
//                            }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

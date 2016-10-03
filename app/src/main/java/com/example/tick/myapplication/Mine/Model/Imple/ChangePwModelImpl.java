package com.example.tick.myapplication.Mine.Model.Imple;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.Mine.Model.MineModel;
import com.example.tick.myapplication.Mine.Presenter.MineListener;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
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
 * Created by Tick on 2016/9/19.
 */
public class ChangePwModelImpl implements MineModel<SparseArray> {
    private OkHttpClient client;
    MinePresenter presenter;

    @Override
    public void doCheck(final SparseArray arr, final MineListener listener) {
        client = new OkHttpClient();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Message message = new Message();
                    RequestBody body = new FormBody.Builder().add("user_id", arr.get(0).toString()).add("user_password", arr.get(1).toString()).build();//id是int类型，传递的是string类型
                    Request request = new Request.Builder().url(new MyData().getChangePwUrl()).post(body).build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            message.what=0;
                            message.obj = listener;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            try {
                                String json = response.body().string().toString();
                                BackCode backCode = new Gson().fromJson(json, BackCode.class);
//                                Log.d("aaa3", "onResponse: " + json + "," + backCode.getMessage());
                                message.what = 1;
                                message.obj = listener;
                                handler.sendMessage(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                ((MineListener)msg.obj).onFailed("");
            }
            else if(msg.what==1){
                ((MineListener)msg.obj).onSuccess(new BackCode());
            }
        }
    };
}

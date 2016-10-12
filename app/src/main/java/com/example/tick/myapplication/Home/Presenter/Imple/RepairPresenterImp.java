package com.example.tick.myapplication.Home.Presenter.Imple;

import android.os.Message;
import android.util.Log;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Home.Entity.RepairEntity;
import com.example.tick.myapplication.Home.Presenter.HomePresenter;
import com.example.tick.myapplication.Home.View.Imple.RepairActivity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tick on 2016/9/8.
 */
public class RepairPresenterImp implements HomePresenter {
    private RepairActivity view;
    private OkHttpClient client;
    private static final int SUCCESS = 0;
    private static final int FAILE = 1;

    public RepairPresenterImp(RepairActivity view) {
        this.view = view;
        client = new OkHttpClient();
    }

    public void postRepairData(final RepairEntity entity) {
        //数据提交到数据库
        Gson gson = new Gson();
        final String repairMess = gson.toJson(entity);
        final String  project = entity.getRepair_project();
        final String start_time = entity.getRepair_starttime();
        final String end_time = entity.getRepair_endtime();
        final int user_id = entity.getRepair_userid();
//        Log.d("aaaaaaaa", "setReapirDate: " + repairMess);
        new Thread(new Runnable() {
            final Message message = new Message();
            @Override
            public void run() {
                try {
//                    RequestBody body = RequestBody.create(MediaType.parse("application/json"), repairMess);
                    RequestBody body = new FormBody.Builder().add("endtime",end_time)
                            .add("starttime",start_time)
                            .add("repair_userid",user_id+"")
                            .add("repair_project",project).build();
                    Request request = new Request.Builder().url(new MyData().getRepairUrl()).post(body).build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            message.what = FAILE;
                            view.repairHandler.sendMessage(message);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String json = response.body().string().toString();
//                            Log.d("aaaaa", "onResponse: "+json);
                            message.what = SUCCESS;
                            view.repairHandler.sendMessage(message);
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void postBack(Object o) {

    }
}

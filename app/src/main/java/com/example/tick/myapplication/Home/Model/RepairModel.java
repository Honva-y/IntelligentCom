//package com.example.tick.myapplication.Home.Model;
//
//import android.os.Message;
//import android.util.Log;
//
//import com.example.tick.myapplication.Home.Entity.RepairEntity;
//import com.example.tick.myapplication.Home.Presenter.RepairPresenterImp;
//import com.google.gson.Gson;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// * Created by Tick on 2016/9/8.
// */
//public class RepairModel {
//    RepairPresenterImp presenterImp;
//    private OkHttpClient client;
//    private String repairUrl="";
//    private static  final int SUCCESS  =0;
//    private static  final int FAILE  =1;
//    public RepairModel(RepairPresenterImp presenterImp) {
//        this.presenterImp = presenterImp;
//    }
//    public void setReapirDate(RepairEntity entity){
//        //数据提交到数据库
//        Gson gson = new Gson();
//        String repairMess = gson.toJson(entity);
////        gson.fromJson(mess,RepairEntity.class);
//        Log.d("aaaaaaaa", "setReapirDate: "+repairMess);
//        final Message message = new Message();
//        try {
//            client = new OkHttpClient();
//            RequestBody body = RequestBody.create(MediaType.parse("application/json"),repairMess);
//            Request request = new Request.Builder().url(repairUrl).post(body).build();
//            Call call = client.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    message.what = FAILE;
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    message.what = SUCCESS;
//                }
//
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

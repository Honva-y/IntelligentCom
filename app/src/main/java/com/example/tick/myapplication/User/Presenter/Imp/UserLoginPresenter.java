//package com.example.tick.myapplication.User.Presenter.Imp;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Message;
//import android.util.Log;
//
//import com.example.tick.myapplication.GlobalValue.MyData;
//import com.example.tick.myapplication.User.Entity.UserAll;
//import com.example.tick.myapplication.User.Presenter.UserListeren;
//import com.example.tick.myapplication.User.Presenter.UserPresenter;
//import com.example.tick.myapplication.User.View.LoginActivity;
//import com.example.tick.myapplication.Utils;
//import com.google.gson.Gson;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// * Created by Tick on 2016/9/13.
// */
//public class UserLoginPresenter implements UserListeren,UserPresenter{
//    LoginActivity view;
//    OkHttpClient client;
//    private static final int SUCCESS = 0;
//    private static final int ACCOUNT_ERROE = 1;
//    private static final int NETWORK_ERROR = 2;
//    public static final int COMPLETE = 3;
//    public static final int NOT_COMPLETE = 4;
//    public static final int VERIFY = 5;
//    SharedPreferences sharedPreferences;
//    public static int USER_ID;
//
//    public UserLoginPresenter(LoginActivity view) {
//        this.view = view;
//        client = new OkHttpClient.Builder().build();
//    }
//
//    public void loginResult(final String account, final String password) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final Message message = new Message();
//                try {
//                    RequestBody body = new FormBody.Builder().add("user_account", account).add("user_password", password).build();
//                    Request request = new Request.Builder().url(new MyData().getLoginUrl()).post(body).build();
//                    Call call = client.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            message.what = NETWORK_ERROR;
//                            view.handler.sendMessage(message);
//                        }
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            String json = response.body().string().toString();
//                            //获取数据
//                            Gson gson = new Gson();
//                            UserAll userAll = gson.fromJson(json, UserAll.class);
//                            if(userAll!=null){
//                                if (userAll.getSendCode().getCode() == 1) {//1为登录成功，0为登录失败
//                                    keepAccountAndPassword(account, password);//保存账户密码信息
//                                    keepUserInfo(userAll);//将数据保存在文件中
//                                    String carId = userAll.getUserVo().getUser().getUser_card();
//                                    String appro = userAll.getUserVo().getUser().getUser_approver();
//                                    message.what = SUCCESS;
//                                    message.arg1 = userAll.getUserVo().getUser().getUser_id();
//
//                                    if (carId.equals(""))//判断是否信息完善
//                                        message.arg2 = NOT_COMPLETE;
//                                    else if (!carId.equals("") && appro.equals(""))
//                                        message.arg2 = VERIFY;
//                                    else if (!carId.equals("") && !appro.equals(""))
//                                        message.arg2 = COMPLETE;
//                                } else {
//                                    message.what = ACCOUNT_ERROE;
//                                }
//                                view.handler.sendMessage(message);
//                            }
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    public void keepAccountAndPassword(String account, String password) {//保存用户账号密码
//        SharedPreferences.Editor editor = view.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
//        editor.clear();//先清除再保存
//        editor.putString("account", account);
//        editor.putString("password", password);
//        editor.commit();
//    }
//
//    public void keepUserInfo(UserAll userAll) {//保存用户信息
//        SharedPreferences.Editor editor = view.getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
//        editor.clear();
//        editor.putString("user_nickname", userAll.getUserVo().getUser().getUser_nickname());//昵称
//        editor.putString("user_type", userAll.getUserVo().getUsertype().getUsertype_name());//用户类型
//        editor.putString("user_photo", userAll.getUserVo().getUser().getUser_account()); //手机号码
//        editor.putString("user_sex", userAll.getUserVo().getUser().getUser_sex()); //性别
//        editor.putString("user_community", userAll.getUserVo().getCommunity().getCommunity_name()); //小区
//        editor.putString("user_email", userAll.getUserVo().getUser().getUser_email()); //邮箱
//        editor.putString("user_card", userAll.getUserVo().getUser().getUser_card()); //身份证
//        editor.putString("user_approver", userAll.getUserVo().getUser().getUser_approver());//审核人
//        editor.putString("user_password", userAll.getUserVo().getUser().getUser_password());//用户密码
//        editor.putString("user_head", userAll.getUserVo().getUser().getUser_head()); //图片路径
////        Log.d("bbb3", "keepUserInfo: "+userAll.getUserVo().getUser().getUser_id());
////        new MyData().setUser_id(userAll.getUserVo().getUser().getUser_id());//设置成全局变量
//        editor.putInt("user_id", userAll.getUserVo().getUser().getUser_id()); //用户id用户后期查询
//        USER_ID = userAll.getUserVo().getUser().getUser_id();
////        Log.d("bbb1", "keepUserInfo: "+userAll.getUserVo().getUser().getUser_head());
////        Log.d("bbb2", "keepUserInfo: "+userAll.getUserVo().getUser().getUser_approver());
//        editor.commit();
//    }
//
//    @Override
//    public void onSuccess(Object o, Object o2) {
//
//    }
//
//    @Override
//    public void onFailed() {
//
//    }
//
//    @Override
//    public void doModel(Object o, Object o2) {
//
//    }
//}

package com.example.tick.myapplication.User.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;

import com.example.tick.myapplication.MainActivity;
import com.example.tick.myapplication.MyView.ClearEditText;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Entity.UserAll;
import com.example.tick.myapplication.User.Entity.UserId;
import com.example.tick.myapplication.User.Presenter.Imp.UserLoginPre;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.Imp.CompleteInfoActivity;
import com.example.tick.myapplication.User.View.Imp.ForgetPwActivity;
//import com.example.tick.myapplication.User.Presenter.Imp.UserLoginPresenter;
import com.example.tick.myapplication.User.View.Imp.RegisterView;
import com.example.tick.myapplication.Utils;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/31.
 */
public class LoginActivity extends Activity implements DialogInterface.OnKeyListener,UserView {
//    private UserLoginPresenter presenter;
    private UserLoginPre presenter;
    private SharedPreferences sharedPreferences;
    private ProgressDialog pDialog;
    @BindView(R.id.login_et_account)
    MaterialEditText account;
    @BindView(R.id.login_et_password)
    MaterialEditText password;
    private  Message message;
    public static int USER_ID;
    private static final int SUCCESS = 0;
    private static final int ACCOUNT_ERROE = 1;
    private static final int NETWORK_ERROR = 2;
    public static final int COMPLETE = 3;
    public static final int NOT_COMPLETE = 4;
    public static final int VERIFY = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);
//        presenter = new UserLoginPresenter(this);
        presenter = new UserLoginPre(this);
        initView();
    }

    //初始化控件
    private void initView() {
        getAccountAndPassword();//获取账号密码
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("登陆中...");
        pDialog.setCancelable(false);
        pDialog.setOnKeyListener(this);
    }

    //获取登陆成功过的账号密码
    private void getAccountAndPassword() {
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String ac = sharedPreferences.getString("account", "");
        String pw = sharedPreferences.getString("password", "");
        account.setText(ac);
        password.setText(pw);
        account.setSelection(ac.length());//光标位置
    }

    @OnClick(R.id.login_bt_register)
    void OnResgister() {
        startActivity(new Intent(this, RegisterView.class).putExtra("title", "注册账号"));
    }

    @OnClick(R.id.login_tv_forget)
    void OnForget() {
        startActivity(new Intent(this, ForgetPwActivity.class).putExtra("title", "找回密码"));
    }

    @OnClick(R.id.ripple_bt)
    void OnLogin() {
        if (CheckText())//如果为空，提示
            return;
        HashMap hashMap = new HashMap();
        hashMap.put("user_account",account.getText().toString().trim());
        hashMap.put("user_password",password.getText().toString().trim());
        presenter.doModel(hashMap,"");
    }

    //判断账号密码是否为空
    private boolean CheckText() {
        if (account.getText().toString().equals("") || password.getText().toString().equals("")) {
            Utils.showToast(this, "账号、密码不能为空");
            return true;
        }
        return false;
    }


    //是否登录成功
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            this.obtainMessage();
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS://成功
                    UserId.getInstance().setUser_id(msg.arg1);//用sharefrefener代替
                    if (msg.arg2 == COMPLETE) {
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else if (msg.arg2 == NOT_COMPLETE) {
                        finish();
                        Utils.showToast(LoginActivity.this, "请先完善信息");
                        startActivity(new Intent(LoginActivity.this, CompleteInfoActivity.class));
                    } else if (msg.arg2 == VERIFY) {
                        new AlertDialog.Builder(LoginActivity.this).setMessage("信息审核中，请耐心等待").setPositiveButton("确定", null).show();
                    }
                    break;
                case ACCOUNT_ERROE://失败
                    Utils.showToast(LoginActivity.this, "账号密码错误");
                    password.setText("");
                    break;
                case NETWORK_ERROR://失败
                    Utils.showToast(LoginActivity.this, "网络出错!!");
                    break;
            }
        }
    };


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (pDialog == dialog) {
            pDialog.dismiss();
        }
        return true;
    }

    @Override
    public void showDialog(String mess) {
        pDialog.show();
    }

    @Override
    public void hideDialog() {
        pDialog.dismiss();
    }

    @Override
    public void onPersenter(Object o, Object o2) {

    }

    @Override
    public void onShowMess(Object o, Object o2) {
        UserAll all = (UserAll) o;
        message = new Message();
        if(all!=null) {
            if (all.getSendCode().getCode() == 1) {
                keepAccountAndPassword(account.getText().toString().trim(), password.getText().toString().trim());
                keepUserInfo(all);
                message.what = SUCCESS;
                String carId = all.getUserVo().getUser().getUser_card();
                int state = all.getUserVo().getUser().getUser_checkstate();
                if (carId.equals(""))//判断是否信息完善
                    message.arg2 = NOT_COMPLETE;
                else if (state==0)//未审核
                    message.arg2 = VERIFY;
                else if (state==1)//已经审核通过
                    message.arg2 = COMPLETE;
            } else {
                message.what = ACCOUNT_ERROE;
            }
        }else{
            message.what = NETWORK_ERROR;
        }
        handler.sendMessage(message);
    }

    @Override
    public void onBackCode() {
    }
    public void keepAccountAndPassword(String account, String password) {//保存用户账号密码
        SharedPreferences.Editor editor = getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        editor.clear();//先清除再保存
        editor.putString("account", account);
        editor.putString("password", password);
        editor.commit();
    }
    public void keepUserInfo(UserAll userAll) {//保存用户信息
        SharedPreferences.Editor editor = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("user_nickname", userAll.getUserVo().getUser().getUser_nickname());//昵称
        editor.putString("user_type", userAll.getUserVo().getUsertype().getUsertype_name());//用户类型
        editor.putString("user_photo", userAll.getUserVo().getUser().getUser_account()); //手机号码
        editor.putString("user_sex", userAll.getUserVo().getUser().getUser_sex()); //性别
        editor.putString("user_community", userAll.getUserVo().getCommunity().getCommunity_name()); //小区
        editor.putString("user_email", userAll.getUserVo().getUser().getUser_email()); //邮箱
        editor.putString("user_card", userAll.getUserVo().getUser().getUser_card()); //身份证
        editor.putString("user_approver", userAll.getUserVo().getUser().getUser_approver());//审核人
        editor.putString("user_password", userAll.getUserVo().getUser().getUser_password());//用户密码
        editor.putString("user_head", userAll.getUserVo().getUser().getUser_head()); //图片路径
//        Log.d("bbb3", "keepUserInfo: "+userAll.getUserVo().getUser().getUser_id());
//        new MyData().setUser_id(userAll.getUserVo().getUser().getUser_id());//设置成全局变量
        editor.putInt("user_id", userAll.getUserVo().getUser().getUser_id()); //用户id用户后期查询
        USER_ID = userAll.getUserVo().getUser().getUser_id();
//        Log.d("aaaa2", "keepUserInfo: "+USER_ID);
//        Log.d("bbb1", "keepUserInfo: "+userAll.getUserVo().getUser().getUser_head());
//        Log.d("bbb2", "keepUserInfo: "+userAll.getUserVo().getUser().getUser_approver());
        editor.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Utils.clickTwic(this);
        }
        return false;
    }
}

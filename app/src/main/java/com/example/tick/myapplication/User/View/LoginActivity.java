package com.example.tick.myapplication.User.View;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.example.tick.myapplication.User.Entity.UserId;
import com.example.tick.myapplication.User.View.Imp.CompleteInfoActivity;
import com.example.tick.myapplication.User.View.Imp.ForgetPwActivity;
import com.example.tick.myapplication.User.Presenter.Imp.UserLoginPresenter;
import com.example.tick.myapplication.User.View.Imp.RegisterView;
import com.example.tick.myapplication.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/31.
 */
public class LoginActivity extends Activity implements DialogInterface.OnKeyListener {
    private UserLoginPresenter presenter;
    private SharedPreferences sharedPreferences;
    private ProgressDialog pDialog;
    @BindView(R.id.login_et_account)
    ClearEditText account;
    @BindView(R.id.login_et_password)
    ClearEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);
        presenter = new UserLoginPresenter(this);
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

    @OnClick(R.id.login_bt_login)
    void OnLogin() {
        if (CheckText())//如果为空，提示
            return;
        pDialog.show();
//        Log.d("aaaa", "OnLogin: "+account.getText().toString()+","+password.getText().toString());
        presenter.loginResult(account.getText().toString().trim(), password.getText().toString().trim());//登录操作
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
                case 0://成功
                    pDialog.dismiss();
                    UserId.getInstance().setUser_id(msg.arg1);//用sharefrefener代替
                    if (msg.arg2 == UserLoginPresenter.COMPLETE) {
                        finish();
                        Utils.showToast(LoginActivity.this, "登陆成功");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else if (msg.arg2 == UserLoginPresenter.NOT_COMPLETE) {
                        finish();
                        Utils.showToast(LoginActivity.this, "请先完善信息");
                        startActivity(new Intent(LoginActivity.this, CompleteInfoActivity.class));
                    } else if (msg.arg2 == UserLoginPresenter.VERIFY) {
                        new AlertDialog.Builder(LoginActivity.this).setMessage("信息审核中，请耐心等待").setPositiveButton("确定", null).show();
                    }

                    break;
                case 1://失败
                    pDialog.dismiss();
                    account.addShakeAnimation();//抖动无效，不知道什么原因
                    Utils.showToast(LoginActivity.this, "账号密码错误");
                    password.setText("");
                    break;
                case 2://失败
                    pDialog.dismiss();
                    Utils.showToast(LoginActivity.this, "网络出错!!");
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (pDialog == dialog) {
            pDialog.dismiss();
        }
        return true;
    }
}

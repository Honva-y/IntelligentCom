package com.example.tick.myapplication.User.View.Imp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.MyView.ClearEditText;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Entity.RegisterEntity;
import com.example.tick.myapplication.User.Entity.UserEntity;
import com.example.tick.myapplication.User.Presenter.Imp.RegisterPre;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.UserView;
import com.example.tick.myapplication.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.internal.Util;

/**
 * Created by Tick on 2016/8/31.
 */
public class RegisterView extends Activity implements UserView {
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.register_photo_num)
    ClearEditText et_photo;
    @BindView(R.id.register_password)
    ClearEditText et_pw;
    @BindView(R.id.register_sure_pw)
    ClearEditText et_sure_pw;
    @BindView(R.id.register_code)
    ClearEditText et_code;
    private ProgressDialog pDialog;
    private UserPresenter presenter;
    private final static int GET_CODE = 0;
    private final static int REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        //加载对话框
        pDialog = new ProgressDialog(this);
        presenter = new RegisterPre(this);
    }

    @OnClick(R.id.many_top_back)
    void onBack() {
        finish();
    }

    @OnClick(R.id.register_bt_getcode)
    void onGetCode() {
        if (isRightPhoto())//获取验证码
            onPersenter("", GET_CODE);
    }

    @OnClick(R.id.register_bt_register)
    void onRegister() {
        if (isRightPhoto() && isRightPw() && isRightCode()) {
            RegisterEntity entity = new RegisterEntity();
            entity.setUser_account(et_photo.getText().toString().trim());
            entity.setUser_password(et_pw.getText().toString().trim());
            entity.setCode(Integer.parseInt(et_code.getText().toString().trim()));
            onPersenter(entity, REGISTER);
        }
    }

    @Override
    public void showDialog(String mess) {
        pDialog.setMessage(mess);
        pDialog.show();
    }

    @Override
    public void hideDialog() {
        pDialog.dismiss();
    }

    @Override
    public void onPersenter(Object o1, Object o2) {
        presenter.doModel(o1, o2);
    }

    @Override
    public void onShowMess(Object o, Object o2) {
        Message message = new Message();
        message.obj = ((BackCode) o).getMessage();
        message.what = ((BackCode) o).getCode();
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Utils.showToast(getApplicationContext(), msg.obj + "");
            if(msg.what==0){//请求码失败

            }else if(msg.what==1&&String.valueOf(msg.obj).contains("注册成功")){//请求码正确
                //跳转到修改密码界面
                finish();
            }
            else if(msg.what==1&&String.valueOf(msg.obj).contains("短信")){//请求码正确,不做处理

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }

    @Override
    public void onBackCode() {

    }

    boolean isRightPhoto() {//账号密码是否为空
        if (et_photo.getText().toString().equals("")) {
            Utils.showToast(this, "手机号不能为空");
            return false;
        } else if (et_photo.getText().toString().length() != 11) {
            Utils.showToast(this, "手机格式不正确");
            return false;
        }
        return true;
    }

    boolean isRightPw() {//2次密码是否一致
        if (!et_pw.getText().toString().equals(et_sure_pw.getText().toString())) {
            Utils.showToast(this, "两次密码不一致");
            et_pw.setText("");
            et_sure_pw.setText("");
            return false;
        } else if (et_pw.getText().toString().equals("") || et_sure_pw.getText().toString().equals("")) {
            Utils.showToast(this, "密码不能为空");
            et_pw.requestFocus();
            return false;
        }else if(et_pw.getText().toString().length()<6){
            Utils.showToast(this,"密码必须大于6位");
            et_pw.requestFocus();
            return false;
        }
        return true;
    }

    boolean isRightCode() {
        if (et_code.getText().toString().equals("")) {
            Utils.showToast(this, "验证码不能为空");
            et_code.requestFocus();
            return false;
        }else if(et_code.getText().toString().trim().length()!=6){
            Utils.showToast(this,"验证码不正确");
            et_code.requestFocus();
            return false;
        }
        return true;
    }

}

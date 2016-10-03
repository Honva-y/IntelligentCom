package com.example.tick.myapplication.User.View.Imp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.C;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.MyView.ClearEditText;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Entity.MessageCode;
import com.example.tick.myapplication.User.Entity.UserEntity;
import com.example.tick.myapplication.User.Presenter.Imp.ForgetPwPre;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.UserView;
import com.example.tick.myapplication.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/31.
 */
public class ForgetPwActivity extends Activity implements UserView {
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.forgetpw_photo)
    ClearEditText et_photo;
    @BindView(R.id.forgetpw_code)
    ClearEditText et_code;
    private ProgressDialog pDialog;
    private UserPresenter presenter;
    private static final int GETCODE = 0;
    private static final int GETPW = 1;
    private String user_account;
    private int VerificationCode = 0;//验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgetpw);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        pDialog = new ProgressDialog(this);
        presenter = new ForgetPwPre(this);
        et_photo.requestFocus();
    }

    @OnClick(R.id.many_top_back)
    void onBack() {
        finish();
    }

    @OnClick(R.id.forgetpw_bt_getpw)
    void onFindAccount() {
        if (isRightPhoto() && isRightCode()) {
//            HashMap map = new HashMap();
//            map.put("user_account", et_photo.getText().toString().trim());
            user_account = et_photo.getText().toString().trim();
            if (VerificationCode != Integer.parseInt(et_code.getText().toString().trim())) {
                Utils.showToast(ForgetPwActivity.this, "验证码错误");
                et_code.requestFocus();
                et_code.setText("");
            } else {
                /////////////////////需要重新设置页面再进行网络请求
//                presenter.doModel(user_account, GETPW);
                startActivity(new Intent(ForgetPwActivity.this, ModifyPwView.class).putExtra("title", "修改密码").putExtra("user_account", user_account));
                Utils.showToast(ForgetPwActivity.this, "验证码正确");
                finish();
            }

        }
    }

    @OnClick(R.id.forgetpw_bt_getcode)
    void onGetCode() {
        if (isRightPhoto()) {
            presenter.doModel(null, GETCODE);
            et_code.requestFocus();
        }
    }

    boolean isRightPhoto() {
        if (et_photo.getText().toString().trim().equals("")) {
            Utils.showToast(this, "手机号码不能为空");
            return false;
        } else if (et_photo.getText().toString().trim().length() != 11) {
            Utils.showToast(this, "手机号码格式不正确");
            return false;
        }
        return true;
    }

    boolean isRightCode() {
        if (et_code.getText().toString().trim().equals("")) {
            Utils.showToast(this, "验证码不能为空");
            et_code.requestFocus();
            return false;
        } else if (et_code.getText().toString().trim().length() != 6) {
            Utils.showToast(this, "验证码不正确");
            et_code.requestFocus();
            return false;
        }
        return true;
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
    public void onPersenter(Object o, Object o2) {

    }

    @Override
    public void onShowMess(Object o, Object o2) {
        Message message = new Message();
        if ((int) o2 == GETCODE) {//获取验证码操作
            MessageCode backCode = (MessageCode) o;
            message.what = backCode.getSendCode().getCode();//确认码
            message.obj = backCode.getSendCode().getMessage();//确认码信息
            message.arg1 = GETCODE;//标记操作
            message.arg2 = Integer.parseInt(backCode.getNumber());//验证码
        } else if ((int) o2 == GETPW) {//忘记密码操作
            BackCode backCode = (BackCode) o;
            message = new Message();
            message.what = backCode.getCode();//确认码
            message.obj = backCode.getMessage();//确认信息
            message.arg1 = GETPW;//验证码
        }
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("aaaaa", "handle.....what:" + msg.what);
            if (msg.what == 0) {//请求码失败
                Utils.showToast(ForgetPwActivity.this, msg.obj.toString());
            }
//            else if (msg.what == 1 && String.valueOf(msg.obj).contains("密码")) {//请求码正确  ,跳转到修改密码界面
//                startActivity(new Intent(ForgetPwActivity.this, ModifyPwView.class).putExtra("title", "修改密码").putExtra("user_account", user_account));
//                finish();
//            }
            else if (msg.what == 1 && String.valueOf(msg.obj).contains("短信") && msg.arg1 == GETCODE) {//请求码正确,不做处理
                VerificationCode = msg.arg2;
                Utils.showToast(ForgetPwActivity.this, "发送短信成功");
            }
        }
    };

    @Override
    public void onBackCode() {

    }
}
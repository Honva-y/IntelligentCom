package com.example.tick.myapplication.User.View.Imp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.MyView.ClearEditText;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Presenter.Imp.ModifyPwPre;
import com.example.tick.myapplication.User.Presenter.UserPresenter;
import com.example.tick.myapplication.User.View.UserView;
import com.example.tick.myapplication.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/9/28.
 */
public class ModifyPwView extends Activity implements UserView {
    @BindView(R.id.modifypw_et_pw)
    ClearEditText et_pw;
    @BindView(R.id.modifypw_et_surepw)
    ClearEditText et_sure;
    @BindView(R.id.many_top_title)
    TextView tittle;
    private String user_account;
    private UserPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register_modifypw);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tittle.setText(getIntent().getStringExtra("title"));
        user_account = getIntent().getStringExtra("user_account");
        presenter = new ModifyPwPre(this);
    }

    @OnClick(R.id.modifypw_ok_pw)
    void onOk() {
        if(isRightPw()){
            HashMap map = new HashMap();
            map.put("user_account",user_account);
            map.put("user_password",et_pw.getText().toString().trim());
            presenter.doModel(map,null);
        }
    }
    boolean isRightPw(){
        if(et_pw.getText().toString().equals("")||et_sure.getText().toString().equals("")){
            Utils.showToast(this,"密码不能为空");
            return false;
        }else if(!et_pw.getText().toString().trim().equals(et_sure.getText().toString().trim())){
            Utils.showToast(this,"密码不一致");
            return false;
        }
        return true;
    }
    @Override
    public void showDialog(String mess) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onPersenter(Object o, Object o2) {

    }

    @Override
    public void onShowMess(Object o, Object o2) {
        BackCode backCode = (BackCode) o;
        Message mess = new Message();
        mess.what = backCode.getCode();
        mess.obj = backCode.getMessage();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1) {
                Utils.showToast(ModifyPwView.this, "修改成功");
                finish();
            }else {
                Utils.showToast(ModifyPwView.this,"服务器出错，修改失败");
            }
        }
    };
    @Override
    public void onBackCode() {

    }
}

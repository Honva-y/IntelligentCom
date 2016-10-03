package com.example.tick.myapplication.Mine.View.Impl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tick.myapplication.Mine.Entity.UserInfo;
import com.example.tick.myapplication.Mine.Presenter.Impl.ComleteInfoImpl;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.MineView;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/31.
 */
public class CompleteInfoActivity extends Activity implements MineView {
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.conplete_et_realname)
    EditText realname;
    @BindView(R.id.conplete_et_card)
    EditText card;
    @BindView(R.id.conplete_et_community)
    EditText community;
    @BindView(R.id.conplete_et_email)
    EditText email;
    private MinePresenter presenter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_complete_info);
        ButterKnife.bind(this);
        initView();
    }

    //初始化控件
    private void initView() {
        presenter = new ComleteInfoImpl(this);
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("提交中...");
    }

    @OnClick(R.id.many_top_back)
    void OnBack() {
        finish();
    }

    @OnClick(R.id.completeinfo_bt_ok)
    void OnCompleteInfo() {
        if(!isEmpty()) return;//判空
        if (checkEmail(email.getText().toString()) && checkIdCard(card.getText().toString())) {
            UserInfo info = new UserInfo();
            info.setUser_realname(realname.getText().toString());
            info.setUser_card(card.getText().toString());
            info.setUser_community(community.getText().toString());
            info.setUser_email(email.getText().toString());
            presenter.postUser(info);
        } else if (!checkEmail(email.getText().toString())) {//判断不符合
            showToast("邮箱格式不正确");
            email.setText("");
            email.requestFocus();
        }else if(!checkIdCard(card.getText().toString())){
            showToast("身份证错误");
            card.setText("");
            card.requestFocus();
        }
    }

    public boolean checkEmail(String email) { //判断邮件格式是否正确
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean checkIdCard(String id) {    //判断身份证号码是否正确
        String check = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
        Pattern pattern = Pattern.compile(check);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }


    public boolean isEmpty(){
        if(realname.getText().toString().equals("")){
            showToast("真实姓名不能为空");
            realname.requestFocus();
            return false;
        }else if(card.getText().toString().equals("")){
            showToast("身份证不能为空");
            card.requestFocus();
            return false;
        }else if(community.getText().toString().equals("")){
            showToast("小区名不能为空");
            community.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void showDialog() {
        pDialog.show();
    }

    @Override
    public void hideDialog() {
        pDialog.dismiss();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showSuccess(Object userInfo) {

    }

    @Override
    public void doPresenter(Object o) {

    }

    void showToast(String mess){
        Utils.showToast(this,mess);
    }

}

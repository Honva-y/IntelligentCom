package com.example.tick.myapplication.Mine.View.Impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tick.myapplication.MainActivity;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.Mine.Presenter.Impl.ChangePwImpl;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.MineView;
import com.example.tick.myapplication.MyView.ActivityTask;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.View.LoginActivity;
import com.example.tick.myapplication.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/31.
 */
public class ChangePasswordActivity extends Activity implements MineView{
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.changepw_old)
    EditText oldpw;
    @BindView(R.id.changepw_new)
    EditText newpw;
    @BindView(R.id.changepw_new_sure)
    EditText surepw;
    private MinePresenter presenter;
    private ProgressDialog pDialog;
    private String pw;
    private int user_id;
    private SharedPreferences sharedPreferences;
    private SparseArray array;
    private ActivityTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_changepassword);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        presenter = new ChangePwImpl(this);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("正在修改...");
        //MIMA
        sharedPreferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        pw = sharedPreferences.getString("user_password","");
        user_id = sharedPreferences.getInt("user_id",0);//用户id，如果没有，默认为0
        array = new SparseArray();
        //将activity加入task中
        task = ActivityTask.getInstance();
        task.addActivity(this);
    }

    @OnClick(R.id.many_top_back)
    void OnBack() {
        finish();
    }
    @OnClick(R.id.changepassword_bt_ok)
    void OnChangePw() {
        if(!pw.equals(oldpw.getText().toString())){
            Utils.showToast(this,"密码错误");
            oldpw.setText("");
        }else if (TextUtils.isEmpty(oldpw.getText()) || TextUtils.isEmpty(newpw.getText()) || TextUtils.isEmpty(surepw.getText())) {
            Utils.showToast(this,"密码不能为空");
        } else if (!newpw.getText().toString().equals(surepw.getText().toString())) {
            Utils.showToast(this,"2次密码不一致");
            resetET();
        }else {
            array.put(0,user_id);
            array.put(1,newpw.getText().toString());
            presenter.postUser(array);
        }

    }
    public void resetET(){
        oldpw.setText("");
        newpw.setText("");
        surepw.setText("");
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
        Utils.showToast(this,"操作出错..");
    }

    @Override
    public void doPresenter(Object o) {

    }

    @Override
    public void BackData(Object o, Object o2) {

    }

    @Override
    public void showSuccess(Object backCode) {
        BackCode backCode1 = (BackCode) backCode;
        if(((BackCode) backCode).getCode()==1) {
            new AlertDialog.Builder(ChangePasswordActivity.this).setMessage("修改成功，请重新登陆").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    task.removeAll();
                }
            }).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }
}

package com.example.tick.myapplication.Propery.View.Imple;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.Propery.Presenter.Imple.SuggestProperty;
import com.example.tick.myapplication.Propery.Presenter.PropertyPersenter;
import com.example.tick.myapplication.Propery.View.PropertyView;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/30.
 */
public class SuggestActivity extends Activity implements PropertyView<BackCode>{
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.suggest_et_content)
    EditText content;
    private PropertyPersenter persenter;
    private SharedPreferences preferences;
    private SparseArray array;
    private ProgressDialog pDialog;
    private  Message message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprey_suggest);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.many_top_back)
    void OnBack() {
        finish();
    }
    @OnClick(R.id.suggest_bt_ok) void OnClickOk(){
        if(content.getText().toString().equals("")) {
            Utils.showToast(this, "提交信息不能为空");
        }else {
            array.put(1,content.getText().toString());
            persenter.postMessage(array);
        }
    }
    private void initView() {
        //获取标题并且设置标题
        title.setText( getIntent().getStringExtra("title"));
        persenter = new SuggestProperty(this);
        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        array = new SparseArray();
        int user_id = preferences.getInt("user_id",0);
        array.put(0,user_id);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("提交中...");
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
    public void onSuccess(BackCode backCode) {
        message = new Message();
        message.what = backCode.getCode();
        handler.sendMessage(message);
    }

    @Override
    public void onFaile(String mess) {
        message = new Message();
        message.what=2;
        message.obj = mess;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                Utils.showToast(getApplicationContext(), msg.obj+"");
            }else if(msg.what==1){
                Utils.showToast(getApplicationContext(), "建议提交成功，我们会尽快处理");
                finish();
            }else{
                Utils.showToast(getApplicationContext(), msg.obj+"");
            }
        }
    };
}

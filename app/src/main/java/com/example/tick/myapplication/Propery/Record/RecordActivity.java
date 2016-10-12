package com.example.tick.myapplication.Propery.Record;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tick.myapplication.Propery.Entity.RecordEntity;
import com.example.tick.myapplication.Propery.Presenter.Imple.RecordPre;
import com.example.tick.myapplication.Propery.Presenter.PropertyPersenter;
import com.example.tick.myapplication.Propery.View.PropertyView;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.View.LoginActivity;
import com.example.tick.myapplication.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by Tick on 2016/8/30.
 */
public class RecordActivity extends Activity implements PropertyView{
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.record_ptr)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.record_recycleview)
    RecyclerView recyclerView;
    private RecordAdapter adapter;
    private ProgressDialog pDialog;
    private PropertyPersenter persenter;
    private  RecordEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprey_record);
        ButterKnife.bind(this);
        initView();
    }
    @OnClick(R.id.many_top_back) void OnBack(){
        finish();
    }
    //初始化控件
    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        //进度对话框
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("加载中...");
        //初始化persener
        persenter = new RecordPre(this);
//        Log.d("aaaa", "user_id: "+UserLoginPresenter.USER_ID);
        persenter.postMessage(LoginActivity.USER_ID);
        //初始化adapter
        adapter = new RecordAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //分割线
//        recyclerView.addItemDecoration( new RecycleViewDivider(LinearLayoutManager.HORIZONTAL,1,R.color.colorback));
        //初始化ptr
//        ptrFrameLayout = new PtrFrameLayout(this);
        //设置下拉头
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15),0,0);
        header.setTextColor(getResources().getColor(R.color.colorBlue));
        header.initWithString("LAIFU");
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        persenter.postMessage(LoginActivity.USER_ID);
                        adapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                    }
                },2000);
            }
        });

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onSuccess(Object o) {
        entity = (RecordEntity) o;
        Message message = new Message();
        message.what = entity.getSendCode().getCode();//返回码，返回码信息
        message.obj = entity.getSendCode().getMessage();
        if(entity.getPaymentVo().size()==0){
            message.arg1 = 1;//记录为空
        }
        handler.sendMessage(message);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){//不成功
                Utils.showToast(RecordActivity.this,msg.obj.toString());
            }else {
                if(msg.arg1 == 1){
                    Utils.showToast(RecordActivity.this,"空空如也~");
                }else{
                    adapter.update(entity);
                }
            }
        }
    };
    @Override
    public void onFaile(String mess) {

    }
}

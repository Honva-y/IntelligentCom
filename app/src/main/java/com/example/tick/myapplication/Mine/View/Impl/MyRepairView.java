package com.example.tick.myapplication.Mine.View.Impl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.tick.myapplication.Mine.Aadapter.RepairAdapter;
import com.example.tick.myapplication.Mine.Entity.BackRepair;
import com.example.tick.myapplication.Mine.Presenter.Impl.RepairPresenter;
import com.example.tick.myapplication.Mine.View.MineView;
import com.example.tick.myapplication.MyView.RecycleViewDivider;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Entity.MessageCode;
import com.example.tick.myapplication.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by Tick on 2016/9/26.
 */
public class MyRepairView extends Activity implements MineView{
    private ProgressDialog pDialog;
    private String user_id;
    private RepairPresenter presenter;
    private RepairAdapter adapter;
    private List<BackRepair.RepairBean> repair;
    private BackRepair backRepair;
    @BindView(R.id.mine_repair_ptr)
    PtrFrameLayout ptrFramentLayout;
    @BindView(R.id.many_top_title)
    TextView tv_title;
    @BindView(R.id.mine_repair_recycleview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_myrepair);
        ButterKnife.bind(this);
        initView();
    }
    void initView(){
        //添加标题
        tv_title.setText(getIntent().getStringExtra("title"));
        user_id = getIntent().getStringExtra("userid");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("加载中...");

        presenter = new RepairPresenter(this);
        doPresenter(user_id);
        adapter = new RepairAdapter(MyRepairView.this);
        //设置分割线
        RecycleViewDivider divider = new RecycleViewDivider(LinearLayoutManager.HORIZONTAL, 1, R.color.colorback);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置下拉头
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
        header.setTextColor(getResources().getColor(R.color.colorBlue));
        header.initWithString("LAIFU");
        ptrFramentLayout.setHeaderView(header);
        ptrFramentLayout.addPtrUIHandler(header);
        ptrFramentLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.postUser(user_id);
                        adapter.notifyDataSetChanged();
                        ptrFramentLayout.refreshComplete();
                    }
                }, 1800);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }

    @OnClick(R.id.many_top_back)
    void onBack() {
        finish();
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
    public void showSuccess(Object o) {
        backRepair = (BackRepair) o;
        repair = ((BackRepair)o).getRepair();
        Message message = new Message();
        message.what = backRepair.getSendCode().getCode();
        message.obj = backRepair.getRepair();
        Log.d("bbbb", "showSuccess: "+backRepair.getRepair().size()+","+backRepair.getRepair().get(0).getRepair_project());
        handler.sendMessage(message);
//        adapter.updataData(repair);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                adapter.updataData((List<BackRepair.RepairBean>) msg.obj);
//                Log.d("aaaa", "handleMessage: handeler");
            }else {
                Utils.showToast(MyRepairView.this,"网络异常");
            }
        }
    };

    @Override
    public void doPresenter(Object o) {
        presenter.postUser(o.toString());
        showDialog();
    }
}

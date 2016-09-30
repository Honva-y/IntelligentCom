package com.example.tick.myapplication.Propery.Record;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.tick.myapplication.MyView.RecycleViewDivider;
import com.example.tick.myapplication.R;

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
public class RecordActivity extends Activity{
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.record_ptr)
    PtrClassicFrameLayout ptrFrameLayout;
    @BindView(R.id.record_recycleview)
    RecyclerView recyclerView;
    private RecordAdapter adapter;
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
        //初始化adapter
        adapter = new RecordAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //分割线
        recyclerView.addItemDecoration( new RecycleViewDivider(LinearLayoutManager.HORIZONTAL,1,R.color.colorback));
        //初始化ptr
        ptrFrameLayout = new PtrClassicFrameLayout(this);
        //设置下拉头
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15),0,0);
        header.setTextColor(R.color.colorBlue);
        header.initWithString("LAIFU");
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                },2000);
            }
        });

    }

}

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

import com.example.tick.myapplication.Mine.Aadapter.SuggestAdapter;
import com.example.tick.myapplication.Mine.Entity.BackSuggest;
import com.example.tick.myapplication.Mine.Presenter.Impl.SuggestPresenter;
import com.example.tick.myapplication.Mine.View.MineView;
import com.example.tick.myapplication.MyView.RecycleViewDivider;
import com.example.tick.myapplication.R;

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
public class MySuggestView extends Activity implements MineView {
    private ProgressDialog pDialog;
    private SuggestPresenter presenter;
    private List<BackSuggest.ComplainsBean> Complains = null;
    private SuggestAdapter adapter;
    private String user_id;
    @BindView(R.id.mine_suggest_ptr)
    PtrFrameLayout ptrFramentLayout;
    @BindView(R.id.many_top_title)
    TextView tv_title;
    @BindView(R.id.mine_suggest_recycleview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_mysuggest);
        ButterKnife.bind(this);
        initView();
    }

    void initView() {
        //添加标题
        tv_title.setText(getIntent().getStringExtra("title"));
        user_id = getIntent().getStringExtra("userid");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("加载中...");
        presenter = new SuggestPresenter(this);
        doPresenter(user_id);
        //适配器
        adapter = new SuggestAdapter(MySuggestView.this);
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

    @OnClick(R.id.many_top_back)
    void onBack() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
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
    public void showSuccess(Object backSuggest) {
        Complains = ((BackSuggest) backSuggest).getComplains();
        Log.d("bbb", "showSuccess: " + Complains.get(0).getComplains_content() + "," + Complains.get(0).getComplains_id() + ",大小" + Complains.size());
        adapter.updataData(Complains);
    }

    @Override
    public void doPresenter(Object o) {
        showDialog();
        presenter.postUser(o);//传递无用参数，请求数据
    }

}

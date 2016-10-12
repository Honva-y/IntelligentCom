package com.example.tick.myapplication.Topic.View;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.MyView.RecycleViewDivider;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.Topic.Adapter.TopicAdapter;
import com.example.tick.myapplication.Topic.Entity.TopicEntity;
import com.example.tick.myapplication.Topic.Entity.ZanList;
import com.example.tick.myapplication.Topic.Presenter.Imp.TopicPre;
import com.example.tick.myapplication.Topic.Presenter.TopicPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by Tick on 2016/9/5.
 */
public class TopicFragment extends Fragment implements TopicView {
    @BindView(R.id.topic_ptr)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.topic_recycler)
    RecyclerView recyclerView;
    private static final int REQUEST = 0;
    private static final int CLICKZAN = 1;
    private static final int COMMENT = 2;
    private View view;
    private TopicPresenter presenter;
    private ProgressDialog pDialog;
    private TopicAdapter adapter;
    private int user_id;
    private String user_nickname;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic_topic, container, false);
//        view = inflater.inflate(R.layout.topic_top_community_info, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        pDialog = new ProgressDialog(getActivity());
        preferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = preferences.getInt("user_id", 0);//获取用户id
        user_nickname = preferences.getString("user_nickname", "");//获取用户id
        presenter = new TopicPre(this);

        presenter.doRequest(user_id, null);

        initPtrFrameLayout();//初始化下拉刷新框架

        adapter = new TopicAdapter(getActivity(), presenter, user_id, user_nickname);
        //设置分隔线
        RecycleViewDivider divider = new RecycleViewDivider(LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.colorback));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initPtrFrameLayout() {
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.topic_top_community_info,null);
//        StoreHouseHeader header = new StoreHouseHeader(view);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
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
                        presenter.doRequest(user_id, null);
                        adapter.notifyDataSetChanged();
                        ptrFrameLayout.refreshComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onShowMess(Object o) {
        TopicEntity entity = (TopicEntity) o;
        adapter.upData(entity);
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }

    @Override
    public void showDialog() {
//        pDialog.show();
    }

    @Override
    public void hideDialog() {
//        pDialog.dismiss();
    }

    @Override
    public void doZanList(Object o) {
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                adapter.notifyDataSetChanged();
            }
        }
    };
}

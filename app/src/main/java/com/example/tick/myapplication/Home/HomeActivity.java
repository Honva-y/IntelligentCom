package com.example.tick.myapplication.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tick.myapplication.Home.Entity.NotifyEntity;
import com.example.tick.myapplication.Home.Presenter.HomePresenter;
import com.example.tick.myapplication.Home.Presenter.Imple.HomePresenterImp;
import com.example.tick.myapplication.Home.View.HomeView;
import com.example.tick.myapplication.MyView.ActivityTask;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.Home.View.Imple.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/29.
 */
public class HomeActivity extends Fragment implements HomeView{
    private View view;
    private Intent intent;
    private ProgressDialog pDialog;
    private HomePresenter presenter;
    @BindView(R.id.home_notifi_time)
    TextView tv_notify_time;
    @BindView(R.id.home_notifi_content)
    TextView tv_notify_content;
    private String date,content;
    private Message mess;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    private void initView() {
        pDialog = new ProgressDialog(getActivity());
        presenter = new HomePresenterImp(this);
        showDialog("玩命加载中...");
        presenter.postBack(0);//传递个无关参数
    }
@OnClick(R.id.home_iv_repair) void OnRepair(){
    intent = new Intent(getContext(),RepairActivity.class);
    intent.putExtra("title","自助修理");
    startActivity(intent);
}
@OnClick(R.id.home_iv_around) void OnAround(){
    intent = new Intent(getContext(),AroundActivity.class);
    intent.putExtra("title","周边");
    startActivity(intent);
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
    public void showMess(Object o) {
        int year = (((NotifyEntity)o).getNotifyList().getNotify_datetime().getYear()+100);
        int month = (((NotifyEntity)o).getNotifyList().getNotify_datetime().getMonth()+1);
        int day = (((NotifyEntity)o).getNotifyList().getNotify_datetime().getDate());
        date = year+"年"+month+"月"+day+"日";
        content = ((NotifyEntity)o).getNotifyList().getNotify_content();
        mess = new Message();
        handler.sendMessage(mess);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv_notify_time.setText(date);
            tv_notify_content.setText(content);
        }
    };
    @Override
    public void postPresenter(Object o) {
        presenter.postBack(o);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }
}

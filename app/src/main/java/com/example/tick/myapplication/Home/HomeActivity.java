package com.example.tick.myapplication.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tick.myapplication.Home.View.RepairActivity;
import com.example.tick.myapplication.R;

import java.io.BufferedReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/29.
 */
public class HomeActivity extends Fragment{
    private View view;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }
    private void initView() {

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
}

package com.example.tick.myapplication.Propery.View.Imple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.Propery.PayMent.PaymentActivity;
import com.example.tick.myapplication.Propery.Record.RecordActivity;
import com.example.tick.myapplication.Propery.View.PropertyView;
import com.example.tick.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/29.
 */
public class PropreyActivity extends Fragment {
    private View view;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_propery,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
    }
    @OnClick(R.id.propery_ll_payment) void OnPayment(){
        intent = new Intent(getContext(),PaymentActivity.class);
        intent.putExtra("title","物业缴费");
        startActivity(intent);
    }
    @OnClick(R.id.propery_ll_record) void OnRecord(){
        intent = new Intent(getContext(),RecordActivity.class);
        intent.putExtra("title","缴费记录");
        startActivity(intent);
    }
    @OnClick(R.id.propery_ll_suggest) void OnSuggest(){
        intent = new Intent(getContext(),SuggestActivity.class);
        intent.putExtra("title","投诉建议");
        startActivity(intent);
    }
}

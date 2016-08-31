package com.example.tick.myapplication.Mine;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/8/29.
 */
public class MineActivity extends Fragment implements View.OnClickListener{
    private View view;
    private LinearLayout chpassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine,container,false);
        initView();
        return view;
    }

    private void initView() {
        chpassword = (LinearLayout) view.findViewById(R.id.mine_ll_chpassword);
        chpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.mine_ll_chpassword:
                intent = new Intent(getContext(),ChangePasswordActivity.class);
                intent.putExtra("title","修改密码");
                startActivity(intent);
                break;
        }
    }
}

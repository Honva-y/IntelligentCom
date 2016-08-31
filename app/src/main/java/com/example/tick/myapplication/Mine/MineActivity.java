package com.example.tick.myapplication.Mine;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.LoginActivity;

/**
 * Created by Tick on 2016/8/29.
 */
public class MineActivity extends Fragment implements View.OnClickListener{
    private View view;
    private LinearLayout chpassword,logout;
    private Button bt_exit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine,container,false);
        initView();
        return view;
    }

    private void initView() {
        chpassword = (LinearLayout) view.findViewById(R.id.mine_ll_chpassword);
        logout = (LinearLayout) view.findViewById(R.id.mine_ll_logout);
        bt_exit = (Button) view.findViewById(R.id.mine_ll_exit);
        chpassword.setOnClickListener(this);
        logout.setOnClickListener(this);
        bt_exit.setOnClickListener(this);
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
            case R.id.mine_ll_logout:
                new AlertDialog.Builder(getContext()).setMessage("注销").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                }).setNegativeButton("取消",null).show();
                break;

            case R.id.mine_ll_exit:
                new AlertDialog.Builder(getContext()).setMessage("退出").setPositiveButton("确定",null).setNegativeButton("取消",null).show();
                break;


        }
    }
}

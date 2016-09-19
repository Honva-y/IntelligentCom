package com.example.tick.myapplication.Mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.MainActivity;
import com.example.tick.myapplication.Mine.Presenter.*;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Login.LoginActivity;


import java.io.File;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by Tick on 2016/8/29.
 */
public class MineActivity extends Fragment {
    private View view;
    private AlertDialog alertDialog;
    @BindView(R.id.mine_iv_usericon)
    ImageView usericon;
    @BindView(R.id.mine_tv_nickname)
    TextView tv_nickname;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOSE_PICTURE = 2;
    private Uri tempUri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }



    private void initView() { //初始化控件

    }

    @OnClick(R.id.mine_iv_usericon)

    void OnSelect() {//选择图片来源
        pickOrTake();
    }

    @OnClick(R.id.mine_ll_chpassword)
    void OnChangePw() {//跳转到修改密码页面
        startActivity(new Intent(getActivity(), ChangePasswordActivity.class).putExtra("title", "修改密码"));
    }

    @OnClick(R.id.mine_ll_completeinfo)
    void OnCompleteInfo() {  //跳转到完善信息页面
        startActivity(new Intent(getActivity(), CompleteInfoActivity.class).putExtra("title", "完善信息"));
    }

    @OnClick(R.id.mine_ll_logout)
    void OnLogout() { //注销账户
        new AlertDialog.Builder(getContext()).setMessage("注销").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        }).setNegativeButton("取消", null).show();
    }

    @OnClick(R.id.mine_ll_exit)
    void OnExit() {   //退出应用
        new AlertDialog.Builder(getContext()).setMessage("退出").setPositiveButton("确定", null).setNegativeButton("取消", null).show();
    }

    @OnLongClick(R.id.mine_tv_nickname)
    boolean OnNickName() {
        View layout = LayoutInflater.from(view.getContext()).inflate(R.layout.mine_alertdialog_nickname, null);
        final EditText editText = (EditText) layout.findViewById(R.id.mine_et_nickname);
        new AlertDialog.Builder(getActivity()).setCancelable(false).setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTv_nickname(editText.getText().toString());
            }
        }).setNegativeButton("取消", null).show();
        return true;
    }


    public void pickOrTake() { //选择拍照还是选择从图片库中选择，初始化对话框,创建一个alertDialog对象
        alertDialog = new AlertDialog.Builder(getActivity()).create();  //自定义布局
        View view = getActivity().getLayoutInflater().inflate(R.layout.mine_alertdialog_select, null); //设置对话框布局
        alertDialog.setView(view, 0, 0, 0, 0);//显示dialog,需要先显示
        alertDialog.show(); //获取显示设备的宽度
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();//获取对话框界面参数对象
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes(); //设置参数宽度
        params.width = width - (width / 6); //设置参数高度
        params.height = WindowManager.LayoutParams.WRAP_CONTENT; //布局位置
        params.gravity = Gravity.CENTER;
        alertDialog.getWindow().setAttributes(params);//按钮监听

        (view.findViewById(R.id.mine_bt_takephoto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onTakePhoto();
            }
        });
        (view.findViewById(R.id.mine_bt_pickphoto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onPickPhoto();
            }
        });
    }


    public void setUsericon(Bitmap bitmap) {  //设置头像
        usericon.setImageBitmap(bitmap);
    }

    public void setTv_nickname(String name) { //设置昵称
        tv_nickname.setText(name);
    }

    public void onTakePhoto() {//拍照
        Intent openCarmeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//创建相机意图
        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png"));//指定照片保存路径，每次拍照图片都会被覆盖
        openCarmeIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);//将文件输出到指定路径
        getActivity().startActivityForResult(openCarmeIntent, TAKE_PHOTO);
    }

    public void onPickPhoto() {  //打开图库
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(intent,CHOSE_PICTURE);
    }
}

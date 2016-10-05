package com.example.tick.myapplication.Mine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.Mine.Entity.BackCode;
import com.example.tick.myapplication.Mine.Presenter.Impl.MineImpl;
import com.example.tick.myapplication.Mine.Presenter.MinePresenter;
import com.example.tick.myapplication.Mine.View.Impl.MyRepairView;
import com.example.tick.myapplication.Mine.View.Impl.MySuggestView;
import com.example.tick.myapplication.Mine.View.MineView;
import com.example.tick.myapplication.MyView.CircleTransform;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.View.LoginActivity;
import com.example.tick.myapplication.Mine.View.Impl.ChangePasswordActivity;
import com.example.tick.myapplication.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by Tick on 2016/8/29.
 */
public class MineActivity extends Fragment implements MineView{
    private View view;
    private AlertDialog alertDialog;
    @BindView(R.id.mine_iv_usericon)
    ImageView usericon;
    @BindView(R.id.mine_tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.mine_tv_user_type)
    TextView tv_userType;
    @BindView(R.id.mine_tv_user_sex)
    TextView tv_userSex;
    @BindView(R.id.mine_tv_user_community)
    TextView tv_userCommunity;
    @BindView(R.id.mine_tv_user_email)
    TextView tv_userEmail;
    @BindView(R.id.mine_tv_user_photo)
    TextView tv_userPhoto;
//    @BindView(R.id.mine_ll_completeinfo)
//    LinearLayout ll_completeinfo;
//    @BindView(R.id.mine_tv_user_completeinfo)
//    TextView tv_completeinfo;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOSE_PICTURE = 2;
    private Uri tempUri;//拍照图片保存暂时地址
    private ProgressDialog pDialog;
    private MinePresenter presenter;
    private SharedPreferences sharedPreferences;
    private int user_id;
    private String nickname;
    private SparseArray array;
    private String imagePaht;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mine, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() { //初始化控件
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("加载ing..");
        presenter = new MineImpl(this);
        array = new SparseArray();
        setUserInfo();
    }

    private void setUserInfo() {//初始化业主信息
        sharedPreferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        tv_nickname.setText(sharedPreferences.getString("user_nickname", ""));
        tv_userType.setText(sharedPreferences.getString("user_type", ""));
        tv_userPhoto.setText(sharedPreferences.getString("user_photo", ""));
        tv_userSex.setText(sharedPreferences.getString("user_sex", ""));
        tv_userCommunity.setText(sharedPreferences.getString("user_community", ""));
        tv_userEmail.setText(sharedPreferences.getString("user_email", ""));
        user_id = sharedPreferences.getInt("user_id", 0);
//        docheckInfo(sharedPreferences.getString("user_card", ""), sharedPreferences.getString("user_approver", ""));
        Picasso.with(getActivity()).load(new MyData().getBaseUrl() + sharedPreferences.getString("user_head", ""))//加载头像
                .placeholder(R.mipmap.head)
                .error(R.mipmap.head).resize(80, 80)
                .transform(new CircleTransform()).into(usericon);
    }

    @OnClick(R.id.mine_iv_usericon)
    void OnSelect() {//选择图片来源
        pickOrTake();
    }

    @OnClick(R.id.mine_ll_chpassword)
    void OnChangePw() {//跳转到修改密码页面
        startActivity(new Intent(getActivity(), ChangePasswordActivity.class).putExtra("title", "修改密码"));
    }
//
//    @OnClick(R.id.mine_ll_completeinfo)
//    void OnCompleteInfo() {  //跳转到完善信息页面
//        startActivity(new Intent(getActivity(), CompleteInfoActivity.class).putExtra("title", "完善信息"));
//    }

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
        new AlertDialog.Builder(getContext()).setMessage("退出").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);//退出程序
            }
        }).setNegativeButton("取消", null).show();
    }

    @OnClick(R.id.mine_ll_mysuggest)
    void onSuggest() {
        startActivity(new Intent(getActivity(), MySuggestView.class).putExtra("title", "我的投诉建议").putExtra("userid",user_id+""));
    }

    @OnClick(R.id.mine_ll_myrepair)
    void onRepair() {
        startActivity(new Intent(getActivity(), MyRepairView.class).putExtra("title", "我的维修列表").putExtra("userid",user_id+""));
    }

    @OnLongClick(R.id.mine_tv_nickname)
    boolean OnNickName() {
        View layout = LayoutInflater.from(view.getContext()).inflate(R.layout.mine_alertdialog_nickname, null);
        final EditText editText = (EditText) layout.findViewById(R.id.mine_et_nickname);
        new AlertDialog.Builder(getActivity()).setCancelable(false).setView(layout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText().toString().equals("")) {
                    Utils.showToast(getActivity(), "昵称不能为空");
                } else {
                    array.clear();
                    array.put(0, 0);//修改昵称
                    array.put(1, user_id);
                    nickname = editText.getText().toString();
                    array.put(2, nickname);
                    presenter.postUser(array);
                }
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
                hideDialog();
                onTakePhoto();
                alertDialog.dismiss();
                array.clear();
                array.put(0, 1);//修改头像
                array.put(1, user_id);
                array.put(2, getImagePaht());
                presenter.postUser(array);
            }
        });
        (view.findViewById(R.id.mine_bt_pickphoto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideDialog();
                onPickPhoto();
                alertDialog.dismiss();
            }
        });
    }

    public void uploadUserHead() {
        array.clear();
        array.put(0, 1);//修改头像
        array.put(1, user_id);
        array.put(2, getImagePaht());
//        presenter.postUser(array);
    }

    public void setUsericon(Bitmap bitmap) {  //设置头像
        usericon.setImageBitmap(bitmap);
    }

    public void setTv_nickname(String name) { //设置昵称
        tv_nickname.setText(name);
    }

    public void onTakePhoto() {//拍照
        try {
            Intent openCarmeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//创建相机意图
            tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png"));//指定照片保存路径，每次拍照图片都会被覆盖
            openCarmeIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);//将文件输出到指定路径
            getActivity().startActivityForResult(openCarmeIntent, TAKE_PHOTO);
        } catch (Exception e) {
            e.printStackTrace();
            Utils.showToast(getActivity(), "没有可用摄像头");
        }
    }

    public void onPickPhoto() {  //打开图库
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(intent, CHOSE_PICTURE);
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
    public void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }

    @Override
    public void showError() {
        try {
            hideDialog();
            Utils.showToast(getActivity(), "操作出错：showError");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showSuccess(Object object) {
        Message message = new Message();
        message.what = ((BackCode)object).getCode();
        handler.sendMessage(message);
    }

    @Override
    public void doPresenter(Object o) {

    }


//    public void docheckInfo(String IdCard, String approver) {
//        if (!IdCard.equals("") && !approver.equals("")) {
//            ll_completeinfo.setClickable(false);//信息完善不可点击
//            tv_completeinfo.setText("信息已完善");
//            tv_completeinfo.setTextColor(getResources().getColor(R.color.colorGreen));
//        } else if (!IdCard.equals("") && approver.equals("")) {
//            ll_completeinfo.setClickable(false);//信息完善不可点击
//            tv_completeinfo.setText("信息审核中");
//            tv_completeinfo.setTextColor(getResources().getColor(R.color.colorBlue));
//        } else {
//            ll_completeinfo.setClickable(true);//信息完善不可点击
//            tv_completeinfo.setText("信息未完善");
//            tv_completeinfo.setTextColor(getResources().getColor(R.color.colorRed));
//        }
//    }

    public String getImagePaht() {
        return imagePaht;
    }

    public void setImagePaht(String imagePaht) {
        this.imagePaht = imagePaht;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Utils.showToast(getActivity(), "修改失败");
                    break;
                case 1:
                    setTv_nickname(nickname);
                    Utils.showToast(getActivity(), "修改成功");
                    break;
            }
        }
    };
}

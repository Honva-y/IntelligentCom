package com.example.tick.myapplication.Home.View.Imple;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.Home.Entity.RepairEntity;
import com.example.tick.myapplication.Home.HomeActivity;
import com.example.tick.myapplication.Home.Presenter.Imple.RepairPresenterImp;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.User.Entity.UserId;
import com.example.tick.myapplication.User.View.LoginActivity;
import com.example.tick.myapplication.Utils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/30.
 */
public class RepairActivity extends Activity implements DialogInterface.OnKeyListener {
    @BindView(R.id.home_tv_account)
    TextView tv_account;
    private RepairPresenterImp presenter;
    //获取时间格式时间,时间段
    String partTime;
    private String startTime;
    private String endTime;
    private ProgressDialog pDialog;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_repair);
        ButterKnife.bind(this);
        initView();
    }

    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.repair_tv_date)
    TextView tv_date;
    @BindView(R.id.repair_tv_time)
    TextView tv_time;
    @BindView(R.id.repair_et_content)
    EditText et_content;

    private void initView() {
        presenter = (RepairPresenterImp)new RepairPresenterImp(this);
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("提交维修信息...");
        pDialog.setOnKeyListener(this);
        pDialog.setCancelable(false);
        preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
//        Log.d("aaaa", "initView: "+preferences.getString("user_photo","123123"));
        tv_account.setText(preferences.getString("user_photo",""));
    }
    public Handler repairHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    pDialog.dismiss();
                    Toast.makeText(RepairActivity.this, "提交成功，我们会尽快安排。谢谢", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 1:
                    pDialog.dismiss();
                    Toast.makeText(RepairActivity.this, "维修提交信息出错", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @OnClick(R.id.many_top_back)
    void OnBack() {
        finish();
    }

    @OnClick(R.id.repair_bt_ok)
    void OnOk() {
        //判空方法
        if (TextUtils.isEmpty(tv_date.getText())) {
            Utils.showToast(RepairActivity.this,"请选择维修时间");
        } else if (TextUtils.isEmpty(tv_time.getText())){
            Utils.showToast(RepairActivity.this,"请选择时间段");
        }else if(TextUtils.isEmpty(et_content.getText())) {
            Utils.showToast(RepairActivity.this,"维修内容不能为空");
        } else
        {
            RepairEntity entity = new RepairEntity();
            entity.setRepair_starttime(startTime);
            entity.setRepair_endtime(endTime);
            entity.setRepair_project(et_content.getText().toString());
            entity.setRepair_userid(LoginActivity.USER_ID);
            presenter.postRepairData(entity);
            pDialog.show();
        }


    }

    @OnClick(R.id.repair_tv_date)
    void OnDatePick() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RepairActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_date.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                partTime = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
            }
        }, year, month, date);
        datePickerDialog.show();
    }

    @OnClick(R.id.repair_tv_time)
    void OnTimePick() {
        View view = LayoutInflater.from(this).inflate(R.layout.repair_alertdialog_select, null);
        //创建一个对话框
        final AlertDialog alertDialog = new AlertDialog.Builder(RepairActivity.this).create();
        //设置对话框
        alertDialog.setView(view, 0, 0, 0, 0);
        //需要先显示再进行属性设置
        alertDialog.show();
        //获取屏幕宽度
        int width = getWindowManager().getDefaultDisplay().getWidth();
        final WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        params.width = width - (width / 6);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        alertDialog.getWindow().setAttributes(params);

        view.findViewById(R.id.repair_bt_morning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_time.setText(R.string.repair_morning);
                startTime = partTime+" 9:00:00";
                endTime = partTime+" 12:00:00";
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.repair_bt_afternoon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_time.setText(R.string.repair_afternoon);
                startTime = partTime+" 15:00:00";
                endTime = partTime+" 18:00:00";
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            pDialog.dismiss();
        }
        return false;
    }
}

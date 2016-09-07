package com.example.tick.myapplication.Home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by Tick on 2016/8/30.
 */
public class RepairActivity extends Activity{
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
    private void initView() {
        //获取标题并且设置标题
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
    }
    @OnClick(R.id.many_top_back) void OnBack(){
        finish();
    }
    @OnClick(R.id.repair_bt_ok) void OnOk(){
        //判空方法
        if(isEmpty()) {
            Toast.makeText(RepairActivity.this, "提交成功，谢谢您的支持", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(RepairActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.repair_tv_date) void OnDatePick(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RepairActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_date.setText(year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
            }
        },year,month,date);
        datePickerDialog.show();
    }
    @OnClick(R.id.repair_tv_time) void OnTimePick(){
        View view = LayoutInflater.from(this).inflate(R.layout.repair_alertdialog_select,null);
        //创建一个对话框
        final AlertDialog alertDialog = new AlertDialog.Builder(RepairActivity.this).create();
        //设置对话框
        alertDialog.setView(view,0,0,0,0);
        //需要先显示再进行属性设置
        alertDialog.show();
        //获取屏幕宽度
        int width = getWindowManager().getDefaultDisplay().getWidth();
        WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
        params.width = width - (width/6);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity  = Gravity.CENTER;
        alertDialog.getWindow().setAttributes(params);

       view.findViewById(R.id.repair_bt_morning).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tv_time.setText(R.string.repair_morning);
               alertDialog.dismiss();
           }
       });
        view.findViewById(R.id.repair_bt_afternoon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_time.setText(R.string.repair_afternoon);
                alertDialog.dismiss();
            }
        });
    }
    public boolean isEmpty() {
        return true;
    }
}

package com.example.tick.myapplication.Propery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/8/30.
 */
public class PaymentActivity extends Activity implements View.OnClickListener{
    private TextView title;
    private ImageView iv_back;
    private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprey_payment);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.many_top_title);
        iv_back = (ImageView) findViewById(R.id.many_top_back);
        ok = (Button) findViewById(R.id.payment_bt_ok);
        //获取标题并且设置标题
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        iv_back.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.many_top_back:
                finish();
                break;
            case R.id.payment_bt_ok:
                Toast.makeText(PaymentActivity.this, "缴费成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

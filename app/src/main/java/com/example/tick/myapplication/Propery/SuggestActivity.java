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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/30.
 */
public class SuggestActivity extends Activity implements View.OnClickListener{
    private TextView title;
    private ImageView iv_back;
    @BindView(R.id.suggest_bt_ok)
    Button bt_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprey_suggest);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.many_top_title);
        iv_back = (ImageView) findViewById(R.id.many_top_back);
        //获取标题并且设置标题
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        iv_back.setOnClickListener(this);
        bt_ok.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.many_top_back:
                finish();
                break;
            case R.id.suggest_bt_ok:
                Toast.makeText(SuggestActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

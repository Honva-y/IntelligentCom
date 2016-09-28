package com.example.tick.myapplication.Propery.PayMent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.MyView.RecycleViewDivider;
import com.example.tick.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by Tick on 2016/8/30.
 */
public class PaymentActivity extends Activity {
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.propery_ptr)
    PtrFrameLayout ptrFragment;
    @BindView(R.id.payment_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.payment_tv_sum)
    TextView tv_sum;
    @BindView(R.id.payment_cb_select)
    CheckBox checkSelect;
    private SparseArray<Boolean> checkState;
    private SparseArray<String> type;
    private SparseArray<Double> fee;
    private MyAdapter myAdapter;
    private double sum = 0;//费用总和
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprey_payment);
        ButterKnife.bind(this);
        //初始化数据
        initData();
        //初始化控件
        initView();
    }

    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        //适配器
        myAdapter = new MyAdapter();
        //recycler
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //分割线
        RecycleViewDivider divider = new RecycleViewDivider(LinearLayoutManager.HORIZONTAL);
        divider.setSize(1);//设置分割线大小
        divider.setColor(R.color.colorback);//设置分割线颜色
        recyclerView.addItemDecoration(divider);

        //设置下拉头
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15),0,0);
        header.setTextColor(R.color.colorBlue);
        header.initWithString("LAIFU");
        ptrFragment.setHeaderView(header);
        ptrFragment.addPtrUIHandler(header);
        ptrFragment.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },2000);
            }
        });
    }
    public void initData(){//初始化测试数据
        checkState = new SparseArray<>();
        type = new SparseArray<>();
        fee = new SparseArray<>();
        for (int i = 0; i <10; i++) {
            checkState.put(i,false);
            type.put(i,"电费:"+i);
            fee.put(i, (double) i);
        }
        setSum();
    }
    @OnClick(R.id.many_top_back)
    void OnBack() {
        finish();
    }
    @OnClick(R.id.payment_bt_ok)
    void OnClickOk() {
        Toast.makeText(PaymentActivity.this, "缴费成功", Toast.LENGTH_SHORT).show();
        finish();
    }
    @OnClick(R.id.payment_cb_select)
    void OnSelect(){
        if(checkSelect.isChecked()) {//判断是否被全选
            for (int i = 0; i < checkState.size(); i++) {
                if(!checkState.get(i)) {//判断此checkbox是否已经选过
                    checkState.setValueAt(i, true);
                    myAdapter.notifyItemChanged(i);
                    addPrice(fee.get(i)+"");//将价格加入
                }
            }
        }else if(!checkSelect.isChecked()){
            for (int i = 0; i < checkState.size(); i++) {
                if(checkState.get(i)) {
                    checkState.setValueAt(i, false);
                    myAdapter.notifyItemChanged(i);
                    removePrice(fee.get(i)+"");
                }
            }
        }
        setSum();
    }
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = null;
            View view = LayoutInflater.from(PaymentActivity.this).inflate(R.layout.payment_ll_item,parent,false);
            if(myViewHolder==null){
                myViewHolder = new MyViewHolder(view);
            }
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
                holder.tv_type.setText(type.valueAt(position));
                holder.tv_fee.setText(fee.valueAt(position)+"");
                holder.checkBox.setChecked(checkState.valueAt(position));
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        checkState.setValueAt(position,isChecked);
                        if(holder.checkBox.isChecked()){
                            addPrice(holder.tv_fee.getText().toString());//计算价格
                        }else{
                            removePrice(holder.tv_fee.getText().toString());//计算价格
                        }
                        setSum();
                    }
                });
        }

        @Override
        public int getItemCount() {
            return checkState.size();
        }
    }
    public void addPrice(String money){//添加项目，计算费用
        sum+=Double.parseDouble(money);
    }
    public void removePrice(String money){//减少项目，计算费用
        sum-=Double.parseDouble(money);
    }
    public void setSum() {//设置总价
        tv_sum.setText(sum+"");
    }
}

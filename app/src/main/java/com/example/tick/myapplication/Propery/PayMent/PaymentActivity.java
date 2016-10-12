package com.example.tick.myapplication.Propery.PayMent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.MyView.RecycleViewDivider;
import com.example.tick.myapplication.Propery.Entity.PaymentEntity;
import com.example.tick.myapplication.Propery.Entity.RecordEntity;
import com.example.tick.myapplication.Propery.Presenter.Imple.PaymentPre;
import com.example.tick.myapplication.Propery.View.PropertyView;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.Utils;

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
public class PaymentActivity extends Activity implements PropertyView {
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
    private SparseArray<Boolean> checkState = new SparseArray<>();
    private SparseArray<String> type = new SparseArray<>();
    private SparseArray<Double> fee = new SparseArray<>();
    private MyAdapter myAdapter;
    private double sum = 0;//费用总和
    private PaymentPre presenter;
    private int user_id;
    private Message message;
    private PaymentEntity entity;
    private final int FAILED_CODE = 0;
    private final int SUCCESS_CODE = 1;
    private final int INIT_DATA = 2;
    private final int SUCCESS_DATA = 6;
    private final int EMPETY_DATA = 7;
    private final int REMOVE_PRICE = 8;
    private final int ADD_PRICE = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proprey_payment);
        ButterKnife.bind(this);
        //初始化控件
        initView();
    }

    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        presenter = new PaymentPre(this);
        user_id = getIntent().getIntExtra("user_id", 0);
        presenter.postMessage(user_id);
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
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
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
                        presenter.postMessage(user_id);
                        myAdapter.notifyDataSetChanged();
                        ptrFragment.refreshComplete();
                    }
                }, 2000);
            }
        });
    }

    public void initData(PaymentEntity entity) {//初始化测试数据

        for (int i = 0; i < entity.getPaymentVo().size(); i++) {
            checkState.put(i, false);
            type.put(i, entity.getPaymentVo().get(i).getPaymenttype().getPaymenttype_name());
            fee.put(i, (double) entity.getPaymentVo().get(i).getPaymenttype().getPaymenttype_money());
        }
        Message message = new Message();
        message.arg1 = INIT_DATA;
        handler.sendMessage(message);
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
    void OnSelect() {
        if (checkSelect.isChecked()) {//判断是否被全选
            for (int i = 0; i < checkState.size(); i++) {
                if (!checkState.get(i)) {//判断此checkbox是否已经选过
                    checkState.setValueAt(i, true);
                    myAdapter.notifyItemChanged(i);
                    addPrice(fee.get(i) + "");//将价格加入
                }
            }
        } else if (!checkSelect.isChecked()) {
            for (int i = 0; i < checkState.size(); i++) {
                if (checkState.get(i)) {
                    checkState.setValueAt(i, false);
                    myAdapter.notifyItemChanged(i);
                    removePrice(fee.get(i) + "");
                }
            }
        }
        setSum();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onSuccess(Object o) {
        entity = (PaymentEntity) o;
        message = new Message();
        message.what = entity.getSendCode().getCode();
        if (entity.getPaymentVo().size() == 0) {//如果数据为空
            message.arg1 = EMPETY_DATA;
        } else {
            message.arg1 = SUCCESS_DATA;
        }
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SUCCESS_CODE) {
                Log.d("aaaaaaaaaa", "aaaaaaaaaa: "+msg.arg1);
                switch (msg.arg1) {
                    case ADD_PRICE:
                        addPrice(msg.obj.toString());
                        setSum();
                        break;
                    case REMOVE_PRICE:
                        removePrice(msg.obj.toString());
                        setSum();
                        break;
                    case EMPETY_DATA:
                        Utils.showToast(PaymentActivity.this, "空空如也~");
                        break;
                    case SUCCESS_DATA:
                        initData(entity);
                        break;
                    default:
                        setSum();
                        break;
                }
            } else if (msg.what == FAILED_CODE) {
                Utils.showToast(PaymentActivity.this, "请求失败");
            }

        }
    };

    @Override
    public void onFaile(String mess) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkState.clear();
        type.clear();
        fee.clear();
    }

    private class MyAdapter extends RecyclerView.Adapter<PaymentViewHolder> {

        @Override
        public PaymentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            PaymentViewHolder myViewHolder = null;
            View view = LayoutInflater.from(PaymentActivity.this).inflate(R.layout.payment_ll_item, parent, false);
            if (myViewHolder == null) {
                myViewHolder = new PaymentViewHolder(view);
            }
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final PaymentViewHolder holder, final int position) {
            if (checkState.size() != 0) {
                holder.tv_type.setText(type.valueAt(position));
                holder.tv_fee.setText(fee.valueAt(position) + "");
                holder.checkBox.setChecked(checkState.valueAt(position));
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Message message = new Message();
                        checkState.setValueAt(position, isChecked);
                        if (holder.checkBox.isChecked()) {
                            message.arg1 = ADD_PRICE;//添加
                            message.obj = holder.tv_fee.getText().toString();
                        } else {
                            message.arg1 = REMOVE_PRICE;//减少
                            message.obj = holder.tv_fee.getText().toString();
                        }
                        handler.sendMessage(message);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return checkState.size();
        }
    }


    public void addPrice(String money) {//添加项目，计算费用
        sum += Double.parseDouble(money);
    }

    public void removePrice(String money) {//减少项目，计算费用
        sum -= Double.parseDouble(money);
    }

    public void setSum() {//设置总价
        tv_sum.setText(sum + "");
    }
}

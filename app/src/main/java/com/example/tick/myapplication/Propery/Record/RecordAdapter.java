package com.example.tick.myapplication.Propery.Record;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.Propery.Entity.RecordEntity;
import com.example.tick.myapplication.Propery.Record.ItemRecycle.IteamAdapter;
import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/9/12.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordViewHolder> {
    private RecordActivity myActivity;
    private RecordEntity entity;

    public RecordAdapter(RecordActivity myActivity) {
        this.myActivity = myActivity;
    }

    public void update(RecordEntity entity) {
        this.entity = entity;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecordViewHolder holder = null;
        View view = LayoutInflater.from(myActivity).inflate(R.layout.record_ll_item, parent, false);
        if (holder == null) {
            holder = new RecordViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        if (entity != null) {
            if (entity.getPaymentVo().get(position) != null) {
                //有数据
                holder.type.setText(entity.getPaymentVo().get(position).getPaymenttype().getPaymenttype_name());
                holder.fee.setText(entity.getPaymentVo().get(position).getPaymenttype().getPaymenttype_money()+"");
                int year = entity.getPaymentVo().get(position).getPayment().getPayment_complettime().getYear()+1900;
                int month = entity.getPaymentVo().get(position).getPayment().getPayment_complettime().getMonth()+1;
                int date = entity.getPaymentVo().get(position).getPayment().getPayment_complettime().getDate();
                holder.time.setText(year + "年" + month + "月" + date + "日");
                holder.line.setVisibility(View.GONE);
            }
            else if(entity.getPaymentVo().get(position) == null){ //无数据，并且下一个vo为空，类型、费用隐藏；显示时间
                holder.type.setVisibility(View.GONE);
                holder.fee.setVisibility(View.GONE);
                holder.time.setVisibility(View.GONE);
                holder.line.setVisibility(View.VISIBLE);
            }
        } else {
            Log.d("cccc", "onBindViewHolder: 不错");
            return;
        }
    }

    @Override
    public int getItemCount() {//固定返回3个月的缴费情况，查看更多进入网页
        if (entity != null && entity.getPaymentVo().size()!=0)
            return entity.getPaymentVo().size();
        else
            return 0;
    }
}

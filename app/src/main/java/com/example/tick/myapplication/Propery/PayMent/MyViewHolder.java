package com.example.tick.myapplication.Propery.PayMent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tick.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tick on 2016/9/10.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public TextView tv_type;
    public TextView tv_fee;
    public LinearLayout ll_item;
    public MyViewHolder(View itemView) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.payment_checkbox);
        tv_type = (TextView) itemView.findViewById(R.id.payment_type);
        tv_fee = (TextView) itemView.findViewById(R.id.payment_fee);
        ll_item = (LinearLayout) itemView.findViewById(R.id.payment_iteam);
    }

}

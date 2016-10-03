package com.example.tick.myapplication.Mine.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/9/27.
 */
public class RepairHolder extends RecyclerView.ViewHolder {
    public TextView tv_content;
    public TextView tv_date;
    public TextView tv_replytime;
    public TextView tv_done;
    public RepairHolder(View itemView) {
        super(itemView);
        tv_content = (TextView) itemView.findViewById(R.id.repair_content);
        tv_date = (TextView) itemView.findViewById(R.id.repair_date);
        tv_done = (TextView) itemView.findViewById(R.id.repair_done);
        tv_replytime = (TextView) itemView.findViewById(R.id.repair_reply_date);
    }
}

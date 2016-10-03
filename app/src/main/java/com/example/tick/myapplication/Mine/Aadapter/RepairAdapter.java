package com.example.tick.myapplication.Mine.Aadapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.Mine.Entity.BackRepair;
import com.example.tick.myapplication.Mine.Entity.BackSuggest;
import com.example.tick.myapplication.Mine.View.Impl.MyRepairView;
import com.example.tick.myapplication.Mine.ViewHolder.RepairHolder;
import com.example.tick.myapplication.Mine.ViewHolder.SuggestHolder;
import com.example.tick.myapplication.R;

import java.util.List;

/**
 * Created by Tick on 2016/9/26.
 */
public class RepairAdapter extends RecyclerView.Adapter<RepairHolder> {
    private MyRepairView repairView;
    private List<BackRepair.RepairBean> repairBean;

    public RepairAdapter(MyRepairView repairView) {
        this.repairView = repairView;
    }

    public void updataData(List<BackRepair.RepairBean> repairBean) {
        this.repairBean = repairBean;
    }

    @Override
    public RepairHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RepairHolder holder = null;
        View view = LayoutInflater.from(repairView).inflate(R.layout.repair_ll_item, parent, false);
        if (holder == null) {
            holder = new RepairHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RepairHolder holder, int position) {
        if (repairBean != null) {
            holder.tv_content.setText(repairBean.get(position).getRepair_project());
            holder.tv_date.setText(repairBean.get(position).getRepair_decldatatime() + "");
            int state = repairBean.get(position).getRepair_state();
            if (state == 0) {
                holder.tv_done.setTextColor(repairView.getResources().getColor(R.color.colorRed));
                holder.tv_done.setText("未处理.");
            } else if (state == 1) {
                holder.tv_done.setTextColor(repairView.getResources().getColor(R.color.colorBlue));
                holder.tv_done.setText("已处理.");
            } else {
                holder.tv_done.setTextColor(repairView.getResources().getColor(R.color.colorRed));
                holder.tv_done.setText("处理中...");
            }
            holder.tv_replytime.setText(repairBean.get(position).getRepair_completetime() + "");
        }else
            return;
    }

    @Override
    public int getItemCount() {
        if (repairBean != null &&repairBean.get(0).getRepair_starttime()!=null) {
            if ((repairBean.size() <= 3))
                return repairBean.size();
            else
                return 3;
        } else
            return 0;
    }
}

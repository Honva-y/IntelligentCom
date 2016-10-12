package com.example.tick.myapplication.Mine.Aadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.Mine.Entity.BackSuggest;
import com.example.tick.myapplication.Mine.View.Impl.MySuggestView;
import com.example.tick.myapplication.Mine.ViewHolder.SuggestHolder;
import com.example.tick.myapplication.R;

import java.util.List;

/**
 * Created by Tick on 2016/9/26.
 */
public class SuggestAdapter extends RecyclerView.Adapter<SuggestHolder> {
    private BackSuggest backSuggest;
    private MySuggestView suggestView;

    public SuggestAdapter(MySuggestView suggestView) {
        this.suggestView = suggestView;
    }

    public void updataData(BackSuggest backSuggest) {
        this.backSuggest = backSuggest;
    }

    @Override
    public SuggestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SuggestHolder holder = null;
        View view = LayoutInflater.from(suggestView).inflate(R.layout.suggest_ll_item, parent, false);
        if (holder == null) {
            holder = new SuggestHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final SuggestHolder holder, final int position) {
        if (backSuggest != null) {
            if (backSuggest.getComplains().size() != 0) {
                holder.tv_content.setText(backSuggest.getComplains().get(position).getComplains_content());
                int year = backSuggest.getComplains().get(position).getComplains_datetime().getYear() + 1900;//获取时间
                int month = backSuggest.getComplains().get(position).getComplains_datetime().getMonth() + 1;
                int day = backSuggest.getComplains().get(position).getComplains_datetime().getDay();
                holder.tv_date.setText(year + "年" + month + "月" + day + "日");
                int state = backSuggest.getComplains().get(position).getComplains_state();
                if (state == 0) {
                    holder.tv_done.setTextColor(suggestView.getResources().getColor(R.color.colorRed));
                    holder.tv_done.setText("未处理.");
                    holder.tv_replytime.setText("");
                } else if (state == 1) {
                    holder.tv_done.setTextColor(suggestView.getResources().getColor(R.color.colorBlue));
                    holder.tv_done.setText("已处理.");
                    int re_year = backSuggest.getComplains().get(position).getComplains_replytime().getYear() + 1900;//获取时间
                    int re_month = backSuggest.getComplains().get(position).getComplains_replytime().getMonth() + 1;
                    int re_day = backSuggest.getComplains().get(position).getComplains_replytime().getDay();
                    holder.tv_replytime.setText(re_year + "年" + re_month + "月" + re_day + "日");
                } else {
                    holder.tv_done.setTextColor(suggestView.getResources().getColor(R.color.colorRed));
                    holder.tv_done.setText("处理中...");
                    holder.tv_replytime.setText("");
                }
            }
        } else {
            return;
        }

    }


    @Override
    public int getItemCount() {//返回最近三条
        if (backSuggest != null) {
            if (backSuggest.getComplains().size() <= 3)
                return backSuggest.getComplains().size();
            else
                return 3;
        } else
            return 0;
    }
}

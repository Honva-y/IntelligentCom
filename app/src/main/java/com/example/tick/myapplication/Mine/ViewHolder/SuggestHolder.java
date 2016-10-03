package com.example.tick.myapplication.Mine.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/9/26.
 */
public class SuggestHolder extends RecyclerView.ViewHolder {
    public TextView tv_content;
    public TextView tv_date;
    public TextView tv_replytime;
    public TextView tv_done;
    public SuggestHolder(View itemView) {
        super(itemView);
        tv_content = (TextView) itemView.findViewById(R.id.suggest_content);
        tv_date = (TextView) itemView.findViewById(R.id.suggest_date);
        tv_done = (TextView) itemView.findViewById(R.id.suggest_done);
        tv_replytime = (TextView) itemView.findViewById(R.id.suggest_reply_date);
    }
}

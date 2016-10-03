package com.example.tick.myapplication.Propery.Record;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.Propery.Record.ItemRecycle.IteamAdapter;
import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/9/12.
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordViewHolder> {
    private RecordActivity myActivity;
    private IteamAdapter adapter;
    public RecordAdapter(RecordActivity myActivity) {
        this.myActivity = myActivity;
        adapter = new IteamAdapter(myActivity);
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
    public void onBindViewHolder( RecordViewHolder holder, int position) {
//        holder.myTest.setText(""+position);
    }

    @Override
    public int getItemCount() {//固定返回3个月的缴费情况，查看更多进入网页
        return 15;
    }
}

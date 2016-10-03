package com.example.tick.myapplication.Propery.Record.ItemRecycle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tick.myapplication.Propery.Record.RecordActivity;
import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/9/12.
 */
public class IteamAdapter extends RecyclerView.Adapter<IteamViewHolder> {
    RecordActivity iteamActivyt;

    public IteamAdapter(RecordActivity iteamActivyt) {
        this.iteamActivyt = iteamActivyt;
    }

    @Override
    public IteamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IteamViewHolder viewHolder = null;
        View view = LayoutInflater.from(iteamActivyt).inflate(R.layout.record_ll_item_item,parent,false);
        if(viewHolder==null){
            viewHolder = new IteamViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IteamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

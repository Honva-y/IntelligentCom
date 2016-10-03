package com.example.tick.myapplication.Propery.Record.ItemRecycle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tick.myapplication.Propery.Record.RecordActivity;
import com.example.tick.myapplication.R;

/**
 * Created by Tick on 2016/9/12.
 */
public class IteamViewHolder extends RecyclerView.ViewHolder{
    TextView type,fee;
    public IteamViewHolder(View itemView) {
        super(itemView);
        type = (TextView) itemView.findViewById(R.id.record_type);
        fee = (TextView) itemView.findViewById(R.id.record_fee);
    }
}

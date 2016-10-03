package com.example.tick.myapplication.Propery.Record;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tick.myapplication.R;

import org.w3c.dom.Text;

/**
 * Created by Tick on 2016/9/12.
 */
public class RecordViewHolder extends RecyclerView.ViewHolder {
    TextView type,fee;
    public RecordViewHolder(View itemView) {
        super(itemView);
        type = (TextView) itemView.findViewById(R.id.record_type);
        fee = (TextView) itemView.findViewById(R.id.record_fee);
    }

}

package com.example.tick.myapplication.Topic.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tick.myapplication.R;

import butterknife.ButterKnife;

/**
 * Created by Tick on 2016/9/28.
 */
public class TopicHolder extends RecyclerView.ViewHolder{
    public LinearLayout topic_ll_clickzan;
    public ImageView user_head,iv_comment,iv_clickZan;
    public TextView user_nickname;
    public TextView content;
    public TextView date;
    public TextView zanList;
    public TextView commentList;
    public ImageView delete;

    public TopicHolder(View itemView) {
        super(itemView);
//        ButterKnife.bind(itemView);
        topic_ll_clickzan = (LinearLayout) itemView.findViewById(R.id.topic_ll_clickzan);
        user_head = (ImageView) itemView.findViewById(R.id.topic_iv_userhead);
        user_nickname = (TextView) itemView.findViewById(R.id.topic_tv_nickname);
        content = (TextView) itemView.findViewById(R.id.topic_tv_content);
        date = (TextView) itemView.findViewById(R.id.topic_tv_date);
        zanList = (TextView) itemView.findViewById(R.id.topic_zan_list);
        commentList = (TextView) itemView.findViewById(R.id.topic_comment_list);
        delete = (ImageView) itemView.findViewById(R.id.topic_delete);
        iv_comment = (ImageView) itemView.findViewById(R.id.topic_iv_comment);
        iv_clickZan = (ImageView) itemView.findViewById(R.id.topic_clickzan);
    }
}

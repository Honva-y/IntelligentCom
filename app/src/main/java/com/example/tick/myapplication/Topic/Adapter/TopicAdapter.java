package com.example.tick.myapplication.Topic.Adapter;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.tick.myapplication.GlobalValue.MyData;
import com.example.tick.myapplication.MyView.CircleTransform;
import com.example.tick.myapplication.R;
import com.example.tick.myapplication.Topic.Entity.TopicEntity;
import com.example.tick.myapplication.Topic.Entity.ZanList;
import com.example.tick.myapplication.Topic.Presenter.Imp.TopicPre;
import com.example.tick.myapplication.Topic.Presenter.TopicPresenter;
import com.example.tick.myapplication.Topic.View.TopicView;
import com.example.tick.myapplication.Topic.ViewHolder.TopicHolder;
import com.example.tick.myapplication.Utils;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tick on 2016/9/28.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicHolder> {
    private FragmentActivity parentView;
    private TopicEntity entity;
    private TopicPresenter presenter;
    private int user_id;
    private String nickname;
    private ZanList zanList;

    public TopicAdapter(FragmentActivity parentView, TopicPresenter presenter, int user_id, String nickname) {
        this.parentView = parentView;
        this.presenter = presenter;
        this.user_id = user_id;
        this.nickname = nickname;
    }

    public void upData(TopicEntity entity) {
        this.entity = entity;
    }

    @Override
    public TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TopicHolder holder = null;
        View view = LayoutInflater.from(parentView).inflate(R.layout.topic_ll_item, parent, false);
        if (holder == null) {
            holder = new TopicHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicHolder holder, int position) {
        if (entity != null) {
            Picasso.with(parentView).load(new MyData().getBaseUrl() + entity.getTopicList().get(position).getAuthor().getUser_head()).transform(new CircleTransform()).into(holder.user_head);
            holder.user_nickname.setText(entity.getTopicList().get(position).getAuthor().getUser_nickname());
            holder.content.setText(entity.getTopicList().get(position).getTopic().getTopic_comment());
            holder.date.setText(getYearMonthDay(position));
            holder.zanList.setText(getZanList(entity.getTopicList().get(position).getPraiseVo().size(), position));
            holder.commentList.setText(getCommentList(entity.getTopicList().get(position).getCommentVo().size(), position));
            isZanYet(holder, position);
            isVisible(holder, position);
            holder.iv_clickZan.setOnClickListener(new myClickListener(user_id, position, holder));
            holder.iv_comment.setOnClickListener(new myCommentListener(user_id,position,holder));
            holder.delete.setOnClickListener(new myDelete(entity.getTopicList().get(position).getTopic().getTopic_id()));
            if(user_id==entity.getTopicList().get(position).getAuthor().getUser_id()) {
                holder.delete.setVisibility(View.VISIBLE);
            }else{
                holder.delete.setVisibility(View.GONE);
            }
        }
    }

    private void isZanYet(TopicHolder holder, int position) {//本人是否已经点赞
        if (entity.getTopicList().get(position).isPraise())
            holder.iv_clickZan.setImageResource(R.mipmap.zan1);
        else if (!entity.getTopicList().get(position).isPraise())
            holder.iv_clickZan.setImageResource(R.mipmap.zan0);
    }

    private void isVisible(TopicHolder holder, int position) {//判断是否隐藏点赞表、评论表
        if (entity.getTopicList().get(position).getPraiseVo().size() == 0) {
            holder.topic_ll_clickzan.setVisibility(View.GONE);
        }else if(entity.getTopicList().get(position).getPraiseVo().size() != 0){
            holder.topic_ll_clickzan.setVisibility(View.VISIBLE);
        }
        if (entity.getTopicList().get(position).getCommentVo().size() == 0) {
            holder.commentList.setVisibility(View.GONE);
        }else if(entity.getTopicList().get(position).getCommentVo().size() != 0){
            holder.commentList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (entity != null)
            return entity.getTopicList().size();
        else
            return 0;
    }

    public String getYearMonthDay(int position) { //获取年月日
        int year = entity.getTopicList().get(position).getTopic().getTopic_datetime().getYear() + 1900;
        int month = entity.getTopicList().get(position).getTopic().getTopic_datetime().getMonth() + 1;
        int day = entity.getTopicList().get(position).getTopic().getTopic_datetime().getDay();
        return year + "年" + month + "月" + day + "日";
    }


    public String getZanList(int size, int position) {//点赞列表
        SparseArray sparse = new SparseArray();
        StringBuffer zanlist = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                sparse.put(i, entity.getTopicList().get(position).getPraiseVo().get(i).getUser_nickname());
                zanlist.append(entity.getTopicList().get(position).getPraiseVo().get(i).getUser_nickname() + "、");
            } else {
                zanlist.append(entity.getTopicList().get(position).getPraiseVo().get(i).getUser_nickname());
            }
        }
        return zanlist.toString();
    }

    public String getCommentList(int size, int position) {//评论列表
        String commentList = "";
        for (int i = 0; i < size; i++) {//循环话题数量列表
            int inSize = entity.getTopicList().get(position).getCommentVo().get(i).size();
            List<TopicEntity.TopicListBean.CommentVoBean> commentVoBeen = entity.getTopicList().get(position).getCommentVo().get(i);
            for (int j = 0; j < inSize; j++) {//循环话题评论列表
                if (i==size-1 && j==inSize-1)
                    commentList += commentVoBeen.get(j).getUser().getUser_nickname() + "：" + commentVoBeen.get(j).getComment_content();
                else
                    commentList += commentVoBeen.get(j).getUser().getUser_nickname() + "：" + commentVoBeen.get(j).getComment_content() + "\n";
            }
        }
        return commentList;
    }

    class myDelete implements View.OnClickListener{
        int top_id;
        public myDelete(int top_id) {
            this.top_id = top_id;
        }

        @Override
        public void onClick(View v) {
            new AlertDialog.Builder(parentView).setMessage("确定删除吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.doDelete(user_id,top_id);
                }
            }).setNegativeButton("取消",null).show();

        }
    }

    class myClickListener implements View.OnClickListener {//点赞点击监听器
        int position;
        int user_id;
        TopicHolder holder;
        private static final int CANCLEZAN = 0;
        private static final int CLICKZAN = 1;

        public myClickListener(int user_id, int position, TopicHolder holder) {
            this.position = position;
            this.user_id = user_id;
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            if (entity.getTopicList().get(position).isPraise()) {//取消赞
                entity.getTopicList().get(position).setPraise(false);
                holder.iv_clickZan.setImageResource(R.mipmap.zan0);
                presenter.doClickZan(user_id, entity.getTopicList().get(position).getTopic().getTopic_id(), CANCLEZAN);
                ZanList list = getZanList();
                String mylist = "";
                if (list != null) {
                    for (int i = 0; i < list.getTopicList().size(); i++) {
                        mylist += list.getTopicList().get(i).getUser_nickname();
                    }
                }
                holder.zanList.setText(mylist);
            } else if (!entity.getTopicList().get(position).isPraise()) {//取消赞
                entity.getTopicList().get(position).setPraise(true);
                holder.iv_clickZan.setImageResource(R.mipmap.zan2);
                presenter.doClickZan(user_id, entity.getTopicList().get(position).getTopic().getTopic_id(), CLICKZAN);
                ZanList list = getZanList();
                String mylist = "";
                if (list != null) {
                    for (int i = 0; i < list.getTopicList().size(); i++) {
                        mylist += list.getTopicList().get(i).getUser_nickname();
                    }
                }
                holder.zanList.setText(mylist);
    }
        }
        public ZanList getZanList() {
            return zanList;
        }

    }

    private class myCommentListener implements View.OnClickListener {//评论监听器
        int position;
        int user_id;
        TopicHolder holder;
        private PopupWindow popWindow = null;
        View view;
        EditText et_content;
        public myCommentListener( int user_id,int position, TopicHolder holder) {
            this.position = position;
            this.user_id = user_id;
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            showPopup(holder.iv_comment);
            popupInputMethodWindow();
        }

        private void showPopup(View parent){
            if(popWindow==null){
                LayoutInflater layoutInflater = (LayoutInflater) parentView.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.topic_popup_comment,null);
                popWindow  = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,150,true);
            }
            popWindow.setFocusable(true);
            popWindow.setOutsideTouchable(false);//在外点击消失
            popWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景，点击返回也能使其消失，并且不影响背景
            popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//软键盘不会遮挡住popuowindow
            popWindow.showAtLocation(parent, Gravity.BOTTOM,0,0);
            popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {//设置监听菜单关闭事件
                @Override
                public void onDismiss() {

                }
            });
            popWindow.setTouchInterceptor(new View.OnTouchListener() {//设置触屏事件
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            et_content = (EditText) view.findViewById(R.id.topic_comment_et_content);
            view.findViewById(R.id.topic_comment_bt_send).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mess = et_content.getText().toString().trim();
                    if(mess.equals("")){

                    }
                    else{
                        presenter.doComment(user_id,entity.getTopicList().get(position).getTopic().getTopic_id(),mess);//传入用户ID，话题id，发送的消息
//                    Log.d("aaaa", "onClick: "+mess);
                        popWindow.dismiss();
                    }
                }
            });
        }

        private void popupInputMethodWindow() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) holder.iv_comment.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }, 0);
        }
        Handler handler = new Handler();
    }
}

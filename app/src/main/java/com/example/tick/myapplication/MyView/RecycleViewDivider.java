package com.example.tick.myapplication.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Tick on 2016/9/10.
 */
public class RecycleViewDivider extends RecyclerView.ItemDecoration {
    private Drawable mDrawable;
    //画笔
    private Paint paint;
    //布局方向
    private int mOrientation;
    //分割线尺寸
    private int size;
    //分隔线颜色
    private  int color;

    public RecycleViewDivider(int orientation) {
        this.paint = new Paint();
        mOrientation = orientation;
    }

    public RecycleViewDivider(int mOrientation, int size, int color) {
        this.mOrientation = mOrientation;
        this.size = size;
        this.color = color;
        paint = new Paint();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(mOrientation==LinearLayoutManager.HORIZONTAL){
            drawHorizontal(c,parent);
        }
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final  int left = parent.getPaddingLeft()+20;//获取左边的距离
        final  int right = parent.getWidth() - parent.getPaddingRight()-20;//获取右边距离
        final int childCount = parent.getChildCount();//?
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);//??
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final  int top = child.getBottom() + params.bottomMargin;//画分割线的顶部位置
            final int bottom = top + size;//分割线底部位置
            c.drawRect(left,top,right,bottom,paint);//画方形
        }
    }
}

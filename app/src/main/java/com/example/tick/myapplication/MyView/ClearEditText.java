package com.example.tick.myapplication.MyView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.example.tick.myapplication.R;

import org.w3c.dom.Text;

/**
 * Created by Tick on 2016/9/13.
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
    //删除按钮的应用
    private Drawable mClearDrawable;
    //是否有焦点
    private boolean hasFouce;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //获取edittext的drawableRight，如果没有就设置我们使用的默认图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.clear);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置右边隐藏
        setClearIconVisible(false);
        //设置焦点改变监听
        setOnFocusChangeListener(this);
        //设置输入框内容改变的监听
        addTextChangedListener(this);
    }
    /**
     * 调用setCompoundDrables为绘制右图隐藏还是显示
     *
     * @param visible
     */
    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);

    }

    /**
     * 由于没有监听右边控件的监听器，所以取手指抬起时候的位置作为监听右边控件的依据
     * edittext宽度-图片右边-图片大小 到 edittext宽度-图片左边
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            if(getCompoundDrawables()[2]!=null){
                boolean touchable = event.getX()>(getWidth()-getTotalPaddingRight()) && event.getX()<(getWidth()-getPaddingRight());
                if(touchable)
                    this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当输入框中内容发生改变，判断长度，并设置清楚图标的显示或者隐藏
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFouce = hasFocus;
        if(this.hasFouce){
            setClearIconVisible(getText().length()>0);
        }else{
            setClearIconVisible(false);
        }
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (hasFouce){
                setClearIconVisible(getText().length()>0);
            }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    public void addShakeAnimation(){
        this.startAnimation(shakeAnimation(5));
    }
    /**
     * 设置晃动动画，count一秒晃动的次数
     * @param count
     * @return
     */
    public static Animation shakeAnimation(int count){
        Animation animation = new TranslateAnimation(0,10,0,0);
        animation.setInterpolator(new CycleInterpolator(count));
        animation.setDuration(1000);
        return  animation;
    }
}

package com.example.tick.myapplication.Topic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tick.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Tick on 2016/8/29.
 */
public class TopicActivity extends Fragment implements ViewPager.OnPageChangeListener{
    private View view;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private int srceen=0;
    private ImageView tabline;
    private int CurrentPosition = 0;//记录当前的滑动位置
    //用butterknife框架绑定控件
    @BindView(R.id.topic_tv_topic)
    TextView tv_topic;
    @BindView(R.id.topic_tv_interation)
    TextView tv_interation;
    @BindView(R.id.topic_tv_secondhand)
    TextView tv_secondhand;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_topic, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        initTabLine();
        fragments = new ArrayList<>();
        TopicFragment topicFragment = new TopicFragment();
        InteractionFragment interactionFragment = new InteractionFragment();
        SecondhandFragment secondhandFragment = new SecondhandFragment();
        //加入集合中
        fragments.add(topicFragment);
        fragments.add(interactionFragment);
        fragments.add(secondhandFragment);
        viewPager = (ViewPager) view.findViewById(R.id.topic_viewpage);
        //配置适配器,此处用getFragmentManager(),如果是在activity内，用getSupportFragmentManager()
        fragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        //设置适配器
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void initTabLine() {
        tabline= (ImageView) view.findViewById(R.id.topic_iv_tabline);
        //获取关联的活动再获取显示对象
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics disMe = new DisplayMetrics();
        display.getMetrics(disMe);
        //获取屏幕的1/3长度
        srceen = disMe.widthPixels/3;
        //获取tabline参数长度
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        //设置tabline长度为屏幕的1/3
        lp.width = srceen;
        tabline.setLayoutParams(lp);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //获取tabline布局参数
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tabline.getLayoutParams();
        if(CurrentPosition==0&&position==0){//从第一个页面滑向第二个,第一个参数当前点击滑动屏幕页面（0开始）；第二个参数，滑动的百分比；第三个参数，滑动的像素
            lp.leftMargin = (int) (CurrentPosition*srceen +positionOffset*srceen);
        }else if(CurrentPosition==1&&position==0){//1->0
            lp.leftMargin = (int) (CurrentPosition*srceen + (positionOffset-1)*srceen);
        }else if(CurrentPosition==1&&position==1){//1->2
            lp.leftMargin = (int) (CurrentPosition*srceen + positionOffset*srceen);
        }else if(CurrentPosition==2&&position==1){//2->1
            lp.leftMargin = (int) (CurrentPosition*srceen + (positionOffset-1)*srceen);
        }
       tabline.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
        resetTextColor();
        switch (position){
            case 0:
                tv_topic.setTextColor(getResources().getColor(R.color.colorGreen));
                break;
            case 1:
                tv_interation.setTextColor(getResources().getColor(R.color.colorGreen));
                break;
            case 2:
                tv_secondhand.setTextColor(getResources().getColor(R.color.colorGreen));
                break;
        }
        //改变当前页面值
        CurrentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //重置字体的颜色
    public void resetTextColor(){
        tv_interation.setTextColor(getResources().getColor(R.color.colorText));
        tv_topic.setTextColor(getResources().getColor(R.color.colorText));
        tv_secondhand.setTextColor(getResources().getColor(R.color.colorText));
    }
}

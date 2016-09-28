package com.example.tick.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tick.myapplication.Home.HomeActivity;
import com.example.tick.myapplication.Mine.MineActivity;
import com.example.tick.myapplication.Propery.View.Imple.PropreyActivity;
import com.example.tick.myapplication.Topic.TopicActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private List<Fragment> fragments;
    private HomeActivity homeActivity;
    private MineActivity mineActivity;
    private PropreyActivity propreyActivity;
    private TopicActivity topicActivity;
    private FragmentPagerAdapter pagerAdapter;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOSE_PICTURE = 2;
    private static final int CROP_PHOTO = 3;
    //    private int user_id;
    public Uri tempUri;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    //导航栏布局
    @BindView(R.id.home)
    LinearLayout home;
    @BindView(R.id.propery)
    LinearLayout proprey;
    @BindView(R.id.mine)
    LinearLayout mine;
    @BindView(R.id.topic)
    LinearLayout topic;
    //导航栏文字
    @BindView(R.id.main_tv_home)
    TextView tv_home;
    @BindView(R.id.main_tv_proprey)
    TextView tv_proprey;
    @BindView(R.id.main_tv_mine)
    TextView tv_mine;
    @BindView(R.id.main_tv_topic)
    TextView tv_topic;
    //导航栏图片
    @BindView(R.id.main_iv_home)
    ImageView iv_home;
    @BindView(R.id.main_iv_proprey)
    ImageView iv_proprey;
    @BindView(R.id.main_iv_mine)
    ImageView iv_mine;
    @BindView(R.id.main_iv_topic)
    ImageView iv_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //初始化
        homeActivity = new HomeActivity();
        mineActivity = new MineActivity();
        propreyActivity = new PropreyActivity();
        topicActivity = new TopicActivity();

        fragments = new ArrayList<>();
        fragments.add(homeActivity);
        fragments.add(propreyActivity);
        fragments.add(topicActivity);
        fragments.add(mineActivity);
        //配置适配器
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @OnClick(R.id.home)
    void OnClickHome() {
        viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.propery)
    void OnClickPropery() {
        viewPager.setCurrentItem(1);
    }

    @OnClick(R.id.topic)
    void OnClickTopic() {
        viewPager.setCurrentItem(2);
    }

    @OnClick(R.id.mine)
    void OnClickMine() {
        viewPager.setCurrentItem(3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        reSetTextColor();
        switch (position) {
            case 0:
                iv_home.setImageResource(R.mipmap.home1);
                tv_home.setTextColor(getResources().getColor(R.color.colorBlue));
                break;
            case 1:
                iv_proprey.setImageResource(R.mipmap.proprey1);
                tv_proprey.setTextColor(getResources().getColor(R.color.colorBlue));
                break;
            case 2:
                iv_topic.setImageResource(R.mipmap.topic1);
                tv_topic.setTextColor(getResources().getColor(R.color.colorBlue));
                break;
            case 3:
                iv_mine.setImageResource(R.mipmap.mine1);
                tv_mine.setTextColor(getResources().getColor(R.color.colorBlue));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //重新设置字体的颜色，图片
    public void reSetTextColor() {
        tv_home.setTextColor(getResources().getColor(R.color.colorText));
        tv_proprey.setTextColor(getResources().getColor(R.color.colorText));
        tv_topic.setTextColor(getResources().getColor(R.color.colorText));
        tv_mine.setTextColor(getResources().getColor(R.color.colorText));
        iv_home.setImageResource(R.mipmap.home0);
        iv_proprey.setImageResource(R.mipmap.propery0);
        iv_topic.setImageResource(R.mipmap.topic0);
        iv_mine.setImageResource(R.mipmap.mine0);
    }
    //设置图片保存的路径

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    startPhotoZoom(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png")));
                    break;
                case CHOSE_PICTURE:
                    if (data != null && resultCode == RESULT_OK) {
                        startPhotoZoom(data.getData());
                    }
                    break;
                case CROP_PHOTO:
                    if (data != null) {
                        setImageView(data);
                    } else {

                    }
                    break;
            }
        } else {
            Toast.makeText(this, "取消操作", Toast.LENGTH_SHORT).show();
        }
    }


    private void setImageView(Intent data) {//将图片设置上去
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
//                Log.d("aaa0", "setImageView: "+uri.toString());
            mineActivity.setUsericon(bitmap);
            mineActivity.uploadUserHead();
        }
    }

    private void startPhotoZoom(Uri uri) {//截图
        if (uri == null) {
            Log.d("bbbbb", "startPhotoZoom: the uri is not exit");
        }
        mineActivity.setImagePaht(getImagePath(uri).toString());//将文件路径传递给mineActivity
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setData(uri);
//        Log.d("aaa1", "startPhotoZoom: "+intent.getData().toString());
        intent.setDataAndType(uri, "image/jpg");
        //设置裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);//设置款高比
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);//设置截图后的宽高
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_PHOTO);
    }

    private String getImagePath(Uri uri) {//获取截图后的图片路径
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}

package com.example.tick.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import com.example.tick.myapplication.Mine.model.UserIcon;
import com.example.tick.myapplication.Propery.PropreyActivity;
import com.example.tick.myapplication.Topic.TopicActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
//    private Uri tempUri;
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
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
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
                tv_home.setTextColor(Color.parseColor("#5e86f2"));
                break;
            case 1:
                iv_proprey.setImageResource(R.mipmap.proprey1);
                tv_proprey.setTextColor(Color.parseColor("#5e86f2"));
                break;
            case 2:
                iv_topic.setImageResource(R.mipmap.topic1);
                tv_topic.setTextColor(Color.parseColor("#5e86f2"));
                break;
            case 3:
                iv_mine.setImageResource(R.mipmap.mine1);
                tv_mine.setTextColor(Color.parseColor("#5e86f2"));
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
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "取消拍照", Toast.LENGTH_SHORT).show();
        }
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                    File file = new File(path,UserIcon.getInstance().getImageName());
                    InputStream is = null;
                    try {
                        is = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    mineActivity.setUsericon(bitmap);
//                    Intent intent = new Intent("com.android.camera.action.CROP");
//                    intent.setDataAndType(UserIcon.getInstance().getImageUri(), "image/*");
//                    intent.putExtra("crop", true);
//                    intent.putExtra("return-data", true);
//                    intent.putExtra("aspectX", 1);
//                    intent.putExtra("aspectY", 1);
//                    intent.putExtra("outputX", 40);
//                    intent.putExtra("outputY", 40);
//                    tempUri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/" + "small.jpg");
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CHOSE_PICTURE:
                if(data!=null && resultCode==RESULT_OK)
                {
                    //获取bitmap
                    Uri selectUri = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectUri,filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    //bitmap转换成file文件
                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                    BufferedOutputStream bos = null;
                    try {
                        //文件路径
                        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        //参数一，哪文件夹下，参数二文件名字
                        File file = new File(path,UserIcon.getInstance().getImageName());
                        try {
                            if (file.exists()) {
                                file.delete();
                            }
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bos = new BufferedOutputStream(new FileOutputStream(file));
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
                        bos.flush();
                        bos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mineActivity.setUsericon(bitmap);
                }
                break;
            case CROP_PHOTO:
                //路径默认
//                if (data.hasExtra("data")) {
//                    //设置图片
//                    mineActivity.setUsericon((Bitmap) data.getParcelableExtra("data"));
//                }
//                try {
//                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(tempUri));
//                    mineActivity.setUsericon(bitmap);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

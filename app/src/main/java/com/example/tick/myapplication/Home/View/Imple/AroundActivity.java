package com.example.tick.myapplication.Home.View.Imple;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.example.tick.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tick on 2016/8/30.
 */
public class AroundActivity extends Activity {
    @BindView(R.id.many_top_title)
    TextView title;
    @BindView(R.id.around_mapview)
    MapView mapView;
    private BaiduMap baiduMap;
    private LocationManager manager;
    private String provider;
    private boolean isFirstLocate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_home_around);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //获取标题并且设置标题
        title.setText(getIntent().getStringExtra("title"));
        //baidu地图
        baiduMap = mapView.getMap();
        initLocation();

    }

    private void initLocation() {
        //获取我的位置
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> provides = manager.getAllProviders();
        if (provides.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (provides.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(AroundActivity.this, "No provider use", Toast.LENGTH_SHORT).show();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = manager.getLastKnownLocation(provider);
        if (location != null) {
            navigateTo(location);
        }
        manager.requestLocationUpdates(provider,5000,1,listener);
    }

    private void navigateTo(Location location) {
        if(isFirstLocate){//不知道干嘛的
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
    }
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            navigateTo(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    @OnClick(R.id.many_top_back)
    void OnBack() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}

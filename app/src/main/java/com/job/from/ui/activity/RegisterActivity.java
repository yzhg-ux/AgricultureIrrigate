package com.job.from.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocationClient;
import com.job.from.base.ui.BasicActivity;
import com.job.from.utlis.common.LocationUtils;

/**
 * Created  on 2018/5/19/019.
 * aphorism:
 * True hero is brave and useless, good scenery is perilous peak.
 * One sentence summary:  注册页面
 */
public class RegisterActivity extends BasicActivity {

    /*定位信息*/
    private AMapLocationClient aMapLocationClient;
    private LocationUtils locationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*初始化定位服务*/
        locationUtils = new LocationUtils();
        initLocation();
    }

    private void initLocation() {
        locationUtils.initLocation();
        locationUtils.setOnLocationListener(aMapLocation -> {
            int errorCode = aMapLocation.getErrorCode();
            if (errorCode == 0) {
                /*定位成功*/

            } else {
                /*获取到错误信息,这里不做任何提示*/
                String errorInfo = locationUtils.showErrorInfo(errorCode);
            }
        });
    }
}

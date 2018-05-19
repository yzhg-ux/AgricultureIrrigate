package com.job.from.utlis.common;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by $(剪刀手--yzhg) on 2018/4/12 0012.
 * 用一句话描述: 高德地图定位服务
 * <p>
 * 官方文档 : http://lbs.amap.com/api/android-sdk/summary/
 */

public class LocationUtils {
    private AMapLocationClient mLocationClient;

    public void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(Utils.getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)
        // 接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)
        // 接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /*停止定位,但是不会销毁服务*/
    public void stopLocation() {
        mLocationClient.stopLocation();

    }

    /*销毁定位服务*/
    public void onDestory() {
        mLocationClient.onDestroy();
    }

    private OnLocationListener onLocation;

    //声明定位回调监听器
    private AMapLocationListener mLocationListener = aMapLocation -> {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                if (onLocation != null) onLocation.location(aMapLocation);
            } else {
                onLocation.location(null);
            }
        }
    };

    public void setOnLocationListener(OnLocationListener onLocation) {
        this.onLocation = onLocation;
    }

    public interface OnLocationListener {
        void location(AMapLocation aMapLocation);
    }

    public String showErrorInfo(int errorCode) {
        if (errorCode == 1 || errorCode == 13) {
            return "没有扫描到基站信息,请重新尝试。";
        } else if (errorCode == 4) {
            return "请检查设备网络是否通畅";
        } else if (errorCode == 5) {
            return "您可以稍后再试，或检查网络链路是否存在异常。";
        } else if (errorCode == 11) {
            return "请检查是否安装SIM卡";
        } else if (errorCode == 12) {
            return "请在设备的设置中开启app的定位权限。";
        } else if (errorCode == 14) {
            return "建议持设备到相对开阔的露天场所再次尝试。";
        } else if (errorCode == 18) {
            return "建议手机关闭飞行模式，并打开WIFI开关";
        } else if (errorCode == 19) {
            return "建议手机插上sim卡，打开WIFI开关";
        } else {
            return "定位失败";
        }
    }

}

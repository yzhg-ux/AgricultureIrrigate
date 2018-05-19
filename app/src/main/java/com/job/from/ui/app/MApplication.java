package com.job.from.ui.app;

import android.app.Application;

import com.job.from.utlis.common.Utils;

/**
 * Created by $(剪刀手--yzhg) on 2018/5/19 0019.
 * 用一句话描述该类的用处:
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
    }
}

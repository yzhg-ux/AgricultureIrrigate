package com.job.from.utlis.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 剪刀手 , 时间 2017/6/22.
 * <p>
 * 警句:真英雄大勇无谓,好风景总在险峰
 *
 * 通过集合保存Activity,随时随地退出集合
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities) {
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}

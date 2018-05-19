package com.job.from.base.ui;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.job.from.R;
import com.job.from.utlis.common.ActivityCollector;
import com.job.from.utlis.common.LogUtils;
import com.job.from.utlis.dialog.CustomDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;


/**
 * description (Activity的父类)
 * Created by yzhg on 2017/9/21.
 */

public abstract class BasicActivity extends RxAppCompatActivity {

    private View mContextView;
    private CustomDialog customDialog;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * Activity生命周期方法
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, TAG + "--的onCreate方法");
        /*初始化加载框*/
        customDialog = new CustomDialog.Builder(this)
                .setView(R.layout.load_dialog)
                .setStyle(R.style.MyDialogTheme)
                .setGravity(Gravity.CENTER)
                .build();
        /*将Activity存放的集合中,方便退出*/
        ActivityCollector.addActivity(this);
    }

    /**
     * 页面跳转
     */
    public void startActivity(Class<?> clazz, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (isFinish) {
            this.finish();
        }
    }

    /**
     * 携带Bundle数据跳转页面
     */
    public void startActivity(Class<?> clazz, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isFinish) {
            this.finish();
        }
    }

    /**
     * 携带参数 跳转页面，并返回数据
     */
    public void startActivityForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            return !topActivity.getPackageName().equals(context.getPackageName());
        }
        return false;
    }

    /**
     * 判断一个Activity是否正在运行
     *
     * @param pkg     pkg为应用包名
     * @param cls     cls为类名eg
     * @param context
     * @return
     */
    public static boolean isClsRunning(Context context, String pkg, String cls) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        ActivityManager.RunningTaskInfo task = tasks.get(0);
        if (task != null) {
            return TextUtils.equals(task.topActivity.getPackageName(), pkg) &&
                    TextUtils.equals(task.topActivity.getClassName(), cls);
        }
        return false;
    }

    /*开启弹窗*/
    public void showDialog() {
        if (customDialog != null && !customDialog.isShowing()) customDialog.show();
    }

    /*关闭弹窗*/
    public void closeDialog() {
        if (customDialog != null && customDialog.isShowing()) customDialog.hide();
    }

    /**
     * 打印日志
     */
    public void showD(String msg) {
        LogUtils.d(TAG, msg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG, TAG + "--的onStart方法");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d(TAG, TAG + "--的onNewIntent方法");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG, TAG + "--的onResume方法");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG, TAG + "--的onPause方法");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(TAG, TAG + "--的onStop方法");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, TAG + "--的onDestroy方法");
        if (customDialog != null) customDialog.dismiss();
    }
}





package com.job.from.base.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.job.from.utlis.common.LogUtils;
import com.job.from.utlis.toast.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;


/**
 * description (Fragment的父类)
 * Created by yzhg on 2017/9/21.
 */

public abstract class BasicFragment extends RxFragment {

    protected Context context;
    protected Activity activity;
    private String TAG = this.getClass().getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle bundle) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
        LogUtils.d(TAG, TAG + "--onCreateView方法");
        context = getActivity();
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.d(TAG, TAG + "--onViewCreated方法");
        /**页面加载完毕,调用处理逻辑的方法*/
        initDate();
    }

    /**
     * 页面跳转
     */
    public void startActivity(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }


    /**
     * 显示Toast
     */
    public void showToast(int msg) {
        ToastUtils.showToast(msg);
    }

    /**
     * 打印Log
     */
    public void showLog(String msg) {
        LogUtils.d(TAG, msg);
    }


    /**
     * 初始化数据
     */
    protected abstract void initDate();


    /**
     * onAttach Fragment生命周期 用于获取上下文
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.d(TAG, TAG + "--onAttach方法");
    //    this.context = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, TAG + "--onCreate方法");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(TAG, TAG + "--onActivityCreated方法");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d(TAG, TAG + "--onStart方法");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, TAG + "--onResume方法");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, TAG + "--onPause方法");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(TAG, TAG + "--onStop方法");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, TAG + "--onDestroyView方法");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, TAG + "--onDestroy方法");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d(TAG, TAG + "--onDetach方法");
    }

}
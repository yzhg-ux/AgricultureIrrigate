package com.job.from.http.base;


import android.app.Activity;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.job.from.R;
import com.job.from.constant.CConstant;
import com.job.from.utlis.common.LogUtils;
import com.job.from.utlis.common.Utils;
import com.job.from.utlis.dialog.CommonDialogUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <p>
 * describe:返回数据的统一处理
 */

public abstract class BasicObserver<T> implements Observer<T> {

    private Activity activity;
    private CommonDialogUtils commonDialogUtils;

    /**
     * 创建构造,传递上下文
     *
     * @param activity
     */
    protected BasicObserver(Activity activity, int isLoad) {
        this.activity = activity;
        commonDialogUtils = new CommonDialogUtils();
        if (isLoad == CConstant.CONSTANT_REFRESH_NORMAL)
            commonDialogUtils.showProgress(activity);
    }

    /**
     * 创建两个参数的构造,传递上下文,传递一个String参数
     *
     * @param activity
     */
    public BasicObserver(Activity activity, String msg) {
        this.activity = activity;
        commonDialogUtils = new CommonDialogUtils();
        commonDialogUtils.showProgress(activity, msg);
    }

    /**
     * 创建三个参数的构造,是否显示弹框,自定义内容
     *
     * @param activity
     * @param isShowLoading
     */
    public BasicObserver(Activity activity, boolean isShowLoading, String msg) {
        this.activity = activity;
        commonDialogUtils = new CommonDialogUtils();
        if (isShowLoading) {
            commonDialogUtils.showProgress(activity, msg);
        }
    }

    public void dismissProgress() {
        if (commonDialogUtils != null) {
            commonDialogUtils.dismissProgress();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    /**
     * Rxjava 获取数据成功是的数据处理
     *
     * @param response
     */
    @Override
    public void onNext(T response) {
        dismissProgress();
        onSuccess(response);
    }

    protected abstract void onDefeated(String string, int errorIndex);

    protected abstract void onSuccess(T response);

    /**
     * RxJava 网络出现问题时的处理方案
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        dismissProgress();
        LogUtils.d("------  Retrofit  ------", e.getMessage());
        if (e instanceof HttpException) {   //HTTP  错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {  //连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {  //连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {  //解析异常
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);  //未知错误
        }
    }

    @Override
    public void onComplete() {

    }

    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                onDefeated(Utils.getString(R.string.net_connect_error), 1111);
                break;
            case CONNECT_TIMEOUT:
                onDefeated(Utils.getString(R.string.net_connect_timeOut), 1112);
                break;
            case BAD_NETWORK:
                onDefeated(Utils.getString(R.string.net_bad_netWork), 1113);
                break;
            case PARSE_ERROR:
                onDefeated(Utils.getString(R.string.net_parse_error), 1114);
                break;
            case UNKNOWN_ERROR:
            default:
                onDefeated(Utils.getString(R.string.net_unKnown_error), 1000);
                break;
        }
    }


    /**
     * 采用枚举
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}














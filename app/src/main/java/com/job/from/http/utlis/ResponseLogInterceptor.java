package com.job.from.http.utlis;


import com.job.from.utlis.common.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author ${水木科技 - yzhg} on 2018/3/15.
 * <p>
 * describe:
 *   日志拦截器，用于获取网络日志信息
 *
 */

public class ResponseLogInterceptor implements Interceptor {

    /**
     * 获取网络数据是，找到此信息即可获知，
     */
    private static final String TAG = "------  OkHttp  ------";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        LogUtils.d(TAG, "url     =  : " + request.url());
        LogUtils.d(TAG, "method  =  : " + request.method());
        LogUtils.d(TAG, "headers =  : " + request.headers());
        LogUtils.d(TAG, "body    =  : " + request.body());
        LogUtils.d(TAG, "code     =  : " + response.code());
        LogUtils.d(TAG, "message  =  : " + response.message());
        LogUtils.d(TAG, "protocol =  : " + response.protocol());
        if (response.body() != null && response.body().contentType() != null) {
            MediaType mediaType = response.body().contentType();
            String string = response.body().string();
            LogUtils.d(TAG, "mediaType =  :  " + mediaType.toString());
            LogUtils.d(TAG, "string    =  : " + string);
            ResponseBody responseBody = ResponseBody.create(mediaType, string);
            return response.newBuilder().body(responseBody).build();
        } else {
            return response;
        }
    }
}

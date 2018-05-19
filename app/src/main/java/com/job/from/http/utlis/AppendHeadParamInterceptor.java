package com.job.from.http.utlis;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author ${水木科技 - yzhg} on 2018/3/15.
 * <p>
 * describe:
 *      用于添加统一的Token和UserId
 */

public class AppendHeadParamInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Headers.Builder builder = request.headers().newBuilder();
        Headers newHeader = builder
                .add("token", "")
                .add("usersId", "")
                .build();
        Request build = request.newBuilder()
                .headers(newHeader)
                .build();
        return chain.proceed(build);
    }

}

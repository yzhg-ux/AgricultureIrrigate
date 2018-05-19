package com.job.from.http.manager;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.job.from.http.api.Api;
import com.job.from.http.utlis.AppendHeadParamInterceptor;
import com.job.from.http.utlis.HttpCacheInterceptor;
import com.job.from.http.utlis.ResponseLogInterceptor;
import com.job.from.utlis.common.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author ${水木科技 - yzhg} on 2018/3/15.
 * <p>
 * describe:
 *      网络访问的入口
 */

public class NetWorkManager {


    private static Api sTackOutBaseApi;

    /**
     * 使用单利设计模式，生成API
     * @return
     */
    public synchronized static Api createService(){
        if(sTackOutBaseApi == null){
            Retrofit retrofit = createRetrofit();
            sTackOutBaseApi = retrofit.create(Api.class);
        }
        return sTackOutBaseApi;
    }

    /**
     * 创建RetroFit实例对象
     * @return
     */
    private static Retrofit createRetrofit() {
        OkHttpClient okHttpClient = getOkHttpClient();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.URL_HOME)
                .build();
    }

    @NonNull
    public static OkHttpClient getOkHttpClient() {
        /**
         * 设置网络缓存,大小100M
         */
        File cacheFile = new File(Utils.getContext().getCacheDir(),"SMCache");
        Cache cache = new Cache(cacheFile,1024*1024*100);

        return new OkHttpClient.Builder()
                /*设置链接超时的时间*/
                .readTimeout(Api.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Api.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new ResponseLogInterceptor())
                .addInterceptor(new AppendHeadParamInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();
    }
}











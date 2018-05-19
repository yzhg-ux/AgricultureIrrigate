package com.job.from.utlis.update;

import android.os.Environment;


import com.job.from.utlis.common.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.annotations.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by $(剪刀手--yzhg) on 2018/4/2 0002.
 * 用一句话描述()
 */

public class DownloadUtil {
    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;

    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    public void download(final String url, final String saveDir, final OnDownloadListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept-Encoding", "identity")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed(e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                try {
                    long total = response.body().contentLength();
                    LogUtils.d( "-------------------------------总进度" + total);
                    is = response.body().byteStream();
                    File file = new File(savePath, getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        sum += len;
                        fos.write(buf, 0, len);
                        int progress = (int) (sum * 1.0f / total * 100);
                        LogUtils.d("-=-----当前进度" + sum + "总进度" + total);
                        // 下载中
                        listener.onDownloading(progress,total);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess(file.getAbsolutePath());
                } catch (Exception e) {
                    listener.onDownloadFailed(e.getMessage().toString());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         *
         * @param absolutePath
         */
        void onDownloadSuccess(String absolutePath);

        /**
         * @param progress 下载进度
         * @param total
         */
        void onDownloading(int progress, long total);

        /**
         * 下载失败
         * @param s
         */
        void onDownloadFailed(String s);
    }
}

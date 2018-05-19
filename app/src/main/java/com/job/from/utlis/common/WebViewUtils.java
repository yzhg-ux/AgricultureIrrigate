package com.job.from.utlis.common;

import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者 涛雷科技 , 时间 2017/12/22.
 * <p>
 * 警句:真英雄大勇无谓,好风景总在险峰
 * 概括(一句话总结该类用法)
 */

public class WebViewUtils {

    private static WebViewUtils instance = null;

    private WebViewUtils() {
    }

    public static WebViewUtils getInstance() {
        synchronized (WebViewUtils.class) {
            if (instance == null) {
                instance = new WebViewUtils();
            }
        }
        return instance;
    }

    private WebSettings settings;

    public WebViewUtils initWebView(WebView webview, String url) {
        if (settings == null)
            settings = webview.getSettings();
        initWebSetting(settings);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
        webview.getSettings().setBlockNetworkImage(false);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }

        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //获取Url中
                if(urlTitle !=null){
                    urlTitle.urlTitle(view,title);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(webview.getSettings()
                    .MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
        }
        webview.loadUrl(url);
        return instance;
    }

    private void initWebSetting(WebSettings settings) {
        settings.setUseWideViewPort(true);//设置自适应手机
        settings.setLoadWithOverviewMode(true);  //设置自适应手机
        settings.setJavaScriptEnabled(true); //设置支持js
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setSupportZoom(true);  //支持缩放
        settings.setBuiltInZoomControls(true);//设置内置的缩放器,若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放器
        settings.setCacheMode(WebSettings.LOAD_DEFAULT); //关闭webView中缓存
        settings.setAllowFileAccess(true);//设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);   //支持通过js打开新窗口
        settings.setLoadsImagesAutomatically(true);  //设置自动加载图片
        settings.setDefaultTextEncodingName("UTF-8");  //设置编码格式
    }


    private onReceivedTitle urlTitle;

    /**
     * 实现接口回调
     */
    public void setOnReceivedTitle(onReceivedTitle urlTitle) {
        this.urlTitle = urlTitle;
    }


    //创建接口回调
    public interface onReceivedTitle {
        void urlTitle(WebView view, String title);
    }

}

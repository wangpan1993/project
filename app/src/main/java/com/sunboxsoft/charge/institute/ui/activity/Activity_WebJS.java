package com.sunboxsoft.charge.institute.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gzsll.jsbridge.WVJBWebView;
import com.sunboxsoft.charge.institute.R;
import com.sunboxsoft.charge.institute.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.KeyEvent.KEYCODE_BACK;


public class Activity_WebJS extends BaseActivity {

    @BindView(R.id.pb_web)
    ProgressBar mPb_web;
    @BindView(R.id.ll_root)
    LinearLayout mLayout;

    private WVJBWebView mWebView;
    private boolean showTitle;
    private String mTitle;
    private TextView tv_titleName;

    @Override
    protected int getLayout() {
        return R.layout.common_web_activity;
    }

    @Override
    protected void initView() {

        mPb_web.setVisibility(View.GONE);//网页加载进度显示与隐藏

        tv_titleName = (TextView) findViewById(R.id.tv_titleName);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WVJBWebView(mContext);
        mWebView.setLayoutParams(params);

        mWebView.setWebChromeClient(initWebChromeCliet());
//        mWebView.setWebViewClient(initWebViewCliet());不能要，加上就出不来页面了。原因待调查
//
        initWebSetting();//webView配置
        registHandler();//注册JS交互

        mLayout.addView(mWebView);
    }

    private void registHandler() {
        mWebView.registerHandler("submitFromWeb", new WVJBWebView.WVJBHandler() {
            @Override
            public void request(Object data, WVJBWebView.WVJBResponseCallback callback) {
                Log.e("TAG", "指定Handler接收来自web的数据：" + data);
//                function.onCallBack("getUserInfos");
            }
        });
    }

    private WebViewClient initWebViewCliet() {
        return new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                //handler.cancel();// 默认的处理方式，WebView变成空白页
                handler.proceed();//忽略证书的错误继续Load页面内容，不会显示空白页面
                // handler.handleMessage(null);    //可做其他处理
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
                //加载出问题怎么办，比如404
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //页面开始加载，
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        };
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_titleRight:
                mWebView.callHandler("getUserInfos", "回传数据", new WVJBWebView.WVJBResponseCallback() {
                    @Override
                    public void callback(Object data) {
                        Log.d("TAG", "按钮点击" + data + "");
                    }
                });
                break;
        }
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        showTitle = intent.getBooleanExtra("showTitle", true);
        mTitle = intent.getStringExtra("title");
        if (!showTitle) {
            findViewById(R.id.base_title).setVisibility(View.GONE);
        } else {
            tv_titleName.setText(mTitle);
        }

        String path = "http://139.129.195.140:8085/test";
        webViewLoadUrl(path);
    }

    public void webViewLoadUrl(String path) {
        mWebView.loadUrl(path);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }


    private WebChromeClient initWebChromeCliet() {
        return new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //网页加载进度
                mPb_web.setProgress(newProgress);
                if (newProgress == 100) {
                    mPb_web.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (showTitle) {
                    if (TextUtils.isEmpty(mTitle)) {
                        tv_titleName.setText(title);
                    }
                }
            }
        };
    }

    /**
     * 暂时不用
     */
    private void initWebSetting() {
        WebSettings webSettings = mWebView.getSettings();


        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //支持插件
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
    }
}

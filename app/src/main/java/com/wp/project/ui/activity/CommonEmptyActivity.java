package com.wp.project.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.base.BaseActivity;
import com.wp.project.ui.view.MyWebView;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by WangPan on 2018/1/18.
 */

public class CommonEmptyActivity extends BaseActivity {

    private MyWebView mCommonWeb;
    private ProgressBar mPb_web;
    private boolean showTitle;
    private String mTitle;
    private TextView tv_titleName;

    @Override
    protected int getLayout() {
        return R.layout.common_web_activity;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {

        LinearLayout mLayout = (LinearLayout) findViewById(R.id.ll_root);
        mPb_web = (ProgressBar) findViewById(R.id.pb_web);
        tv_titleName = (TextView) findViewById(R.id.tv_titleName);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mCommonWeb = new MyWebView(getApplicationContext());
        mCommonWeb.setLayoutParams(params);

        mCommonWeb.setWebChromeClient(initWebChromeCliet());
        mLayout.addView(mCommonWeb);

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

        String path = intent.getStringExtra("path");
        mCommonWeb.loadUrl(path);
    }


    @Override
    public void onFail(String message) {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mCommonWeb.canGoBack()) {
            mCommonWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mCommonWeb != null) {
            mCommonWeb.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mCommonWeb.clearHistory();

            ((ViewGroup) mCommonWeb.getParent()).removeView(mCommonWeb);
            mCommonWeb.destroy();
            mCommonWeb = null;
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
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if(showTitle){
                    if(TextUtils.isEmpty(mTitle)){
                        tv_titleName.setText(title);
                    }
                }
            }
        };
    }


}

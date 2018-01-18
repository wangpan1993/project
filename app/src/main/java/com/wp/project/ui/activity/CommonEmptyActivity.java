package com.wp.project.ui.activity;

import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wp.project.R;
import com.wp.project.base.BaseActivity;
import com.wp.project.ui.view.MyWebView;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by WangPan on 2018/1/18.
 */

public class CommonEmptyActivity extends BaseActivity {

    private MyWebView mCommonWeb;
    private LinearLayout mLayout;

    @Override
    protected int getLayout() {
        return R.layout.common_web_activity;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {
        
        mLayout = (LinearLayout) findViewById(R.id.ll_root);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mCommonWeb = new MyWebView(getApplicationContext());
        mCommonWeb.setLayoutParams(params);
        mLayout.addView(mCommonWeb);

    }

    @Override
    protected void initDatas() {

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
}

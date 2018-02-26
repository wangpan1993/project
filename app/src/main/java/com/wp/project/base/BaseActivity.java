package com.wp.project.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.application.AppManager;

import java.util.ArrayList;

/**
 * Created by 王攀 on 2017/2/22.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView, View.OnClickListener {
    //title相关
    protected View layout_title;
    protected RelativeLayout rl_titleLeft;
    protected RelativeLayout rl_titleRight;
    protected TextView tv_titleName;
    protected TextView tv_titleLeft;
    protected TextView tv_titleRight;
    protected ImageView iv_titleLeft;
    protected ImageView iv_titleRight;

    protected Activity mContext;//上下文
    protected T mPresenter;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //acticity跳转动画——右进左出
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);//展示页面
        AppManager.getAppManager().addActivity(this);
        init();//初始化共有内容
        initView();//初始化当前Activity控件
        initDatas();//初始化数据
        if (findViewById(R.id.ll_errer).getVisibility() != View.VISIBLE) {
            findViewById(R.id.fl_content).setVisibility(View.VISIBLE);
        }
        Log.d("base","onCreate");
    }

    private void init() {
        View view = View.inflate(this, getLayout(), null);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_content);
        rl_titleLeft = findById(R.id.rl_titleLeft, true);
        rl_titleRight = findById(R.id.rl_titleRight, true);
        tv_titleName = findById(R.id.tv_titleName);
        tv_titleLeft = findById(R.id.tv_titleLeft);
        tv_titleRight = findById(R.id.tv_titleRight);
        iv_titleLeft = findById(R.id.iv_titleLeft);
        iv_titleRight = findById(R.id.iv_titleRight);
        layout_title = findById(R.id.base_title);

        frameLayout.addView(view);

        mContext = this;
        createPresenter();//建立presenter
        mPresenter.attachView(this);
    }


    @SuppressWarnings("unchecked")
    protected <T> T findById(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findById(int id, boolean isEnvent) {
        T view = (T) findViewById(id);
        if (isEnvent)
            view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_titleLeft:
                finish();
                break;
        }
    }

    public void showErrorView() {
        findViewById(R.id.ll_errer).setVisibility(View.VISIBLE);
        findViewById(R.id.fl_content).setVisibility(View.GONE);

    }

    public void showSucceedView() {
        findViewById(R.id.ll_errer).setVisibility(View.GONE);
        findViewById(R.id.fl_content).setVisibility(View.VISIBLE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("base","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("base","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("base","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("base","onStop");
    }

    @Override
    protected void onDestroy() {

//        Presenter解除绑定
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        Log.d("base","onDestroy");
        //删除当前Activity
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }


    protected abstract int getLayout();

    protected abstract void createPresenter();

    protected abstract void initView();

    protected abstract void initDatas();


    private final int SDK_PERMISSION_REQUEST = 999;//权限回调专用

    @TargetApi(23)
    public void getPersimmions() {
        String permissionInfo = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();

            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }
}

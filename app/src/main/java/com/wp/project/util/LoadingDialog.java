package com.wp.project.util;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.wp.project.R;
import com.wp.project.application.MyApplication;

/**
 * Created by WangPan on 2017/12/12.
 */

public class LoadingDialog {
    private static LoadingDialog loadingDialog;
    private Dialog dialog;
    private Context mContext;

    private LoadingDialog(Context context) {
        dialog = new Dialog(context, R.style.loadstyle);
        this.mContext = context;
        View view = View.inflate(MyApplication.getInstance(), R.layout.dialog_loading, null);
        ImageView loadingImage = (ImageView) view.findViewById(R.id.iv_loading);
        ObjectAnimator animator = ObjectAnimator.ofFloat(loadingImage, "rotation", 0f, 360f);
        animator.setDuration(500);
        animator.setRepeatCount(-1);
        animator.start();
        dialog.setContentView(view);
    }

    public static LoadingDialog getInstance(Context context) {
        if (loadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (loadingDialog == null) {
                    loadingDialog = new LoadingDialog(context);
                }
            }
        }
        return loadingDialog;
    }

    public void showProgressDialog() {
        if(!((Activity)mContext).isFinishing())
            dialog.show();
    }

    public void dismissProgressDialog() {
        if(!((Activity)mContext).isFinishing())
            dialog.dismiss();
    }

}

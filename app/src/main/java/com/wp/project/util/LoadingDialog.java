package com.wp.project.util;

import android.animation.ObjectAnimator;
import android.app.Dialog;
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

    private LoadingDialog() {
        dialog = new Dialog(MyApplication.getInstance(), R.style.loadstyle);
        View view = View.inflate(MyApplication.getInstance(), R.layout.dialog_loading, null);
        ImageView loadingImage = (ImageView) view.findViewById(R.id.iv_loading);
        ObjectAnimator animator = ObjectAnimator.ofFloat(loadingImage, "rotation", 0f, 360f);
        animator.setDuration(500);
        animator.setRepeatCount(-1);
        animator.start();
        dialog.setContentView(view);
    }

    public static LoadingDialog getInstance() {
        if (loadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (loadingDialog == null) {
                    loadingDialog = new LoadingDialog();
                }
            }
        }
        return loadingDialog;
    }

    public void showProgressDialog() {
        dialog.show();
    }

    public void dismissProgressDialog() {
        dialog.dismiss();
    }

}

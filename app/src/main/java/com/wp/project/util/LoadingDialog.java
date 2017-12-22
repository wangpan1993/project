package com.wp.project.util;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.wp.project.R;

/**
 * Created by WangPan on 2017/12/12.
 */

public class LoadingDialog {
    private static LoadingDialog loadingDialog;
    private Dialog dialog;

    private LoadingDialog(Context mContext) {
        dialog = new Dialog(mContext, R.style.loadstyle);
        View view = View.inflate(mContext, R.layout.dialog_loading, null);
        ImageView loadingImage = (ImageView) view.findViewById(R.id.iv_loading);
        ObjectAnimator animator = ObjectAnimator.ofFloat(loadingImage, "rotation", 0f, 360f);
        animator.setDuration(500);
        animator.setRepeatCount(-1);
        animator.start();
        dialog.setContentView(view);
    }

    public static LoadingDialog getInstance(Context mContext) {
        if (loadingDialog == null) {
            synchronized (LoadingDialog.class) {
                if (loadingDialog == null) {
                    loadingDialog = new LoadingDialog(mContext);
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

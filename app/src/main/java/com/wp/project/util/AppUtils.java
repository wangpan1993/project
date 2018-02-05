package com.wp.project.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.util.Allinterface.IDialogClickListener;

import java.security.MessageDigest;

/**
 * Created by 王攀 on 2017/2/22.
 * 跟App相关的辅助类
 */
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final static String encodeMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toUpperCase();
            //  return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNetworkAvailable(Context mContext) {
        boolean result = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm != null) {
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null) {
                    result = ni.isAvailable();
                }
            }
        } catch (Exception e) {
        }

        return result;

    }

    public static boolean isAllPermissionGranted(Context mContext,
                                                 String permissionList[]) {
        if (permissionList.length == 0)
            return false;
        for (int i = 0; i < permissionList.length; i++) {
            if (ContextCompat.checkSelfPermission(mContext, permissionList[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    /**
     * 拨打电话
     * @param mContext 猜猜这是啥
     * @param phoneNum 打给谁
     * @param msg 提示语
     * @param requestCode 假如没有权限的话——————你说咋办就咋办
     */
    public static void callPhoneNum(final Activity mContext, final String phoneNum, String msg, int requestCode)
    {
        if(AppUtils.isAllPermissionGranted(mContext, new String[]{Manifest.permission.CALL_PHONE}))
        {
            AlertUtils.showConfirmDialog(mContext, msg,
                    true, new IDialogClickListener() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onConfirm() {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri
                                    .parse("tel:" + phoneNum));
                            mContext.startActivity(intent);
                        }
                        @Override
                        public void onCancel() {
                        }
                    });
        }
        else
        {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.CALL_PHONE}, requestCode);
        }


    }

    //提示对话框
    public static void showConfirmDialog(final Activity context, String message ,boolean cancelVisibleFlag,final IDialogClickListener iDialogClickListener) {
        if(context!=null&&!context.isFinishing())
        {
            final Dialog dialog = new Dialog(context, R.style.CustomAlertDialog);
            Window win = dialog.getWindow();

            win.getDecorView().setPadding(AppUtils.getWithDisplay(context)/10, 0, AppUtils.getWithDisplay(context)/10, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);

            View v = context.getLayoutInflater().inflate(
                    R.layout.layout_message_dialog, null);

            TextView tv_message = (TextView) v.findViewById(R.id.tv_message);
            tv_message.setText(message);

            Button bt_confirm = (Button) v.findViewById(R.id.bt_confirm);
            bt_confirm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if(iDialogClickListener!=null)
                        iDialogClickListener.onConfirm();
                }
            });
            Button bt_cacel = (Button) v.findViewById(R.id.bt_cacel);
            if(cancelVisibleFlag)
                bt_cacel.setVisibility(View.VISIBLE);
            bt_cacel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(iDialogClickListener!=null)
                        iDialogClickListener.onCancel();
                    dialog.dismiss();
                }
            });
            dialog.setContentView(v);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    /**
     * 获取屏幕的宽度
     *
     * @param mContext
     * @return
     */
    public static int getWithDisplay(Context mContext) {

        return mContext.getResources().getDisplayMetrics().widthPixels;
    }
}
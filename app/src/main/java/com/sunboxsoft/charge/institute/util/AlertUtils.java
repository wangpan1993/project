package com.sunboxsoft.charge.institute.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sunboxsoft.charge.institute.R;
import com.sunboxsoft.charge.institute.util.Allinterface.IDialogClickListener;


public class AlertUtils {

    //提示对话框
    public static void showConfirmDialog(final Activity context, String message , boolean cancelVisibleFlag, final IDialogClickListener iDialogClickListener) {
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
}

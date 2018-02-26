//package com.wp.project.network;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.google.gson.JsonSyntaxException;
//import com.wp.project.application.MyApplication;
//import com.wp.project.base.BaseActivity;
//import com.wp.project.util.LoadingDialog;
//
//import java.net.ConnectException;
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//
///**
// * Created by 王攀 on 2017/2/22.
// */
//
//public abstract class HttpObserver<T> implements Observer<T> {
//
//    private boolean showProgress;//是否显示进度条
//    private LoadingDialog loadingDialog;
//    private Context mContext;
//
//    public HttpObserver() {//默认调用此构造方法——显示进度条
//        showProgress = true;
//    }
//
//    protected HttpObserver(Context context, boolean showProgress) {//想显示就显示，不想显示就不显示
//        this.showProgress = showProgress;
//        loadingDialog = LoadingDialog.getInstance(context);
//    }
//
//    protected HttpObserver(Context context) {//想显示就显示，不想显示就不显示
//        showProgress = true;
//        mContext = context;
//        loadingDialog = LoadingDialog.getInstance(context);
//
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
//
//    @Override
//    public void onSubscribe(Disposable d) {
//        if (showProgress) {
//            //TODO:以后添加——此处显示加载进度框
//            loadingDialog.showProgressDialog();
//        }
//    }
//
//    @Override
//    public void onNext(T t) {
//        //TODO:以后添加——此处取消加载进度框
//        loadingDialog.dismissProgressDialog();
//        loadingDialog = null;
//        ((BaseActivity) mContext).showSucceedView();
//        onSuccess(t);
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        loadingDialog.dismissProgressDialog();
//        loadingDialog = null;
//        ((BaseActivity) mContext).showErrorView();
//        if (e instanceof ApiException) {
//            ((ApiException) e).errorDeal(mContext);
//            Toast.makeText(MyApplication.getInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        } else if ((e instanceof UnknownHostException)) {
//            Toast.makeText(MyApplication.getInstance(), "网络异常", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof JsonSyntaxException) {
//            Toast.makeText(MyApplication.getInstance(), "数据异常", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof SocketTimeoutException) {
//            Toast.makeText(MyApplication.getInstance(), "连接超时", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof ConnectException) {
//            Toast.makeText(MyApplication.getInstance(), "连接服务器失败", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(MyApplication.getInstance(), "未知错误", Toast.LENGTH_SHORT).show();
//        }
//        onFail(e);
//    }
//
//    protected abstract void onSuccess(T t);
//
//    protected abstract void onFail(Throwable e);
//
//}

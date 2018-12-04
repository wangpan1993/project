package com.wp.project.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wp.project.application.Contants;
import com.wp.project.base.BasePresenter;
import com.wp.project.base.BaseResponse;
import com.wp.project.modle.JokeBean;
import com.wp.project.modle.ResponseBean;
import com.wp.project.network.HttpHold;
import com.wp.project.network.JsonCallback;
import com.wp.project.ui.interfaces.TestIView;


public class TestPresenter extends BasePresenter<TestIView> {


    public TestPresenter(Context mContext) {
        super(mContext);
    }

    public void showJoke(int page, int pagesize) {

    }

    public <T> void getJonke2(String key, final CallBack<T> callBack) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", "15a8570de0fad9f681fbb6b0ca352665");
        HttpHold.okGoPost("http://v.juhe.cn/toutiao/index", httpParams, new JsonCallback<ResponseBean<T>>() {
            @Override
            public void onSuccess(Response<ResponseBean<T>> response) {
                callBack.onSuccess(response.body().result);

            }

            @Override
            public void onError(Response<ResponseBean<T>> response) {
                super.onError(response);
                callBack.onFail();
            }
        });
    }

}

package com.wp.project.presenter;

import android.content.Context;
import android.util.Log;

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

import java.util.HashMap;
import java.util.Map;


public class TestPresenter extends BasePresenter<TestIView> {


    public TestPresenter(Context mContext) {
        super(mContext);
    }

    public void showJoke(int page, int pagesize) {

        Map<String, Object> params = getParams(new String[]{"page", "pagesize", "key"},
                new Object[]{page, pagesize, Contants.JUHE_KEY});

//        Api.getServer().jokeShow(params)
//                .compose(schedulersTransformer())
//                .subscribe(new HttpObserver<JokeBean>(mContext) {
//
//                    @Override
//                    protected void onSuccess(JokeBean jokeBean) {
//                        if (mView != null){
//                            mView.onShowJoke(jokeBean);
//                        }
//                    }
//
//                    @Override
//                    protected void onFail(Throwable e) {
//                    }
//                });
    }

    public void getJonke() {
//
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", "15a8570de0fad9f681fbb6b0ca352665");
        HttpHold.okGoPost("http://v.juhe.cn/toutiao/index", httpParams, new JsonCallback<BaseResponse<JokeBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<JokeBean>> response) {
                JokeBean result = response.body().getResult();
                Log.d("TestPresenter", "result:" + result);
            }
        });
    }

    public void getJonke2() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("key", "15a8570de0fad9f681fbb6b0ca352665");
        HttpHold.okGoPost("http://v.juhe.cn/toutiao/index", httpParams, new JsonCallback<ResponseBean<JokeBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<JokeBean>> response) {
                response.body().result.getData();
            }
        });
    }
}

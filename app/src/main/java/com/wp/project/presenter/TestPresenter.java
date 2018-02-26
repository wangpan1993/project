package com.wp.project.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wp.project.application.Contants;
import com.wp.project.base.BasePresenter;
import com.wp.project.base.BaseResponse;
import com.wp.project.modle.JokeBean;
import com.wp.project.network.JsonCallBack;



public class TestPresenter extends BasePresenter {


    public TestPresenter(Context mContext) {
        super(mContext);
    }

    public void getJonke(String url, int page, int pagesize,final int msgWhat) {
//        Map<String, String> params = getParams(new String[]{"page", "pagesize", "key"},
//                new Object[]{page, pagesize, Contants.JUHE_KEY});
        OkGo.<BaseResponse<JokeBean>>get(url)
                .params("page", page)
                .params("pagesize", pagesize)
                .params("key", Contants.JUHE_KEY)
                .execute(new JsonCallBack<BaseResponse<JokeBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<JokeBean>> response) {
                        super.onSuccess(response);
                        mMessage.what = msgWhat;
                        mMessage.obj = response.body().getResult();
                        mView.onSuccess(mMessage);
                    }
                });
    }


}

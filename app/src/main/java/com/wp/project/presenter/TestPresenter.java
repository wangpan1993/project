package com.wp.project.presenter;

import android.content.Context;
import android.util.Log;

import com.wp.project.application.Contants;
import com.wp.project.base.BasePresenter;
import com.wp.project.modle.beans.JokeBean;
import com.wp.project.network.Api;
import com.wp.project.network.HttpObserver;
import com.wp.project.ui.iview.TestIView;

import java.util.Map;


public class TestPresenter extends BasePresenter<TestIView> {


    public TestPresenter(Context mContext) {
        super(mContext);
    }

    public void showJoke(int page, int pagesize) {

        Map<String, Object> params = getParams(new String[]{"page", "pagesize", "key"},
                new Object[]{page, pagesize, Contants.JUHE_KEY});

        Api.getServer().jokeShow(params)
                .compose(schedulersTransformer())
                .subscribe(new HttpObserver<JokeBean>(mContext) {

                    @Override
                    protected void onSuccess(JokeBean jokeBean) {
                        if(mView!=null)
                        mView.onShowJoke(jokeBean);
                    }

                    @Override
                    protected void onFail(Throwable e) {
                        Log.d("TestPresenter", e.getMessage());
                    }
                });
    }
}

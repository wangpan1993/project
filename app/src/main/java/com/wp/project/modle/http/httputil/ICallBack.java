package com.wp.project.modle.http.httputil;

import android.util.Log;

import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.wp.project.base.BaseResponse;

/**
 * Created by WangPan on 2018/1/13.
 */

public abstract class ICallBack<T> implements Callback<BaseResponse<T>> {

    @Override
    public void onStart(Request<BaseResponse<T>, ? extends Request> request) {

    }

    @Override
    public void onSuccess(Response<BaseResponse<T>> response) {
        switch (response.body().getError_code()) {
            case 0:
                onSuccess(response.body().getResult());
                break;
            case 1:
                break;
            case 2:
                break;
        }

    }

    @Override
    public void onCacheSuccess(Response<BaseResponse<T>> response) {
        switch (response.body().getError_code()) {
            case 0:
                onSuccess(response.body().getResult());
                break;
            case 1://统一处理的错误
                break;
            case 2://统一处理的错误
                break;
            default://其他情况单独处理
                onFail(response.body());
                break;
        }
    }

    @Override
    public void onError(Response<BaseResponse<T>> response) {
        try {
            throw response.getException();
        } catch (Throwable throwable) {
            Log.d("ICallBack", response.getException().getMessage());
        }
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }


    @Override
    public BaseResponse<T> convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(BaseResponse<T> response);

}

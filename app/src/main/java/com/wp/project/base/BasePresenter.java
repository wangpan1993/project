package com.wp.project.base;


import android.content.Context;
import android.os.Message;

import com.wp.project.network.ApiException;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王攀 on 2017/2/22.
 */

public class BasePresenter<T extends BaseView> {

    protected T mView;
    protected Message mMessage;
    protected Context mContext;

    public T getView() {
        return mView;
    }

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void attachView(T t) {
        this.mView = t;
        mMessage = new Message();
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        if (mMessage != null) {
            mMessage = null;
        }
    }

    //统一请求参数处理
    protected Map<String, Object> getParams(String[] keys, Object[] values) {
        Map<String, Object> map = new HashMap<>();
        int keyLength = keys == null ? 0 : keys.length;
        int valuesLength = values == null ? 0 : values.length;
        if (keyLength != valuesLength) {
            throw new IllegalArgumentException("check your Params key or value length!");
        }
        for (int i = 0; i < keyLength; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    //统一请求响应处理
    protected <T> ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new Function<BaseResponse<T>, T>() {
                            @Override
                            public T apply(BaseResponse<T> tBaseResponse) throws Exception {
                                if (tBaseResponse.getError_code() != 0)
                                    throw new ApiException((BaseResponse<String>) tBaseResponse);

                                return tBaseResponse.getResult();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}

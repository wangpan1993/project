package com.wp.project.network;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.Set;

public class HttpHold {


    //http://v.juhe.cn/toutiao/index?key=15a8570de0fad9f681fbb6b0ca352665
    public static <T>void okGoPost(String url, HttpParams params, final JsonCallback<T> okCallBack){
        HttpParams publicParams = new HttpParams();
        publicParams.put(params);
        OkGo.<T>post(url)
                .params(publicParams)
                .execute(okCallBack);

    }
    public interface OkCallBack<T>{
        void onSuccess(T tBean);
        void onFail();
    }
}

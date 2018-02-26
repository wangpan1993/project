package com.wp.project.network;


import android.util.Log;

import com.bumptech.glide.load.resource.SimpleResource;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.wp.project.base.BaseResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;

/**
 * Created by WangPan on 2018/2/26.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {


        Type gentype = getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType) gentype).getActualTypeArguments();

        Type type = types[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");

        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArguments = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) return null;

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

//        if (rawType != BaseResponse.class) {
//            T data = gson.fromJson(jsonReader, type);
//            response.close();
//            return data;
//        } else {
//            if(typeArguments==Void.class){
//                SimpleResource simpleResource  = gson.fromJson(jsonReader,SimpleResource.class);
//                response.close();
//                return (T) simpleResource.toBaseResponse();
//
//            }else{
        BaseResponse baseResponse = gson.fromJson(jsonReader, type);
        response.close();
        int code = baseResponse.getResult();
        switch (code) {
            case 1001:
                return (T) baseResponse;
            case 1:
                return null;
            default:
                throw new IllegalStateException("错误编码:" + code + "，错误信息：" + baseResponse.getMsg());
        }

//            }
//        }

    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        if (exception != null) exception.printStackTrace();
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            Log.d("okgo", "网络连接失败，请检查网络");
        } else if (exception instanceof SocketTimeoutException) {
            Log.d("okgo", "网络连接超时");
        } else if (exception instanceof HttpException) {
            Log.d("okgo", ((HttpException) exception).message());
        } else {
            Log.d("okgo", "访问异常");
        }
    }

    @Override
    public void onSuccess(Response<T> response) {

    }


}

package com.wp.project.modle.http.httputil;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WangPan on 2017/6/13.
 */
public class HttpUtil {

    private final OkHttpClient mOkHttpClient;
    private final Gson mGson;
    private Handler mHandler;
    private static HttpUtil mHttpUtil;

    private HttpUtil() {
        //1 实例OkHttpClient;
        mOkHttpClient = new OkHttpClient();
        //实例化Gson对象
        mGson = new Gson();
        //得到主线程的Handler
        //参数为Loper类的静态方法，得到主线程的轮训器
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static HttpUtil getIntance() {
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    //get请求获得String
    public void getStringFromNet(String url, CallBack callback) {
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    //get请求获得Bean
    public <T> void getFromBean(final String url, final Class<T> clazz, final CallBack<T> callBack) {
        //2 实例化Request对象
        Request request = new Request.Builder().url(url).build();
        //3 请求网络数据
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //服务器返回数据类型为String类型
                //得到json串
                String json = response.body().string();
                final T result = mGson.fromJson(json, clazz);
                //线程转换
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //完成线程转换
                        callBack.success(result);
                    }
                });
            }
        });
    }

    /**
     * post请求
     *
     * @param url
     * @param clazz
     * @param callBack
     * @param params
     * @param <T>
     */
    public <T> void postBeanFromNet(String url,
                                    final Class<T> clazz, final CallBack<T> callBack, Params... params) {
        //得到Request对象
        Request request = postRequest(url, params);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //服务器返回数据类型为String类型
                //得到json串
                String json = response.body().string();
                Log.d("HttpUtil", json);
                final T result = mGson.fromJson(json, clazz);
                //线程转换
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //完成线程转换
                        callBack.success(result);
                    }
                });
            }
        });
    }

    public Request postRequest(String url, Params[] params) {
        //得到FormEncodingBuilder对象，用来存储post的请的参数，键值对形式
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("xwdoor", "xwdoor");
        RequestBody formBody = builder.build();
        //构建Request对象
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        return request;
    }


    public interface CallBack<T> extends Callback {
        void success(T result);

        void error();

        @Override
        void onFailure(Call call, IOException e);

        @Override
        void onResponse(Call call, Response response) throws IOException;
    }

    public static class Params {
        public String key;
        public String value;

        public Params(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}

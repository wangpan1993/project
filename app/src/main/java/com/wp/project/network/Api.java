package com.wp.project.network;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wp.project.application.Contants;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 单例retrofit，添加日志收集（d级别）   关键字：retrofit
 * Created by 王攀 on 2017/2/22.
 */

public class Api {
    private OkHttpClient okHttpClient;
    private ApiService apiserver;

    private Api() {
        okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {//这个chain里面包含了request和response，所以你要什么都可以从这里拿
                Request request = chain.request();
                long t1 = System.nanoTime();//请求发起的时间

                String method = request.method();
                if ("POST".equals(method)) {
                    StringBuilder sb = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        Log.d("retrofit", String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                                request.url(), chain.connection(), request.headers(), sb.toString()));
                    }
                } else {
                    Log.d("retrofit", String.format("发送请求 %s on %s%n%s",
                            request.url(), chain.connection(), request.headers()));
                }
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();//收到响应的时间
                //这里不能直接使用response.body().string()的方式输出日志
                //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
                //个新的response给应用层处理
                ResponseBody responseBody = response.peekBody(1024 * 1024);
                Log.d("retrofit",
                        String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                                response.request().url(),
                                responseBody.string(),
                                (t2 - t1) / 1e6d,
                                response.headers()
                        ));
                return response;
            }
        }).build();

        apiserver = new Retrofit.Builder()
                .baseUrl(Contants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiService.class);
    }

    private static class RetrofitInstance {
        private static Api api = new Api();
    }

    //得到Server对象
    public static ApiService getServer() {
        return RetrofitInstance.api.apiserver;
    }
}

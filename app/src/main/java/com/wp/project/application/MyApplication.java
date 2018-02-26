package com.wp.project.application;


import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.model.HttpParams;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 王攀 on 2017/2/22.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getScreenSize();
        instance = this;
        init();
    }

    private void init() {

        initOkGo();
    }

    private void initOkGo() {
        getOkhttpClient();
        HttpParams params = new HttpParams();
        params.put("bizid", "bizid");
        params.put("marking", "marking");
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(getOkhttpClient())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                //全局统一缓存模式，默认不使用缓存，可以不传
//                .setCacheTime(1 * 60 * 60 * 1000)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次
                // (一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders()                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }

    public OkHttpClient getOkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(getInterceptor());//拦截器

        //使用数据库保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));

        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //自签名证书
//        try {
//            HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open
// ("srca.cer"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        return builder.build();
    }

    @NonNull
    private Interceptor getInterceptor() {
        return new Interceptor() {
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
                        Log.d("okgo", String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                                request.url(), chain.connection(), request.headers(), sb.toString
                                        ()));
                    }
                } else {
                    Log.d("okgo", String.format("发送请求 %s on %s%n%s",
                            request.url(), chain.connection(), request.headers()));
                }
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();//收到响应的时间
                //这里不能直接使用response.body().string()的方式输出日志
                //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
                //个新的response给应用层处理
                ResponseBody responseBody = response.peekBody(1024 * 1024);
                Log.d("okgo",
                        String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                                response.request().url(),
                                responseBody.string(),
                                (t2 - t1) / 1e6d,
                                response.headers()
                        ));
                return response;
            }
        };
    }

    /**
     * 初始化屏幕宽高
     */
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    /**
     * 这还有一系列的第三方SDK的初始化
     */
}

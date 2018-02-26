//package com.wp.project.network;
//
//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import com.wp.project.application.Contants;
//import com.wp.project.application.MyApplication;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * 单例retrofit，添加日志收集（d级别）   关键字：retrofit
// * Created by 王攀 on 2017/2/22.
// */
//
//public class Api {
//    private OkHttpClient okHttpClient;
//    private ApiService apiserver;
//
//    private Api() {
//        apiserver = new Retrofit.Builder()
//                .baseUrl(Contants.BASE_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(MyApplication.getInstance().getOkhttpClient())
//                .build()
//                .create(ApiService.class);
//    }
//
//    private static class RetrofitInstance {
//        private static Api api = new Api();
//    }
//
//    //得到Server对象
//    public static ApiService getServer() {
//        return RetrofitInstance.api.apiserver;
//    }
//}

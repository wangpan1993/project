//package com.wp.project.network;
//
//import com.wp.project.application.Contants;
//import com.wp.project.base.BaseResponse;
//import com.wp.project.modle.beans.JokeBean;
//
//import java.util.Map;
//
//import io.reactivex.Observable;
//import retrofit2.http.FieldMap;
//import retrofit2.http.FormUrlEncoded;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.Path;
//import retrofit2.http.Query;
//import retrofit2.http.QueryMap;
//
///**
// * 接口文档整理
// * Created by 王攀 on 2017/2/22.
// */
//
//public interface ApiService {
////    String key = "3bcdd67e654cd2e959fb133e76d27fa0";
////
////    @FormUrlEncoded
////    @POST("login")
////    Observable<BaseResponse<LoginBean>> login(@Field("phone_num") String nick_name,
////                                          @Field("user_password") String phone_num);
//
//    @GET(Contants.URL_TEST)
//    Observable<BaseResponse<JokeBean>> jokeShow(@Query("page") int page, @Query("pagesize") int pagesize, @Query("key") String key);
//
//    @GET(Contants.URL_TEST)
//    Observable<BaseResponse<JokeBean>> jokeShow(@QueryMap() Map<String, Object> map);
//
//    @FormUrlEncoded
//    @POST(Contants.URL_TEST)
//    Observable<BaseResponse<JokeBean>> postStringDatas(@FieldMap Map<String, Object> map);
//
//    //统一数据get调用：回调类型：String
//    @GET("{url}")
//    Observable<BaseResponse<String>> getStringDatas(@Path("url") String url, @QueryMap() Map<String, Object> map);
//
//    //统一数据post调用：回调类型：String
//    @FormUrlEncoded
//    @POST("{url}")
//    Observable<BaseResponse<String>> postDatas(@Path("url") String url, @FieldMap() Map<String, Object> map);
//
//    @GET("{url}")
//    Observable<BaseResponse<Object>> getObjectDatas(@Path("url") String url, @QueryMap() Map<String, Object> map);
//
//    @FormUrlEncoded
//    @POST("{url}")
//    Observable<BaseResponse<Object>> postObjectDatas(@Path("url") String url, @FieldMap() Map<String, Object> map);
//
//
//}

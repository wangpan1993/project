package com.wp.project.network;

import com.wp.project.application.Contants;
import com.wp.project.base.BaseResponse;
import com.wp.project.modle.beans.JokeBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 接口文档整理
 * Created by 王攀 on 2017/2/22.
 */

public interface ApiService {
//    String key = "3bcdd67e654cd2e959fb133e76d27fa0";
//
//    @FormUrlEncoded
//    @POST("login")
//    Observable<BaseResponse<LoginBean>> login(@Field("phone_num") String nick_name,
//                                          @Field("user_password") String phone_num);

    @GET(Contants.URL_TEST)
    Observable<BaseResponse<JokeBean>> jokeShow(@Query("page") int page,
                                                @Query("pagesize") int pagesize,
                                                @Query("key") String key);

    @GET(Contants.URL_TEST)
    Observable<BaseResponse<JokeBean>> jokeShow(@QueryMap() Map<String, Object> map);

    @FormUrlEncoded
    @POST(Contants.URL_TEST)
    Observable<BaseResponse<JokeBean>> postDatas(@FieldMap Map<String, Object> map);
}

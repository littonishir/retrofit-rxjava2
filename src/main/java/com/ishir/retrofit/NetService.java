package com.ishir.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by littonishir on 2017/10/20.
 */

public interface NetService {
    /**
     * 此处的接口是用来定义项目中的所有的业务方法
     * Observable为回调对象 <泛型>为Bean类  xxx是方法名(自己任意取)
     */
    @FormUrlEncoded //该注解表示会对请求体的参数进行url编码，目的是防止中文乱码
    @POST("ishir")
    Observable<LoginBean> login(@Field("name") String p1, @Field("password") String p2);
}

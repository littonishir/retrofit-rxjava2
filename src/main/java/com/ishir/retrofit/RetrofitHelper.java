package com.ishir.retrofit;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by littonishir on 2017/10/20.
 */

public class RetrofitHelper {
    private static RetrofitHelper mInstance = new RetrofitHelper();

    Retrofit retrofit;
    NetService netService;

    private RetrofitHelper() {
        //1.创建Regrofit的实例对象
        retrofit = new Retrofit.Builder()
                //设置服务器主机,注意：baseUrl必须以/结尾，否则报错
                .baseUrl("http://192.168.7.57:3000/")
                //配置gson解析器,内部会使用Gson库来解析java bean
                .addConverterFactory(GsonConverterFactory.create())
                //用于返回Rxjava调用
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //添加Http拦截器,拦截后台返回的数据
                .client(getOkHttpClient())
                .build();

        //2.获取NetService业务接口的实现类对象,其中内部是通过动态代理来创建接口实现类对象的
        netService = retrofit.create(NetService.class);
    }

    public static RetrofitHelper create() {
        return mInstance;
    }

    public NetService getNetService() {
        return netService;
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("ishir", "result: " + message);
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }
}

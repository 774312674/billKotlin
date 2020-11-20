package com.rb.httpretrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TODO desc
 *
 * @author: miaoke1
 * @date: 2020/11/20
 *
 * https://blog.csdn.net/cs_lwb/article/details/82016997
 */
public class RetrofitUtil {

    public void init(){

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(10,TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接
                .build()
                ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsUrl.baseUrl)
                //设置自定义okHttp
                .client(client)
                //添加GSON解析：返回数据转换成GSON类型
                .addConverterFactory(GsonConverterFactory.create())//添加Rxjava支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiUrl api = retrofit.create(ApiUrl.class);

//        Call<Bean> demo = api.getRetrofit();
//        demo.enqueue(new Callback<Bean>() {
//            @Override
//            public void onResponse(Call<Bean> call, Response<Bean> response) {
//                System.out.print("请求成功信息: "+response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Bean> call, Throwable t) {
//                System.out.print("请求失败信息: " +t.getMessage());
//            }
//        });
    }


}
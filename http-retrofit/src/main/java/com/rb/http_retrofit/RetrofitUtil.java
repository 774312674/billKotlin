package com.rb.http_retrofit;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsUrl.baseUrl)
                //设置自定义okHttp
                .client(initOkHttp())
                //添加GSON解析：返回数据转换成GSON类型
                .addConverterFactory(GsonConverterFactory.create())//添加Rxjava支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiUrl api = retrofit.create(ApiUrl.class);
        api.getRetrofit1()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "onSubscribe: " );
                    }

                    @Override
                    public void onNext(Bean bean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 初始化okhttp
     */
    private OkHttpClient initOkHttp() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(10,TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接
                .build();
    }


}
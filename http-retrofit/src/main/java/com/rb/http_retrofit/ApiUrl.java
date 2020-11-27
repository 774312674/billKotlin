package com.rb.http_retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * TODO desc
 *
 * @author: miaoke1
 * @date: 2020/11/20
 */
public interface ApiUrl {

    @GET(ConstantsUrl.retrofit)
    Observable<Bean> getRetrofit1();

}

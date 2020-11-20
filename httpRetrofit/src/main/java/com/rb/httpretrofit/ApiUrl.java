package com.rb.httpretrofit;

import java.util.Observable;

import retrofit2.Call;
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

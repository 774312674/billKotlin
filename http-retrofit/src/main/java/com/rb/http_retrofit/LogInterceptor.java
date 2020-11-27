package com.rb.http_retrofit;

import android.util.Log;

import com.rb.http_retrofit.log.HttpLogUtil;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * TODO desc
 * 日志拦截器
 *
 * @author: miaoke1
 * @date: 2020/11/20
 */
class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpLogUtil.getInstance().e("request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        HttpLogUtil.getInstance().e(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        HttpLogUtil.getInstance().e("response body:" + content);

        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
//                .header("Authorization", Your.sToken)
                .build();
    }
}

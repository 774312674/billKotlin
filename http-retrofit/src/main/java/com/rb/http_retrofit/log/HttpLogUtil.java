package com.rb.http_retrofit.log;

import android.net.Uri;

/**
 * TODO desc
 *
 * @author: miaoke1
 * @date: 2020/11/23
 */
public class HttpLogUtil extends LogUtil{
    private static final String TAG = "httpRetrofit";
    private static HttpLogUtil instance;
    public static HttpLogUtil getInstance(){
        if (instance==null){
            instance= new HttpLogUtil();
        }
        return instance;
    }

    public HttpLogUtil() {
        super(TAG);
    }
}

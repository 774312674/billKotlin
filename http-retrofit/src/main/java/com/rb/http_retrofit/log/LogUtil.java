package com.rb.http_retrofit.log;

import android.util.Log;

/**
 * TODO desc
 *
 * @author: miaoke1
 * @date: 2020/11/23
 */
class LogUtil {

    private String TAG;

    public LogUtil(String TAG) {
        this.TAG = TAG;
    }

    public void e(String msg){
        Log.e(TAG,msg);
    }

    public void i(String msg){
        Log.i(TAG,msg);
    }

    public void d(String msg){
        Log.d(TAG,msg);
    }




}

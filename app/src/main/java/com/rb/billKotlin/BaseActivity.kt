package com.rb.billKotlin

import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity


/**
 * @author: changZePeng
 * @date: 2020/8/31
 */
open class BaseActivity : RxAppCompatActivity(){

    fun showToast(con:String){
        Toast.makeText(this,con,Toast.LENGTH_SHORT).show();
    }


    /**
     * 检查是否有对应权限
     *
     * @param activity 上下文
     * @param permission 要检查的权限
     * @return  结果标识
     */
    fun verifyPermissions(activity: Activity?, permission: String?): Int {
        return if (ActivityCompat.checkSelfPermission(activity!!, permission!!) == PackageManager.PERMISSION_GRANTED) {
            1
        } else {
            0
        }
    }

}
package com.rb.billKotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.View
import android.widget.TextView


//:继承
class MainActivity : BaseActivity(),View.OnClickListener{

    /**
     * lateinit 延迟初始化
     */
    private lateinit var tvSee:TextView
    private lateinit var tvAdd:TextView

    //savedInstanceState: Bundle?  ?表示可能为null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSee = findViewById(R.id.tvSee)
        tvSee.setOnClickListener(this)
        tvAdd = findViewById(R.id.tvAdd)
        tvAdd.setOnClickListener(this)

        //跳转相机动态权限
        //跳转相机动态权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }

    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id==R.id.tvSee){
                startActivity(Intent(this,LookUpActivity::class.java))
            }else if (v.id==R.id.tvAdd){
                startActivity(Intent(this, AddActivity::class.java))
            }
        }
    }









}
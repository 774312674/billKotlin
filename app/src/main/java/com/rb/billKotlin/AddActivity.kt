package com.rb.billKotlin

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rb.billKotlin.database.CheckDao
import com.rb.billKotlin.database.CheckTabBean
import com.rb.billKotlin.dialog.SystemImageDialog
import com.rb.billKotlin.util.FileUtil
import com.rb.billKotlin.util.UriUtil
import kotlinx.android.synthetic.main.activity_add.*
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * @author: changZePeng
 * @date: 2020/8/31
 */
class AddActivity : BaseActivity() {

    //需要的权限数组 读/写/相机
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    //lateinit延迟初始化
    lateinit var tvContent: TextView
    lateinit var tvPrice: TextView
    lateinit var tvImage: TextView
    lateinit var etContent: EditText
    lateinit var etPrice: EditText
    lateinit var rvImages: RecyclerView
    lateinit var btAdd: Button
    lateinit var btPreserve: Button

    lateinit var imagesAdapter:ImageAdapter

    lateinit var imageUri: Uri
    lateinit var images: ArrayList<String>
    val TAKE_CAMERA = 101
    val TAKE_PICK = 100

    lateinit var checkDao: CheckDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        tvContent = findViewById(R.id.tvContent)
        tvPrice = findViewById(R.id.tvPrice)
        tvImage = findViewById(R.id.tvImage)
        etContent = findViewById(R.id.etContent)
        etPrice = findViewById(R.id.etPrice)
        rvImages = findViewById(R.id.rvImages)
        btAdd = findViewById(R.id.btAdd)
        btPreserve = findViewById(R.id.btPreserve)

        btAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (v != null) {
                    val systemImageDialog = SystemImageDialog()
                    systemImageDialog.clickListener = object : SystemImageDialog.ClickListener {
                        override fun clickPhoto() {

                            var intent = Intent(Intent.ACTION_PICK)  //跳转到 ACTION_IMAGE_CAPTURE
                            intent.type = "image/*"
                            startActivityForResult(intent,TAKE_PICK)

                            systemImageDialog.dismiss()
                        }

                        override fun clickCamera() {

                            if(verifyPermissions(this@AddActivity,PERMISSIONS_STORAGE[2]) == 0){
                                ActivityCompat.requestPermissions(this@AddActivity, PERMISSIONS_STORAGE, 3);
                            }else{
                                //已经有权限
                                imageUri = getFileNameUri()
                                //启动相机程序
                                //启动相机程序
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                                startActivityForResult(intent, TAKE_CAMERA)
                            }
                            systemImageDialog.dismiss()
                        }
                    }
                    systemImageDialog.show(
                        supportFragmentManager, SystemImageDialog::
                        class.java.toString()
                    )
                }
            }
        })

        if(verifyPermissions(this,PERMISSIONS_STORAGE[2]) == 0){
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 3);
        }else{
            checkDao = CheckDao(this)
        }

        //lambda表达式规则
        btPreserve.setOnClickListener{ v ->
            if (v != null) {
                submit()
            }
        }

        val id = intent.getLongExtra("id",-1)

        images = if ( id<0){
             arrayListOf()
        }else{
            val checkTabBean = checkDao.select(id)
            etContent.setText(checkTabBean.content)
            etPrice.setText(checkTabBean.price.toString())
            etRemarks.setText(checkTabBean.remarks)
            val image = checkTabBean.image

            Gson().fromJson(image, object : TypeToken<ArrayList<String>>() {}.type)
        }
        imagesAdapter= ImageAdapter(images)
        rvImages.layoutManager = GridLayoutManager(this,3)
        rvImages.adapter = imagesAdapter

    }

    private fun submit(){
        val content= etContent.text.toString()
        val price = etPrice.text.toString().toDouble()
        val remarks=etRemarks.text.toString()

        var image= Gson().toJson(images).toString()

        CheckDao(this).insert(CheckTabBean(content,price,remarks,image))
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            TAKE_CAMERA -> if (resultCode == Activity.RESULT_OK) {
                if (imageUri.scheme!=null){
                    images.add(imageUri.path.toString())
                    imagesAdapter.notifyDataSetChanged()
                }
            }

            TAKE_PICK -> if (resultCode ==  Activity.RESULT_OK){
                try {
                    if (data!=null){
                        val uri01 = data.data
                        if(uri01!=null){
                            val oldFile = UriUtil.getRealPathFromUri(this@AddActivity, uri01);
                            val newFile = getFileNameUri().path.toString()

                            FileUtil.copyFile(oldFile, newFile,handler,0x01)

                        }
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    }

    private var handler= object :Handler(Looper.myLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what==0x01){
                images.add(msg.obj.toString())
                imagesAdapter.notifyDataSetChanged()
            }else if(msg.what==0x400){
                showToast(msg.obj.toString())
            }
        }
    }



    private fun getFileName():File{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date =Date()
        val name = dateFormat.format(date)
        val dateFormat1 = SimpleDateFormat("yyyy-MM-dd")
        val name1 = dateFormat1.format(date)
//        var outputImage = File(getExternalFilesDir(""), "rb_$name.jpg");
        var outputImage = File(getExternalFilesDir("images/bill/$name1"), "rb_$name.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return outputImage
    }


    fun getFileNameUri():Uri{
        // 创建File对象，用于存储拍照后的图片
        //存放在手机SD卡的应用关联缓存目录下
        /* 从Android 6.0系统开始，读写SD卡被列为了危险权限，如果将图片存放在SD卡的任何其他目录，
           都要进行运行时权限处理才行，而使用应用关联 目录则可以跳过这一步
         */
        return Uri.fromFile(getFileName())

    }



}











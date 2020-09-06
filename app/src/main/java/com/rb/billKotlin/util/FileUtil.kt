package com.rb.billKotlin.util

import android.os.Handler
import android.os.Message
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * @author: changZePeng
 * @date: 2020/9/1
 */
class FileUtil{

    companion object{

        /**
         * errorCode 0x400
         */
        fun copyFile(old: String?, new: String, handler: Handler, successCode:Int){

            Thread() {
                kotlin.run {
                    if (old!=null){
                        val oldFile = File(old)
                        val newFile = File(new)
                        if (oldFile.exists()) {
                            val inPut = FileInputStream(oldFile)
                            val outPut = FileOutputStream(newFile)
                            inPut.channel.transferTo(0, inPut.channel.size(), outPut.channel)
                            inPut.close();
                            inPut.channel.close()
                            outPut.close()
                            outPut.channel.close()

                            val message = Message()
                            message.what = successCode
                            message.obj = newFile
                            handler.sendMessage(message)
                        }else{
                            val message = Message()
                            message.what = 0x400
                            message.obj = "没有找到文件"
                            handler.sendMessage(message)
                        }
                    }else{
                        val message = Message()
                        message.what = 0x400
                        message.obj = "没有找到文件"
                        handler.sendMessage(message)
                    }

                }
            }.start()
        }
    }



}
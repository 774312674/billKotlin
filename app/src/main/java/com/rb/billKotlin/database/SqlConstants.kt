package com.rb.billKotlin.database

/**
 * @author: changZePeng
 * @date: 2020/8/31
 */

class SqlConstants{

    //静态变量
    companion object{
        //文件名
        const val DB_NAME = "myTest.db"
        //版本号
        const val VERSION = 1

        //表名
        const val TAB_NAME = "bill"
        //字段id
        const val TAB_CHECK_ID = "id"
        //字段内容 content
        const val TAB_CHECK_CONTENT = "content"
        //字段数字 prick
        const val TAB_CHECK_PRICE = "price"
        //字段备注remarks
        const val TAB_CHECK_REMARKS = "remarks"
        //字段图片
        const val TAB_CHECK_IMAGE = "image"
        //字段创建时间
        const val TAB_CHECK_CREATE_TIME = "create_time"
        //字段修改时间
        const val TAB_CHECK_UPLOAD_TIME = "upload_time"



    }
}
package com.rb.rbkotlin.database

/**
 * @author: changZePeng
 * @date: 2020/8/31
 */
class CheckTabBean(){

    var id: Long? = null
    lateinit var content: String
    var price: Double = 0.0
    lateinit var remarks: String
    lateinit var image: String
    var createTime: Long = 0
    var uploadTime: Long = 0

    constructor(id: Long,content: String,price:Double,remarks: String,image: String,create_time: Long,upload_time: Long) : this() {
        this.id = id
        this.content = content
        this.price = price
        this.remarks = remarks
        this.image = image
        this.createTime = create_time
        this.uploadTime = upload_time
    }

    constructor(content: String,price:Double,remarks: String,image: String):this(){
        this.content = content
        this.price = price
        this.remarks = remarks
        this.image = image
        this.createTime = System.currentTimeMillis()
        this.uploadTime =  System.currentTimeMillis()

    }

    constructor(content: String,price:Double,remarks: String,image: String,create_time: Long):this(){
        this.content = content
        this.price = price
        this.remarks = remarks
        this.image = image
        this.createTime = create_time
        this.uploadTime =  System.currentTimeMillis()

    }





}


package com.rb.rbkotlin.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

/**
 * @author: changZePeng
 * @date: 2020/8/31
 */
class CheckDao {
    private var dbHelper: DBHelper;
    private var database: SQLiteDatabase;

    constructor(context: Context) {
        dbHelper = DBHelper(context)
        database = dbHelper.writableDatabase
    }

    fun insert(checkTabBean: CheckTabBean): Boolean {
        val contentValues = contentValuesOf()
        contentValues.put(SqlConstants.TAB_CHECK_CONTENT, checkTabBean.content)
        contentValues.put(SqlConstants.TAB_CHECK_PRICE, checkTabBean.price)
        contentValues.put(SqlConstants.TAB_CHECK_REMARKS, checkTabBean.remarks)
        contentValues.put(SqlConstants.TAB_CHECK_IMAGE, checkTabBean.image)
        contentValues.put(SqlConstants.TAB_CHECK_CREATE_TIME, checkTabBean.createTime)
        contentValues.put(SqlConstants.TAB_CHECK_UPLOAD_TIME, checkTabBean.uploadTime)
        val long = database.insert(SqlConstants.TAB_NAME, null, contentValues)
        return long > 0
    }

    fun delete(id: String): Boolean {
        val long =
            database.delete(SqlConstants.TAB_NAME, SqlConstants.TAB_CHECK_ID + "=?", arrayOf(id))
        return long > 0
    }

    fun selectAll(): ArrayList<CheckTabBean> {
        val cursor = database.query(
            SqlConstants.TAB_NAME, arrayOf(
                SqlConstants.TAB_CHECK_ID,
                SqlConstants.TAB_CHECK_CONTENT,
                SqlConstants.TAB_CHECK_PRICE,
                SqlConstants.TAB_CHECK_REMARKS,
                SqlConstants.TAB_CHECK_IMAGE,
                SqlConstants.TAB_CHECK_CREATE_TIME,
                SqlConstants.TAB_CHECK_UPLOAD_TIME
            ), null, null, null, null, null
        )
        val list: ArrayList<CheckTabBean> = ArrayList<CheckTabBean>()
        return if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                list.add(
                    CheckTabBean(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getLong(5),
                        cursor.getLong(6)
                    )
                )
            } while (cursor.moveToNext())
            list
        } else {
            list
        }
    }




    fun select(id: Long):CheckTabBean {
        val cursor = database.query(
            SqlConstants.TAB_NAME, arrayOf(
                SqlConstants.TAB_CHECK_ID,
                SqlConstants.TAB_CHECK_CONTENT,
                SqlConstants.TAB_CHECK_PRICE,
                SqlConstants.TAB_CHECK_REMARKS,
                SqlConstants.TAB_CHECK_IMAGE,
                SqlConstants.TAB_CHECK_CREATE_TIME,
                SqlConstants.TAB_CHECK_UPLOAD_TIME
            ), SqlConstants.TAB_CHECK_ID+"=$id", null, null, null, null
        )
        cursor.moveToFirst()
        return CheckTabBean(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getDouble(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getLong(5),
                cursor.getLong(6)
            )

    }

}







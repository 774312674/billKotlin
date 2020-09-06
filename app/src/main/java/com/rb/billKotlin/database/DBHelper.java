package com.rb.billKotlin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

/**
 * @author: changZePeng
 * @date: 2020/8/31
 */
class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, SqlConstants.DB_NAME, null, SqlConstants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String sql ="create table if not exists " + SqlConstants.TAB_NAME + "("+
                SqlConstants.TAB_CHECK_ID+" integer PRIMARY KEY autoincrement ," +
                SqlConstants.TAB_CHECK_CONTENT+" text, " +
                SqlConstants.TAB_CHECK_PRICE+" double ," +
                SqlConstants.TAB_CHECK_REMARKS +" text," +
                SqlConstants.TAB_CHECK_CREATE_TIME +" time," +
                SqlConstants.TAB_CHECK_UPLOAD_TIME +" time," +
                SqlConstants.TAB_CHECK_IMAGE +" text" +
                ")";*/
        String sql = String.format("create table if not exists %s(%s integer PRIMARY KEY autoincrement ,%s text, %s double ,%s text,%s time,%s time,%s text)", SqlConstants.TAB_NAME, SqlConstants.TAB_CHECK_ID, SqlConstants.TAB_CHECK_CONTENT, SqlConstants.TAB_CHECK_PRICE, SqlConstants.TAB_CHECK_REMARKS, SqlConstants.TAB_CHECK_CREATE_TIME, SqlConstants.TAB_CHECK_UPLOAD_TIME, SqlConstants.TAB_CHECK_IMAGE);



        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

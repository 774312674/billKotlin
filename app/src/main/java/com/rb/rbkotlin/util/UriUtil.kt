package com.rb.rbkotlin.util

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore

/**
 * @author: changZePeng
 * @date: 2020/9/2
 */
class UriUtil{
    companion object{

        fun getRealPathFromUri(
            context: Context,
            uri: Uri
        ): String? {
            var filePath: String? = null
            if (DocumentsContract.isDocumentUri(context, uri)) {
                // 如果是document类型的 uri, 则通过document id来进行处理
                val documentId = DocumentsContract.getDocumentId(uri)
                if (isMediaDocument(uri)) { // MediaProvider
                    // 使用':'分割
                    val id = documentId.split(":".toRegex()).toTypedArray()[1]
                    val selection = MediaStore.Images.Media._ID + "=?"
                    val selectionArgs = arrayOf(id)
                    filePath = getDataColumn(
                        context,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection,
                        selectionArgs
                    )
                } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                    val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        java.lang.Long.valueOf(documentId)
                    )
                    filePath = getDataColumn(context, contentUri, null, null)
                }
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                // 如果是 content 类型的 Uri
                filePath = getDataColumn(context, uri, null, null)
            } else if ("file" == uri.scheme) {
                // 如果是 file 类型的 Uri,直接获取图片对应的路径
                filePath = uri.path
            }
            return filePath
        }


        /**
         * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
         *
         * @return
         */
        fun getDataColumn(
            context: Context,
            uri: Uri,
            selection: String?,
            selectionArgs: Array<String>?
        ): String? {
            var path: String? = null
            val projection =
                arrayOf(MediaStore.Images.Media.DATA)
            var cursor: Cursor? = null
            try {
                cursor =
                    context.contentResolver.query(uri, projection, selection, selectionArgs, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val columnIndex: Int = cursor.getColumnIndexOrThrow(projection[0])
                    path = cursor.getString(columnIndex)
                }
            } catch (e: java.lang.Exception) {
                if (cursor != null) {
                    cursor.close()
                }
            }
            return path
        }
        /**
         * @param uri the Uri to check
         * @return Whether the Uri authority is MediaProvider
         */
        fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }


        /**
         * @param uri the Uri to check
         * @return Whether the Uri authority is DownloadsProvider
         */
        fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }





    }


}
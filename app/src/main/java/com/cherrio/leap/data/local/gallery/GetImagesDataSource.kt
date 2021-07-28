package com.cherrio.leap.data.local.gallery

import android.app.Application
import android.content.ContentUris
import android.os.Build
import android.provider.MediaStore
import com.cherrio.leap.data.model.Images
import com.cherrio.leap.utils.SDKChecks
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetImagesDataSource @Inject constructor() {

    fun getImagesByFolder(application: Application): MutableMap<String, MutableList<Images>> {
        val imageFolders = mutableMapOf<String, MutableList<Images>>()
        var images: MutableList<Images>? = null

        val collection = if (SDKChecks.isOrGreaterThanApi29()) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val relativePath = if (SDKChecks.isOrGreaterThanApi29())
            MediaStore.Images.Media.RELATIVE_PATH
        else
            MediaStore.Images.Media.DATA


        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            relativePath,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )

        val sortOrder = MediaStore.Images.Media.DATE_TAKEN

        val query = application.contentResolver.query(
            collection,
            projection,
            null,
            null,
            sortOrder
        )
        query?.use { cursor ->
            // Cache column indices.
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val pathColumn = cursor.getColumnIndexOrThrow(relativePath)
            val bucketColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getInt(sizeColumn)
                val path = cursor.getString(pathColumn)
                val bucket = cursor.getString(bucketColumn)

                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                images = imageFolders[bucket]
                if (images == null) {
                    images = mutableListOf()
                }
                images?.add(Images(contentUri, name))

                imageFolders[bucket] = images!!

            }

        }
        return imageFolders
    }
}
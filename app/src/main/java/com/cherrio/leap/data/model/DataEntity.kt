package com.cherrio.leap.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataEntity(
    @PrimaryKey
    val url: String,
    val title: String,
    val author: String? = "",
    val description: String,
    val image: String? = "",
    val published_at: String
) {
    companion object {
        fun mapToDataEntity(data: Data): DataEntity {
            return DataEntity(
                data.url,
                data.title,
                data.author,
                data.description,
                data.image,
                data.published_at
            )
        }
    }
}
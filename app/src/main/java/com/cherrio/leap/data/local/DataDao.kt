package com.cherrio.leap.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cherrio.leap.data.model.DataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {
    @Query("SELECT * from dataentity")
    suspend fun getAllNewsData(): List<DataEntity>

    @Insert
    suspend fun addData(dataEntity: DataEntity)

    @Delete
    suspend fun delete(dataEntity: DataEntity)
}
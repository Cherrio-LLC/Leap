package com.cherrio.leap.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cherrio.leap.data.model.DataEntity


@Database(entities = arrayOf(DataEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dataEntityDao(): DataDao

    /**
     * This code will make the database a singleton, meaning it is only created once
     * not multiple times.
     */
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "data_entity.db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
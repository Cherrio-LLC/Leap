package com.cherrio.leap

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cherrio.leap.data.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LeapApp: Application() {

}
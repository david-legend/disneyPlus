package com.davidcobbina.disneyplus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidcobbina.disneyplus.data.local.stores.Profile
import com.davidcobbina.disneyplus.data.local.stores.ProfileDao

@Database(entities = [Profile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val profiles: ProfileDao
}
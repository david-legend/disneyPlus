package com.davidcobbina.disneyplus.di

import android.content.Context
import androidx.room.Room
import com.davidcobbina.disneyplus.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java,
        "database.db"
    ).build()
}
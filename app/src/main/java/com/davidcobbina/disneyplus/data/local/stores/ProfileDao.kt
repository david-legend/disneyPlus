package com.davidcobbina.disneyplus.data.local.stores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("select * from profile order by id ASC")
    fun queryAll(): Flow<List<Profile>>

    @Query("select * from profile where id = :id")
    suspend fun getProfile(id: Int): Profile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: Profile)


}
package com.davidcobbina.disneyplus.data.local.stores

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val avatar: Int,
    val username: String
)

package com.davidcobbina.disneyplus.data.local.model

import android.net.Uri
import com.google.gson.Gson

data class UserProfile(
    var id: Int = 0,
    val avatar: Int = 0,
    val username: String = ""
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

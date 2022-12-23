package com.davidcobbina.disneyplus.data.local.model

import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson


data class Menu(
    val title: String,
    val icon: Int,
    val isSelected: Boolean = false,
    val isBrand: Boolean = false,
    val apiId: String = "",
    val color: Color? = null
) {

    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
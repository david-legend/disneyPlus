package com.davidcobbina.disneyplus.ui.components

import android.content.Context
import android.widget.Toast

fun DisneyToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast
        .makeText(context, message, duration)
        .show()
}
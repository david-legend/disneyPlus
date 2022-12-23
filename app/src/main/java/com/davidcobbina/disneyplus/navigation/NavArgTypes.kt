package com.davidcobbina.disneyplus.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.google.gson.Gson


abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}

class MovieArgType : JsonNavType<Movie>() {
    override fun fromJsonParse(value: String): Movie = Gson().fromJson(value, Movie::class.java)
    override fun Movie.getJsonParse(): String = Gson().toJson(this)
}

class MenuArgType : JsonNavType<Menu>() {
    override fun fromJsonParse(value: String): Menu = Gson().fromJson(value, Menu::class.java)
    override fun Menu.getJsonParse(): String = Gson().toJson(this)
}

class UserProfileArgType : JsonNavType<UserProfile>() {
    override fun fromJsonParse(value: String): UserProfile = Gson().fromJson(value, UserProfile::class.java)
    override fun UserProfile.getJsonParse(): String = Gson().toJson(this)
}
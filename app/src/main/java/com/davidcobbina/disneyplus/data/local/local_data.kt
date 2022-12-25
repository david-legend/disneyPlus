package com.davidcobbina.disneyplus.data.local

import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.AvatarCategory
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.data.local.model.UserProfile

const val EVERYTHING = "EveryThing"
const val MOVIES = "Movies"
const val SHOWS = "Shows"
const val WATCHLIST = "WatchList"
const val DOWNLOADS = "Downloads"
const val MARVEL = "Marvel"
const val DISNEY = "Disney"
const val PIXAR = "Pixar"
const val NATIONAL_GEOGRAPHIC = "National Geographic"
const val STAR_WARS = "Star Wars"

const val TRENDING_API_ID = "8230787"
const val STAR_WARS_API_ID = "8230783"
const val MARVEL_API_ID = "8230785"
const val DISNEY_1_API_ID = "8230785"
const val DISNEY_2_API_ID = "8230785"
const val PIXAR_API_ID = "8230788"
const val NATIONAL_GEOGRAPHIC_API_ID = "8230790"
const val SHOWS_API_ID = "8230781"
const val MOVIES_API_ID = "8230779"

fun getMenuItemsData(): List<Menu> {
    return arrayListOf(
        Menu(EVERYTHING, R.drawable.ic_layers, true, apiId = DISNEY_1_API_ID),
        Menu(MOVIES, R.drawable.ic_film, apiId = SHOWS_API_ID),
        Menu(SHOWS, R.drawable.ic_monitor, apiId = MOVIES_API_ID),
        Menu(WATCHLIST, R.drawable.ic_check),
        Menu(DOWNLOADS, R.drawable.ic_arrow_downward),
    )
}


fun getStudiosData(): List<Menu> {
    return arrayListOf(
        Menu(
            STAR_WARS,
            R.drawable.star_wars,
            color = Color.White,
            isBrand = true,
            apiId = STAR_WARS_API_ID
        ),
        Menu(MARVEL, R.drawable.marvel_logo, isBrand = true, apiId = MARVEL_API_ID),
        Menu(DISNEY, R.drawable.walt_disney, isBrand = true, apiId = DISNEY_2_API_ID),
        Menu(PIXAR, R.drawable.pixar, color = Color.White, isBrand = true, apiId = PIXAR_API_ID),
        Menu(
            NATIONAL_GEOGRAPHIC,
            R.drawable.national_geograhic,
            isBrand = true,
            apiId = NATIONAL_GEOGRAPHIC_API_ID
        ),
    )
}

val classicAvatars = arrayListOf(
    AvatarProfile(1, R.drawable.merida),
    AvatarProfile(2, R.drawable.moana),
    AvatarProfile(3, R.drawable.olaf),
    AvatarProfile(4, R.drawable.mr_incredible),
    AvatarProfile(5, R.drawable.mushu),
    AvatarProfile(6, R.drawable.simba),
)
val disneyAvatars = arrayListOf(
    AvatarProfile(7, R.drawable.merida),
    AvatarProfile(8, R.drawable.moana),
    AvatarProfile(9, R.drawable.olaf),
    AvatarProfile(10, R.drawable.mr_incredible),
    AvatarProfile(11, R.drawable.mushu),
    AvatarProfile(12, R.drawable.simba),
)

//val avatarCategories = arrayListOf(
//    AvatarCategory(R.string.princess, isSelected = true),
//    AvatarCategory(R.string.hero),
//    AvatarCategory(R.string.villain),
//    AvatarCategory(R.string.buddy),
//)

const val DISNEY_AVATAR_CATEGORY = "Disney"
const val CLASSICS_AVATAR_CATEGORY = "The Classics"
fun getProfilesData(): Map<String, List<AvatarProfile>> {
    return mapOf<String, List<AvatarProfile>>(
        CLASSICS_AVATAR_CATEGORY to classicAvatars,
        DISNEY_AVATAR_CATEGORY to disneyAvatars
    )

}

fun getDisneyProfilesData(): List<AvatarProfile> {
    return disneyAvatars
}

fun getDefaultUserProfileData(): UserProfile {
    return UserProfile(avatar = R.drawable.mr_incredible, username = "")
}

//fun getAvatarCategoriesData(): List<AvatarCategory> {
//    return avatarCategories
//}
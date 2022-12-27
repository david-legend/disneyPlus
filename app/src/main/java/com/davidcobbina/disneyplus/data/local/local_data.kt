package com.davidcobbina.disneyplus.data.local

import androidx.compose.ui.graphics.Color
import com.davidcobbina.disneyplus.R
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

val princessAvatars = arrayListOf(
    AvatarProfile(1, R.drawable.merida),
    AvatarProfile(2, R.drawable.elsa),
    AvatarProfile(3, R.drawable.moana),
    AvatarProfile(4, R.drawable.jasmine),
    AvatarProfile(5, R.drawable.mulan),
    AvatarProfile(6, R.drawable.pocahontas),
    AvatarProfile(7, R.drawable.ariel),
    AvatarProfile(8, R.drawable.aurora),
    AvatarProfile(9, R.drawable.belle),
    AvatarProfile(10, R.drawable.anna),
    AvatarProfile(11, R.drawable.cinderella),
    AvatarProfile(12, R.drawable.rapunzel),
    AvatarProfile(13, R.drawable.tiana),
    AvatarProfile(14, R.drawable.snowwhite),
)
val heroAvatars = arrayListOf(
    AvatarProfile(1, R.drawable.allladin),
    AvatarProfile(2, R.drawable.jack_skellington),
    AvatarProfile(3, R.drawable.beast),
    AvatarProfile(4, R.drawable.sulley),
    AvatarProfile(5, R.drawable.hercules),
    AvatarProfile(6, R.drawable.iron_man),
    AvatarProfile(7, R.drawable.buzz),
    AvatarProfile(8, R.drawable.black_widow),
    AvatarProfile(8, R.drawable.venom),
    AvatarProfile(9, R.drawable.captain_america),
    AvatarProfile(10, R.drawable.captain_marvel),
    AvatarProfile(11, R.drawable.han_solo),
    AvatarProfile(12, R.drawable.spiderman),
    AvatarProfile(13, R.drawable.thor),
)
val villainAvatars = arrayListOf(
    AvatarProfile(1, R.drawable.darth_vader),
    AvatarProfile(2, R.drawable.hades),
    AvatarProfile(3, R.drawable.jafar),
    AvatarProfile(4, R.drawable.scar),
    AvatarProfile(5, R.drawable.hyena),
    AvatarProfile(6, R.drawable.ursula),
    AvatarProfile(7, R.drawable.stormtrooper),
    AvatarProfile(8, R.drawable.thanos),
    AvatarProfile(9, R.drawable.evil_queen),
    AvatarProfile(10, R.drawable.dalmations_101),
    AvatarProfile(11, R.drawable.cheshire_cat),
    AvatarProfile(12, R.drawable.thanos_c),
)
val buddyAvatars =  arrayListOf(
    AvatarProfile(1, R.drawable.mike),
    AvatarProfile(2, R.drawable.mirabel),
    AvatarProfile(3, R.drawable.olaf),
    AvatarProfile(4, R.drawable.stitch),
    AvatarProfile(5, R.drawable.yoda),
    AvatarProfile(6, R.drawable.chip),
    AvatarProfile(7, R.drawable.eeyore),
    AvatarProfile(8, R.drawable.forky),
    AvatarProfile(9, R.drawable.olaf),
    AvatarProfile(10, R.drawable.r2d2),
    AvatarProfile(11, R.drawable.walle_e),
    AvatarProfile(12, R.drawable.the_child),
)



const val PRINCESS_AVATAR_CATEGORY = "Princess"
const val HERO_AVATAR_CATEGORY = "Hero"
const val VILLAIN_AVATAR_CATEGORY = "Villain"
const val BUDDY_AVATAR_CATEGORY = "Buddy"

fun getProfilesData(): Map<String, List<AvatarProfile>> {
    return mapOf<String, List<AvatarProfile>>(
        PRINCESS_AVATAR_CATEGORY to princessAvatars,
        HERO_AVATAR_CATEGORY to heroAvatars,
        VILLAIN_AVATAR_CATEGORY to villainAvatars,
        BUDDY_AVATAR_CATEGORY to buddyAvatars
    )

}

fun getDisneyProfilesData(): List<AvatarProfile> {
    return heroAvatars
}

fun getDefaultUserProfileData(): UserProfile {
    return UserProfile(avatar = R.drawable.mulan, username = "")
}

package com.davidcobbina.disneyplus.data.repositories

import com.davidcobbina.disneyplus.data.local.*
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import javax.inject.Inject

class LocalResourcesRepository @Inject constructor() {

    fun getMenuItems(): List<Menu> {
        return getMenuItemsData()
    }

    fun getStudios(): List<Menu> {
        return getStudiosData()
    }

    fun getProfiles(): Map<String, List<AvatarProfile>> {
        return getProfilesData()
    }

    fun getDisneyProfiles(): List<AvatarProfile> {
        return getDisneyProfilesData()
    }

    fun getDefaultUserProfile(): UserProfile {
        return getDefaultUserProfileData()
    }

}
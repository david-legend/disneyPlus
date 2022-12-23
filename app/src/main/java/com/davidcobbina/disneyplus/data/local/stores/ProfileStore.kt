package com.davidcobbina.disneyplus.data.local.stores

import com.davidcobbina.disneyplus.data.local.AppDatabase
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileStore @Inject constructor(
    database: AppDatabase
) {
    private val profiles = database.profiles

     fun allProfiles(): Flow<List<UserProfile>> {
        return getProfiles { profiles.queryAll() }
    }

    suspend fun insertProfile(userProfile: UserProfile) = profiles.insert(userProfile.fromProfile())

    suspend fun getProfile(id: Int): UserProfile = profiles.getProfile(id).toUserProfile()


    private fun getProfiles(
        block: () -> Flow<List<Profile>>,
    ): Flow<List<UserProfile>> {
        return block().map { it.map { profile -> profile.toUserProfile() } }
    }

}


private fun Profile.toUserProfile() = UserProfile(
    id = id,
    avatar = avatar,
    username = username
)

private fun UserProfile.fromProfile() = Profile(
    id = id,
    avatar = avatar,
    username = username
)
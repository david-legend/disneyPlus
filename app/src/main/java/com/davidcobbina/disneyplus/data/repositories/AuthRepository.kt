package com.davidcobbina.disneyplus.data.repositories

import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.data.local.stores.ProfileStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val profileStore: ProfileStore,
    val localResourcesRepository: LocalResourcesRepository,
) {

    fun getAllProfiles(): Flow<List<UserProfile>> {
        return profileStore.allProfiles()
    }

    suspend fun insertProfile(userProfile: UserProfile) = profileStore.insertProfile(userProfile)


    suspend fun getUserProfile(id: Int): UserProfile {
        val result = if (id == -1) {
            localResourcesRepository.getDefaultUserProfile()
        } else {
            profileStore.getProfile(id)
        }
        return result
    }

}
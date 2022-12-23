package com.davidcobbina.disneyplus.ui.screens.select_account_sreen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.DisneyUser
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.data.local.stores.Profile
import com.davidcobbina.disneyplus.data.remote.model.Movie
import com.davidcobbina.disneyplus.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class SelectAccountState(
    val userAccounts: List<Profile> = arrayListOf(
        Profile(1, R.drawable.merida, "Merida"),
        Profile(1, R.drawable.moana, "Moana"),
        Profile(1, R.drawable.olaf, "Olaf"),
        Profile(1, R.drawable.olaf, "Olaf"),
        Profile(1, R.drawable.olaf, "Olaf"),
    )
)

@HiltViewModel
class SelectAccountViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    var data by mutableStateOf(SelectAccountState())

    private val _profileLoading = MutableStateFlow(false)
    val profileLoading: StateFlow<Boolean>
        get() = _profileLoading

    private val _profiles = MutableStateFlow(emptyList<UserProfile>())
    val profiles: StateFlow<List<UserProfile>>
        get() = _profiles


    fun loadProfiles() {
        viewModelScope.launch {
            _profileLoading.value = true
            authRepository.getAllProfiles().collect { response ->
                _profiles.value = response
            }
            _profileLoading.value = false
        }
    }


}
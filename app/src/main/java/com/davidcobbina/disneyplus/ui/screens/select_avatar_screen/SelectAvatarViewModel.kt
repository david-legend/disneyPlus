package com.davidcobbina.disneyplus.ui.screens.select_avatar_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.local.model.AvatarProfile
import com.davidcobbina.disneyplus.data.repositories.LocalResourcesRepository
import com.davidcobbina.disneyplus.navigation.SELECT_AVATAR_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SelectAvatarViewModel @Inject constructor(
    private val localResourcesRepository: LocalResourcesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _previouslySelectedAvatar =
        checkNotNull(savedStateHandle.get<Int>(SELECT_AVATAR_ARGUMENT))
    val previouslySelectedAvatar: Int get() = _previouslySelectedAvatar

    private val selectAvatarEventChannel = Channel<SelectAvatarEvent>()
    val selectAvatarEvent = selectAvatarEventChannel.receiveAsFlow()

    private val _selectedIcon = MutableStateFlow<Int?>(null)
    val selectedIcon: StateFlow<Int?> get() = _selectedIcon

    private val _avatars = MutableStateFlow(emptyMap<String, List<AvatarProfile>>())
    val avatars: StateFlow<Map<String, List<AvatarProfile>>> get() = _avatars

    init {
        viewModelScope.launch {
            _avatars.value = localResourcesRepository.getProfiles()
        }
    }

    fun updateAvatarMap(id: Int) {
        viewModelScope.launch {
            val updatedAvatarList = mutableMapOf<String, List<AvatarProfile>>()
            for (data in _avatars.value) {
                val key = data.key
                val avatarList = arrayListOf<AvatarProfile>()
                for (avatarProfile in data.value) {
                    val item = avatarProfile.copy(isSelected = avatarProfile.id == id)
                    avatarList.add(item)
                    _selectedIcon.value = if (item.isSelected) item.avatar else _selectedIcon.value
                }
                updatedAvatarList[key] = avatarList
            }
            _avatars.value = updatedAvatarList
        }
    }

    fun onNavigateToAddEditProfile(selectedIcon: Int?) = viewModelScope.launch {
        selectAvatarEventChannel.send(
            SelectAvatarEvent.NavigateToAddEditProfile(selectedIcon)
        )
    }

    fun onAvatarSelected(id: Int) = viewModelScope.launch {
        selectAvatarEventChannel.send(
            SelectAvatarEvent.SelectAvatar(id)
        )
    }

    sealed class SelectAvatarEvent {
        data class NavigateToAddEditProfile(val selectedIcon: Int?) : SelectAvatarEvent()
        data class SelectAvatar(val id: Int) : SelectAvatarEvent()
    }
}
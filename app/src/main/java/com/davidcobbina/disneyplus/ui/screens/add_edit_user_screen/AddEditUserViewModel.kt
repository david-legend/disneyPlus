package com.davidcobbina.disneyplus.ui.screens.add_edit_user_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.SettingsListItem
import com.davidcobbina.disneyplus.data.local.model.UserProfile
import com.davidcobbina.disneyplus.data.repositories.AuthRepository
import com.davidcobbina.disneyplus.navigation.ADD_EDIT_ARGUMENT
import com.davidcobbina.disneyplus.navigation.USER_PROFILE_ID_ARGUMENT
import com.davidcobbina.disneyplus.util.validateUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO:: I loose profile state when I type username and navigate to select avatar screen
data class AddEditUserState(
    val settingsList: List<SettingsListItem> = arrayListOf(
        SettingsListItem(
            title = R.string.maturity_rating_title,
            subtitle = R.string.maturity_rating_subtitle,
            leadingIcon = R.drawable.ic_alert_octagon,
            trailingIcon = R.drawable.ic_chevron_right,
        ),
        SettingsListItem(
            title = R.string.display_language_title,
            subtitle = R.string.display_language_subtitle,
            leadingIcon = R.drawable.ic_baseline_language,
            trailingIcon = R.drawable.ic_chevron_right,
        ),
        SettingsListItem(
            title = R.string.audios_and_subtitles_title,
            subtitle = R.string.audios_and_subtitles_subtitle,
            leadingIcon = R.drawable.ic_baseline_subtitles,
            trailingIcon = R.drawable.ic_chevron_right,
        )
    )
)

@HiltViewModel
class AddEditUserViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var data by mutableStateOf(AddEditUserState())

    val isAdd = checkNotNull(savedStateHandle.get<Int>(ADD_EDIT_ARGUMENT)) == 1
    private val profileId = checkNotNull(savedStateHandle.get<Int>(USER_PROFILE_ID_ARGUMENT))

    private val addEditUserEventChannel = Channel<AddEditUserEvent>()
    val addEditUserEvent = addEditUserEventChannel.receiveAsFlow()

    private val _userName = mutableStateOf(TextFieldValue("here"))
    val userName: State<TextFieldValue> get() = _userName

    private val _userProfile =
        MutableStateFlow(UserProfile(avatar = R.drawable.moana))
    val userProfile: StateFlow<UserProfile> get() = _userProfile


    init {
        viewModelScope.launch {
            _userProfile.value = authRepository.getUserProfile(profileId)
            _userName.value = TextFieldValue(_userProfile.value.username)
            Log.i("ADD", _userName.value.text)
        }
    }

    fun updateSelectedAvatar(avatar: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _userProfile.update { it.copy(avatar = avatar) }
        }
    }


    fun saveProfile(profile: UserProfile) {
        if (validateUserName(profile.username)) {
            updateProfile(profile)
            onSaveProfileSuccess()
            onNavigateToSelectAccountScreen()
            return
        }
        onInvalidForm()
    }

    private fun updateProfile(profile: UserProfile) {
        viewModelScope.launch {
            authRepository.insertProfile(profile)
        }
    }

    fun updateUserName(username: TextFieldValue) {
        viewModelScope.launch(Dispatchers.IO) {
            _userProfile.update { it.copy(username = username.text) }
            _userName.value = username
        }
    }


    fun onNavigateToSelectAvatarScreen() = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.NavigateToSelectAvatarScreen)
    }

    fun onNavigateToSelectAccountScreen() = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.NavigateToSelectAccountScreen)
    }

    fun onAvatarSelected(avatar: Int) = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.UpdateSelectedAvatar(avatar))
    }

    fun onSaveUserProfile() = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.SaveUserProfile)
    }

    fun onUserNameChange(name: TextFieldValue) = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.UserNameChanged(name))
    }


    private fun onInvalidForm() = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.InValidForm)
    }

    private fun onSaveProfileSuccess() = viewModelScope.launch {
        addEditUserEventChannel.send(AddEditUserEvent.SavedProfileSuccess)
    }


    sealed class AddEditUserEvent {
        object NavigateToSelectAccountScreen : AddEditUserEvent()
        object NavigateToSelectAvatarScreen : AddEditUserEvent()
        data class UpdateSelectedAvatar(val avatar: Int) : AddEditUserEvent()
        object SaveUserProfile : AddEditUserEvent()
        object SavedProfileSuccess : AddEditUserEvent()
        data class UserNameChanged(val name: TextFieldValue) : AddEditUserEvent()
        object InValidForm : AddEditUserEvent()
    }
}
package com.davidcobbina.disneyplus.ui.screens.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.repositories.UserPreferencesRepository
import com.davidcobbina.disneyplus.ui.screens.add_edit_user_screen.AddEditUserViewModel
import com.davidcobbina.disneyplus.ui.screens.select_avatar_screen.SelectAvatarViewModel
import com.davidcobbina.disneyplus.util.validateEmail
import com.davidcobbina.disneyplus.util.validateUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _logUserInEventChannel = Channel<LogUserInEvent>()
    val logUserInEvent = _logUserInEventChannel.receiveAsFlow()

    private val _email = mutableStateOf("")
    val email : State<String> get() = _email

    fun updateEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _email.value = email
        }
    }
    fun saveEmail(email: String) {
        if (validateEmail(email)) {
            saveEmailToDataStore(email)
            onSaveEmailSuccess(true)
            onNavigateToSelectAccountScreen()
            return
        }
        onSaveEmailSuccess(false)
    }

    private fun saveEmailToDataStore(email: String) {
        viewModelScope.launch {
            userPreferencesRepository.saveEmail(email)
        }
    }

    fun onEmailChanged(email: String) = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.EmailChanged(email))
    }

    private fun onSaveEmailSuccess(status: Boolean) = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.SavedEmailSuccess(status))
    }

    fun onSaveEmail(email: String) = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.SaveUserEmail(email))
    }

    private fun onNavigateToSelectAccountScreen() = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.NavigateToSelectAccountScreen)
    }

    sealed class LogUserInEvent {
        data class EmailChanged(val email: String) : LogUserInEvent()
        data class SaveUserEmail(val email: String) : LogUserInEvent()
        data class SavedEmailSuccess(val status: Boolean) : LogUserInEvent()
        object NavigateToSelectAccountScreen : LogUserInEvent()
    }
}
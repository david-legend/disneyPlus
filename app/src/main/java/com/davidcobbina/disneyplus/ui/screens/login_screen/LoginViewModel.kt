package com.davidcobbina.disneyplus.ui.screens.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.repositories.UserPreferencesRepository
import com.davidcobbina.disneyplus.util.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _logUserInEventChannel = Channel<LogUserInEvent>()
    val logUserInEvent = _logUserInEventChannel.receiveAsFlow()

    private val _email = mutableStateOf(TextFieldValue(""))
    val email : State<TextFieldValue> get() = _email

    fun updateEmail(email: TextFieldValue) {
        viewModelScope.launch(Dispatchers.IO) {
            _email.value = email
        }
    }
    fun saveEmail(email: TextFieldValue) {
        if (validateEmail(email.text)) {
            saveEmailToDataStore(email.text)
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

    fun onEmailChanged(email: TextFieldValue) = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.EmailChanged(email))
    }

    private fun onSaveEmailSuccess(status: Boolean) = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.SavedEmailSuccess(status))
    }

    fun onSaveEmail(email: TextFieldValue) = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.SaveUserEmail(email))
    }

    private fun onNavigateToSelectAccountScreen() = viewModelScope.launch {
        _logUserInEventChannel.send(LogUserInEvent.NavigateToSelectAccountScreen)
    }

    sealed class LogUserInEvent {
        data class EmailChanged(val email: TextFieldValue) : LogUserInEvent()
        data class SaveUserEmail(val email: TextFieldValue) : LogUserInEvent()
        data class SavedEmailSuccess(val status: Boolean) : LogUserInEvent()
        object NavigateToSelectAccountScreen : LogUserInEvent()
    }
}
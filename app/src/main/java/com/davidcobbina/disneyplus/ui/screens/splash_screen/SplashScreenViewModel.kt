package com.davidcobbina.disneyplus.ui.screens.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.repositories.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _userEmail = MutableStateFlow("")
    val userEmail : StateFlow<String> get()  =_userEmail

    init {
        viewModelScope.launch {
            _userEmail.value = userPreferencesRepository.getEmail.first()
        }
    }
}
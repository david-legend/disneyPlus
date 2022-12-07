package com.davidcobbina.disneyplus.ui.screens.menu_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.repositories.MenuRepository
import com.davidcobbina.disneyplus.model.Studio
import com.davidcobbina.disneyplus.model.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val menuEventChannel = Channel<MenuEvent>()
    val menuEvent = menuEventChannel.receiveAsFlow()

    private val _menuItems = MutableStateFlow(emptyList<MenuItem>())
    val menuItems: StateFlow<List<MenuItem>> get() = _menuItems

    private val _studios = MutableStateFlow(emptyList<Studio>())
    val studios: StateFlow<List<Studio>> get() = _studios

    init {
        viewModelScope.launch {
            _menuItems.value = menuRepository.getMenuItems()
            _studios.value = menuRepository.getStudios()
        }
    }


    fun updateMenuList(id: String) {
        viewModelScope.launch {
            val updatedMenu = arrayListOf<MenuItem>()
            for (menu in _menuItems.value) {
                val item = menu.copy(isSelected = menu.title.lowercase() == id.lowercase())
                updatedMenu.add(item)
            }
            _menuItems.value = updatedMenu
        }
    }

    fun onMenuItemSelected(id: String) = viewModelScope.launch {
        menuEventChannel.send(
            MenuEvent.NavigateToScreenSelected(id)
        )
    }

    sealed class MenuEvent {
        data class NavigateToScreenSelected(val id: String) : MenuEvent()
    }
}
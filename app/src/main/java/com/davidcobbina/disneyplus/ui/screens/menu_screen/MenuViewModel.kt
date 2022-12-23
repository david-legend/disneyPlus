package com.davidcobbina.disneyplus.ui.screens.menu_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidcobbina.disneyplus.data.repositories.LocalResourcesRepository
import com.davidcobbina.disneyplus.data.local.model.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: LocalResourcesRepository
) : ViewModel() {

    private val menuEventChannel = Channel<MenuEvent>()
    val menuEvent = menuEventChannel.receiveAsFlow()

    private val _menuItems = MutableStateFlow(emptyList<Menu>())
    val menuItems: StateFlow<List<Menu>> get() = _menuItems

    private val _studios = MutableStateFlow(emptyList<Menu>())
    val studios: StateFlow<List<Menu>> get() = _studios

    init {
        viewModelScope.launch {
            _menuItems.value = menuRepository.getMenuItems()
            _studios.value = menuRepository.getStudios()
        }
    }


    fun updateMenuList(id: String) {
        viewModelScope.launch {
            val updatedMenu = arrayListOf<Menu>()
            for (menu in _menuItems.value) {
                val item = menu.copy(isSelected = menu.title.lowercase() == id.lowercase())
                updatedMenu.add(item)
            }
            _menuItems.value = updatedMenu
        }
    }

    fun onMenuItemSelected( menu: Menu) = viewModelScope.launch {
        menuEventChannel.send(
            MenuEvent.NavigateToScreenSelected(menu)
        )
    }

    sealed class MenuEvent {
        data class NavigateToScreenSelected(val menu: Menu) : MenuEvent()
    }
}
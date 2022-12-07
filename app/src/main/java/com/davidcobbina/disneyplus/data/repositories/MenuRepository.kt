package com.davidcobbina.disneyplus.data.repositories

import com.davidcobbina.disneyplus.data.local.getMenuItemsData
import com.davidcobbina.disneyplus.data.local.getStudiosData
import com.davidcobbina.disneyplus.model.MenuItem
import com.davidcobbina.disneyplus.model.Studio
import javax.inject.Inject

class MenuRepository @Inject constructor() {

    fun getMenuItems(): List<MenuItem> {
        return getMenuItemsData()
    }

    fun getStudios(): List<Studio> {
        return getStudiosData()
    }
}
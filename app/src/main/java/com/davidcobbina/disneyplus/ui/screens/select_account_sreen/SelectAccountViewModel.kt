package com.davidcobbina.disneyplus.ui.screens.select_account_sreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.DisneyUser


data class SelectAccountState(
    val userAccounts: List<DisneyUser> = arrayListOf(
        DisneyUser(R.drawable.merida, "Merida"),
        DisneyUser(R.drawable.moana, "Moana"),
        DisneyUser(R.drawable.olaf, "Olaf"),
    )
)

class SelectAccountViewModel() : ViewModel() {
    var data by mutableStateOf(SelectAccountState())
}
package com.davidcobbina.disneyplus.ui.screens.add_edit_user_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.model.ActionList
import com.davidcobbina.disneyplus.model.DisneyMovie
import com.davidcobbina.disneyplus.model.Episode
import com.davidcobbina.disneyplus.model.SettingsListItem

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


class AddEditUserViewModel() : ViewModel() {

    var data by mutableStateOf(AddEditUserState())
}
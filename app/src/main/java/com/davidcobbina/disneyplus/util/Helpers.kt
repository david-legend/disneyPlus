package com.davidcobbina.disneyplus.util

import android.os.Build
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import com.davidcobbina.disneyplus.data.model.Cast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun parseYearFromDate(date: String): String {
    val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.parse(date, DateTimeFormatter.ISO_DATE).year.toString()
    } else {
        date.split("-")[0]
    }
    return result
}

fun covertMinutesToHourMinute(runtime: Int ): String {
    val timeInSeconds = runtime * 60
    val hours = timeInSeconds / 3600
    val minutes = (timeInSeconds - hours * 3600) / 60

    return if (runtime > 60) {
        "${hours}h ${minutes}m"
    } else {
        "${minutes}m"
    }
}

fun extractDataFromArray(key: String, cast: List<Cast>, maxNumberToExtract: Int): List<String> {
    val result = mutableListOf<String>()
    var itemCount = 0
    for (item in cast) {
        if (key == item.knownForDepartment) {
            result.add(item.name)
            itemCount += 1
        }
        if (itemCount == maxNumberToExtract) break
    }
    return result
}

@OptIn(ExperimentalMaterialApi::class)
fun collapseSheet(scope: CoroutineScope, sheetState: BottomSheetState) {
    scope.launch {
        if (sheetState.isExpanded) {
            sheetState.collapse()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun expandSheet(scope: CoroutineScope, sheetState: BottomSheetState) {
    scope.launch {
        if (sheetState.isCollapsed) {
            sheetState.expand()
        }
    }
}
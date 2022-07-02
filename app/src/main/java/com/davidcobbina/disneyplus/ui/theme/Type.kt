package com.davidcobbina.disneyplus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R

val SFPro = FontFamily(
    Font(R.font.sfpro_regular),
    Font(R.font.sfpro_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(fontFamily = SFPro, color = Color.White, fontSize = 57.sp),
    displayMedium = TextStyle(fontFamily = SFPro, color = Color.White, fontSize = 45.sp),
    displaySmall = TextStyle(fontFamily = SFPro, color = Color.White, fontSize = 36.sp),
    headlineLarge = TextStyle(fontFamily = SFPro, color = Color.White, fontSize = 32.sp),
    headlineMedium = TextStyle(
        fontFamily = SFPro,
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineSmall = TextStyle(fontFamily = SFPro, color = Color.White, fontSize = 24.sp),
    titleLarge = TextStyle(
        fontFamily = SFPro,
        fontSize = 22.sp,
        color = blackGrey50,
        fontWeight = FontWeight.W400
    ),
    titleMedium = TextStyle(
        fontFamily = SFPro,
        fontSize = 16.sp,
        color = blackGrey50,

    ),
    titleSmall = TextStyle(
        fontFamily = SFPro,
        fontSize = 14.sp,
        color = blackGrey50,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Normal,
        color = blue,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(fontFamily = SFPro, color = grey200, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = SFPro, color = grey200, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = SFPro, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = SFPro, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = SFPro, fontSize = 11.sp),
)
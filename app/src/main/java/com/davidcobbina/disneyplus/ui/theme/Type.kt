package com.davidcobbina.disneyplus.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.davidcobbina.disneyplus.R

val hankenGrotesk = FontFamily(
    Font(R.font.hanken_grotesk_black, FontWeight.Black),
    Font(R.font.hanken_grotesk_thin, FontWeight.Thin),
    Font(R.font.hanken_grotesk_extra_light, FontWeight.ExtraLight),
    Font(R.font.hanken_grotesk_light, FontWeight.Light),
    Font(R.font.hanken_grotesk_regular, FontWeight.Normal),
    Font(R.font.hanken_grotesk_medium, FontWeight.Medium),
    Font(R.font.hanken_grotesk_bold, FontWeight.Bold),
    Font(R.font.hanken_grotesk_semi_bold, FontWeight.SemiBold),
    Font(R.font.hanken_grotesk_extra_bold, FontWeight.ExtraBold),

)

// Set of Material typography styles to start with

@Composable
fun DisneyPlusTypography() {

}

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = hankenGrotesk, color = Color.White, fontSize = 57.sp),
    displayMedium = TextStyle(fontFamily = hankenGrotesk, color = Color.White, fontSize = 45.sp),
    displaySmall = TextStyle(fontFamily = hankenGrotesk, color = Color.White, fontSize = 36.sp),
    headlineLarge = TextStyle(fontFamily = hankenGrotesk, color = Color.White, fontSize = 32.sp),
    headlineMedium = TextStyle(
        fontFamily = hankenGrotesk,
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineSmall = TextStyle(fontFamily = hankenGrotesk, color = Color.White, fontSize = 24.sp),
    titleLarge = TextStyle(
        fontFamily = hankenGrotesk,
        fontSize = 22.sp,
        color = grey100,
        fontWeight = FontWeight.W600
    ),
    titleMedium = TextStyle(
        fontFamily = hankenGrotesk,
        fontSize = 20.sp,
        color = grey100,
        fontWeight = FontWeight.W400
    ),
    titleSmall = TextStyle(
        fontFamily = hankenGrotesk,
        fontSize = 14.sp,
        color = grey100,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = hankenGrotesk,
        fontWeight = FontWeight.Normal,
        color = grey100,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(fontFamily = hankenGrotesk, color = grey300, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = hankenGrotesk, color = grey300, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = hankenGrotesk, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = hankenGrotesk, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = hankenGrotesk, fontSize = 11.sp),
)
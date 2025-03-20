package com.vee.musicapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vee.musicapp.R

//object AppTypography {
//    val title = TextStyle(
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Bold
//    )

    val CustomFontFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_bold, FontWeight.Bold)
    )
    val AppTypography = Typography(
    displayLarge = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 57.sp
    ),
    displayMedium = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 45.sp
    ),
    displaySmall = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp
    ),
    titleLarge = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 22.sp
    ),
    titleMedium = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
    ),
    titleSmall = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
    ),
    bodySmall = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
    ),
    labelLarge = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
    ),
    labelMedium = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp
    ),
    labelSmall = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp
    )
    )
//}
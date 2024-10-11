package com.example.calculator.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val BlueLight = Color(0xFFDDE9FC)
val BlueDark = Color(0xFF245CEC)
val Blue = Color(0xFF95B6EF)
val BackgroundDark = Color(0xFF10162A)
val BackgroundLight = Color(0xFFFFFFFF)
val LightGrey = Color(0xFFBFBCBC)
var DarkGrey = Color(0xFF5F5E5E)
val RedLight = Color(0xFFE86B54)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Transparent = Color(0x00000000)

val BlueOperatorsDark = Color(0xFF27386C)
val BlueModeLight = Color(0xFF1B1D4E)
val BlueNumberDark = Color(0xFF121F40)

object ColorApp {
    var buttonNumber = BlueLight
    var buttonEgual = BlueDark
    var buttonOperators = Blue
    var white = White
    var black = Black
    var background = BackgroundDark
    var backgroundLight = BackgroundLight
    var lightGrey = LightGrey
    var transparent = Transparent
    var redLight = RedLight
    var darkGrey = DarkGrey

    fun updateColorsForTheme(darkTheme: Boolean) {
        if (darkTheme) {
            buttonNumber = BlueNumberDark
            buttonEgual = BlueModeLight
            buttonOperators = BlueOperatorsDark
            background = BackgroundDark
            white = White
            black = Black
            transparent = Transparent
        } else {
            buttonNumber = BlueLight
            buttonEgual = BlueDark
            buttonOperators = Blue
            background = BackgroundLight
            lightGrey = LightGrey
            redLight = RedLight
            white = White
            black = Black
            transparent = Transparent
        }
    }
}
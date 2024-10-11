package com.example.calculator.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.CalculatorController
import com.example.calculator.R
import com.example.calculator.ui.ButtonApp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.ColorApp

@Composable
fun CalculatorApp() {
    val controller = remember { CalculatorController() }
    var isDarkTheme by remember { mutableStateOf(false) }

    CalculatorTheme(darkTheme = isDarkTheme) {
        val backgroundColor = if (isDarkTheme) ColorApp.background else ColorApp.backgroundLight

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = controller.input,
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 36.sp),
                    modifier = Modifier.fillMaxWidth(),
                    color = if (isDarkTheme) ColorApp.white else ColorApp.black
                )
                Text(
                    text = controller.result,
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 48.sp),
                    modifier = Modifier.fillMaxWidth(),
                    color = if (isDarkTheme) ColorApp.darkGrey else ColorApp.lightGrey
                )
                Spacer(modifier = Modifier.weight(1f))
                CalculatorButtons(controller, isDarkTheme)
            }
            IconButton(
                onClick = { isDarkTheme = !isDarkTheme },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                val icon = if (isDarkTheme) R.drawable.dark_mode else R.drawable.light_mode
                val iconColor = if (isDarkTheme) ColorApp.white else ColorApp.black
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(iconColor)
                )
            }
        }
    }
}

@Composable
fun CalculatorButtons(controller: CalculatorController, isDarkTheme: Boolean) {
    val buttons = listOf(
        "C", "DEL", "%", "/",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "( )", "0", ".", "="
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        for (i in buttons.indices step 4) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (j in 0 until 4) {
                    val button = buttons[i + j]
                    if (button.isNotEmpty()) {
                        val buttonModifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                        val backgroundColor = when (button) {
                            "=" -> ColorApp.buttonEgual
                            "DEL" -> ColorApp.redLight
                            "+", "-", "x", "/", "%", ".", "( )" -> ColorApp.buttonOperators
                            else -> ColorApp.buttonNumber
                        }
                        val textFontSize = when (button) {
                            "DEL" -> 15.sp
                            else -> 30.sp
                        }
                        val textColor = when (button) {
                            "C" -> ColorApp.redLight
                            "DEL" -> ColorApp.white
                            else -> if (isDarkTheme) ColorApp.white else ColorApp.black
                        }
                        ButtonApp(
                            label = button,
                            onClick = { controller.onButtonClick(button) },
                            modifier = buttonModifier,
                            backgroundColor = backgroundColor,
                            textColor = textColor,
                            textSize = textFontSize
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}
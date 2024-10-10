package com.example.calculator.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.CalculatorController
import com.example.calculator.ui.ButtonApp
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorApp() {
    val controller = remember { CalculatorController() }

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
            color = Color.Black
        )
        Text(
            text = controller.result,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 48.sp),
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray
            
        )
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.weight(1f))
        CalculatorButtons(controller)
    }
}

@Composable
fun CalculatorButtons(controller: CalculatorController) {
    val buttons = listOf(
        "C", "( )", "%", "/",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        ",", "0", " ", "="
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
                            "=" -> Color.Red
                            "C" -> Color.Gray
                            else -> Color.Blue
                        }
                        val textColor = if (button == "C") Color.Red else Color.Black
                        ButtonApp(
                            label = button,
                            onClick = { controller.onButtonClick(button) },
                            modifier = buttonModifier,
                            backgroundColor = backgroundColor,
                            textColor = textColor
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
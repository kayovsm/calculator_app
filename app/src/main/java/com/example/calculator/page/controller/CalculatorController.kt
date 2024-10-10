package com.example.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CalculatorController {
    var input by mutableStateOf("")
    var result by mutableStateOf("")

    fun onButtonClick(value: String) {
        when (value) {
            "=" -> {
                result = try {
                    formatResult(calc(input))
                } catch (e: Exception) {
                    "Error: ${e.message}"
                }
            }
            "C" -> {
                input = ""
                result = ""
            }
            else -> {
                input += value
            }
        }
    }

    private fun calc(expression: String): Double {
        val tokens = expression.split("(?<=[-+x/])|(?=[-+x/])".toRegex()).map { it.trim() }
        val stack = mutableListOf<Double>()

        var i = 0
        while (i < tokens.size) {
            val token = tokens[i]
            when (token) {
                "+" -> stack.add(stack.removeAt(stack.size - 1) + tokens[++i].toDouble())
                "-" -> stack.add(stack.removeAt(stack.size - 1) - tokens[++i].toDouble())
                "x" -> stack.add(stack.removeAt(stack.size - 1) * tokens[++i].toDouble())
                "/" -> stack.add(stack.removeAt(stack.size - 1) / tokens[++i].toDouble())
                else -> stack.add(token.toDouble())
            }
            i++
        }

        return stack[0]
    }

    private fun formatResult(value: Double): String {
        return if (value == value.toInt().toDouble()) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }
}
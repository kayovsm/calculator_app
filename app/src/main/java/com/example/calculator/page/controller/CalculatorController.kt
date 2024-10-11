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
                    formatResult(CalculatorUtils.calculate(input))
                } catch (e: Exception) {
                    "ERROR onButtonClick: ${e.message}"
                }
            }
            "C" -> {
                input = ""
                result = ""
            }
            "DEL" -> {
                input = input.dropLast(1)
                result = ""
            }
            "( )" -> {
                input += CalculatorUtils.nextParenthesis(input)
            }
            else -> {
                input += value
            }
        }
    }

    private fun formatResult(value: Double): String {
        return if (value == value.toInt().toDouble()) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }
}

object CalculatorUtils {
    private val OPERATORS = listOf("+", "-", "x", "/", "%")

    fun calculate(expression: String): Double {
        val tokens = expression.split("(?<=[-+x/%()])|(?=[-+x/%()])".toRegex()).map { it.trim() }.filter { it.isNotEmpty() }
        val values = mutableListOf<Double>()
        val ops = mutableListOf<Char>()

        var i = 0
        while (i < tokens.size) {
            val token = tokens[i]
            when {
                isDouble(token) -> values.add(token.toDouble())
                token == "(" -> ops.add('(')
                token == ")" -> {
                    while (ops.isNotEmpty() && ops.last() != '(') {
                        applyOperation(values, ops)
                    }
                    ops.removeAt(ops.size - 1)
                }
                isOperator(token) -> {
                    while (ops.isNotEmpty() && hasPrecedence(token[0], ops.last())) {
                        applyOperation(values, ops)
                    }
                    ops.add(token[0])
                }
            }
            i++
        }

        while (ops.isNotEmpty()) {
            applyOperation(values, ops)
        }

        return values[0]
    }

    fun nextParenthesis(input: String): String {
        return if (input.count { it == '(' } > input.count { it == ')' }) ")" else "("
    }

    private fun applyOperation(values: MutableList<Double>, ops: MutableList<Char>) {
        val operator = ops.removeAt(ops.size - 1)
        val b = values.removeAt(values.size - 1)
        val a = values.removeAt(values.size - 1)
        values.add(operations(operator, b, a))
    }

    private fun operations(operator: Char, b: Double, a: Double): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            'x' -> a * b
            '/' -> a / b
            '%' -> a * b / 100
            else -> throw UnsupportedOperationException("ERROR: $operator")
        }
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == 'x' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) return false
        return true
    }

    private fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }

    private fun isOperator(value: String): Boolean {
        return value in OPERATORS
    }
}
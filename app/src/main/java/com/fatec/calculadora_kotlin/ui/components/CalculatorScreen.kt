package com.fatec.calculadora_kotlin.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorScreen() {
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            CalculatorButton(label = "+") {
                result = calculate(num1, num2, "+")
            }
            CalculatorButton(label = "-") {
                result = calculate(num1, num2, "-")
            }
        }

        Row {
            CalculatorButton(label = "*") {
                result = calculate(num1, num2, "*")
            }
            CalculatorButton(label = "/") {
                result = calculate(num1, num2, "/")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Resultado: $result")
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(label)
    }
}

fun calculate(num1: String, num2: String, operation: String): String {
    val number1 = num1.toDoubleOrNull() ?: return "Erro"
    val number2 = num2.toDoubleOrNull() ?: return "Erro"

    return when (operation) {
        "+" -> (number1 + number2).toString()
        "-" -> (number1 - number2).toString()
        "*" -> (number1 * number2).toString()
        "/" -> if (number2 != 0.0) (number1 / number2).toString() else "Erro: Divisão por zero"
        else -> "Erro"
    }
}

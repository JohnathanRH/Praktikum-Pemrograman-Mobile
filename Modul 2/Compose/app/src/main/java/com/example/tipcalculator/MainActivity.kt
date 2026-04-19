package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenuBoxScope.menuAnchor
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    var bill : Float? by remember { mutableStateOf(0f) }
    var rate by remember { mutableStateOf(0f) }
    var result by remember { mutableFloatStateOf(0f) }
    var wantsRound by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BillField(
            bill = bill,
            onValueChanged = {
                bill = it.toFloatOrNull()
                result = bill?.times(rate) ?: 0f
                if (wantsRound){ result = result.roundToInt().toFloat() }
            }
        )
        TipRateDropdown(
            options = arrayOf(
                0.15f,
                0.18f,
                0.2f
            ),
            onOptionSelected = {
                rate = it
                result = bill?.times(rate) ?: 0f
                if (wantsRound){ result = result.roundToInt().toFloat() }
            }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            Text("Round up tip?")
            Switch(
                checked = wantsRound,
                onCheckedChange = { wantsRound = !wantsRound }
            )
        }

        Row(){
            Text(
                text = "Tip Amount: $$result",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        App()
    }
}

@Composable
fun BillField(bill : Float?, onValueChanged : (String) -> Unit)
{
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = bill?.toString() ?: "",
        onValueChange = onValueChanged,
        label = { Text("Jumlah bill") },
        leadingIcon = { Icon(Icons.Default.Payments, contentDescription = "Icon") },
        colors = TextFieldDefaults.colors(

        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipRateDropdown(options : Array<Float>, onOptionSelected : (Float) -> Unit)
{
    var opened by remember { mutableStateOf(false) }
    var txt by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        modifier = Modifier.padding(top = 25.dp),
        expanded = opened,
        onExpandedChange = { opened = !opened }
    ) {
        TextField(
            value = txt,
            onValueChange = {},
            readOnly = true,
            label = { Text("Tip Percentage") },
            leadingIcon = { Icon(Icons.Filled.Percent, contentDescription = "IconToo") },
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable, enabled = true)
        )

        ExposedDropdownMenu(
            expanded = opened,
            onDismissRequest = { opened = false }
        ) {
            options.forEach {
                val theText : String = (it * 100).roundToInt().toString()

                DropdownMenuItem(
                    text = { Text("$theText%") },
                    onClick = {
                        txt = "$theText%"
                        onOptionSelected(it)
                        opened = false
                    }
                )
            }
        }
    }
}
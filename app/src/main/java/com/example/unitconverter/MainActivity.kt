package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter();
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
    fun UnitConverter(){

    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnity by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember {
        mutableStateOf(1.0)
    }
    val oConversionFactor = remember {
        mutableStateOf(1.0)
    }


    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )

    fun convertUnitis(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value *100 / oConversionFactor.value).roundToInt() / 100.0;
        outputValue = result.toString();
    }

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "Unity Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnitis()
        },
        label = {Text(text = "Enter Value")}
        )
        Spacer(modifier = Modifier.height(32.dp))
            Row {
                //val context = LocalContext.current;
                Box{
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                        DropdownMenuItem(text = {
                            Text(text = "Centimenters")
                        }, onClick = {
                            oExpanded = false;
                            inputUnit = "Centimenters"
                            conversionFactor.value = 0.01
                            convertUnitis();
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Meters")
                        }, onClick = {
                            oExpanded = false;
                            inputUnit = "Meters"
                            conversionFactor.value = 1.00
                            convertUnitis();
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Feet")
                        }, onClick = {
                            oExpanded = false;
                            inputUnit = "Meters"
                            conversionFactor.value = 0.3048
                            convertUnitis();
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Milimeters")
                        }, onClick = {
                            oExpanded = false;
                            inputUnit = "Meters"
                            conversionFactor.value = 0.001
                            convertUnitis();
                        })
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box{
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputUnity)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text = {
                            Text(text = "Centimenters")
                        }, onClick = {
                            oExpanded = false;
                            outputUnity = "Centimenters"
                            conversionFactor.value = 0.01
                            convertUnitis()
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Meters")
                        }, onClick = {
                            oExpanded = false;
                            outputUnity = "Meters"
                            conversionFactor.value = 1.0
                            convertUnitis()
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Feet")
                        }, onClick = {
                            oExpanded = false;
                            outputUnity = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnitis()
                        })
                        DropdownMenuItem(text = {
                            Text(text = "Milimeters")
                        }, onClick = {
                            oExpanded = false;
                            outputUnity = "Milimeters"
                            conversionFactor.value = 0.001
                            convertUnitis()
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Result: $outputValue $outputUnity")
        }
    }

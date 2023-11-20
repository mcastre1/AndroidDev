package com.example.imperialcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imperialcalculator.ui.theme.ImperialCalculatorTheme
import com.fathzer.soft.javaluator.DoubleEvaluator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImperialCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

val operators = listOf<String>("-", "+", "%", "x")

@Composable
fun MyApp(){
    var userInputOutput by remember {mutableStateOf("")}
    Column (modifier = Modifier.padding(5.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.primary, CutCornerShape(2.dp))
                .weight(1f)
        ) {
            Text(text = userInputOutput,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.BottomEnd), fontSize = 30.sp)
        }
        Divider(
            thickness = 1.dp, color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, top = 5.dp)
        )
        Surface (modifier = Modifier.weight(3f)){
            UI(updatetext = {userInputOutput = it}, userInputOutput) // Send in lambda function to update text
        }
    }
}

var symbols = ArrayList<String>()
// Function used to check what to do when a certain button is pressed
fun operation(updatetext: (String) -> Unit, symbol: String, text: String){

    if (symbol == "Del"){
        if (symbols.isNotEmpty()){
            symbols.removeLast()
        }
    }else if(symbol == "C"){
        symbols = ArrayList<String>()
    }else if (symbol in operators){
        if (symbols.isNotEmpty()){
            if(symbols.last() !in operators){
                symbols.add(symbol)
            }
        }
        
    }else if (symbol == "="){
        Log.d("Evaluate", "Pressed equal button")
        if(symbols.isNotEmpty()){
            for(i in 0 until symbols.size){
                if("/" in symbols[i]){
                    symbols[i] = "." + DoubleEvaluator().evaluate(symbols[i]).toString().split(".")[1]
                }
            }

            var result = DoubleEvaluator().evaluate(symbols.joinToString(" "))
            Log.d("Evaluated!", result.toString())
        }
    }
    else{
        if ("/" in symbol){
            Log.d("symbols", "found fraction")
            if (symbols.isNotEmpty()){
                if("/" !in symbols.last()){
                    symbols.add(symbol)
                }
            }else{
                symbols.add(symbol)
            }
        }else{
            symbols.add(symbol)
        }

    }

    var str = symbols.joinToString(" ")
    updatetext(str)
}

@Composable
fun UI(updatetext: (String) -> Unit, text : String){

    // Whole UI layout
    Column (modifier = Modifier.padding(5.dp)) {

        // Clear and backspace buttons
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)){
            Button(onClick = { operation(updatetext = updatetext, symbol = "C", text = text) }, modifier = Modifier.weight(2f)) {
                Text(text = "Clear")
            }
            Button(onClick = { operation(updatetext = updatetext, symbol = "Del", text = text)}, modifier = Modifier.weight(1f)) {
                Text(text = "Del")
            }
        }

        // Buttons
        Row(modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1.2f)){
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, symbol = "1", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("1")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "2", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("2")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "3", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("3")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, symbol = "4", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("4")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "5", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("5")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "6", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("6")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, symbol = "7", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("7")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "8", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("8")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "9", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("9")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(1.dp), enabled = false) {
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "0", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("0")
                    }
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(1.dp), enabled = false) {
                    }
                }

                Divider(color = Color.Black, thickness = 1.dp)
                Column (modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, symbol = "+", text = text)}, modifier = Modifier.fillMaxWidth()) {
                        Text("+")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "-", text = text)}, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "-")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "x", text = text)}, modifier = Modifier.fillMaxWidth()) {
                        Text(text="x")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "%", text = text)}, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "%")
                    }
                    Button(onClick = {operation(updatetext = updatetext, symbol = "=", text = text)},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f), shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(text = "=")
                    }
                }
            }
            Divider(color = Color.Black,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(2.dp)
                    .width(1.dp))
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)){
                Row(modifier = Modifier.fillMaxWidth()){
                    Column (modifier = Modifier.weight(1f)){
                        Button(onClick = {operation(updatetext = updatetext, symbol = "1/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "3/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "5/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "5/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "7/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "7/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "9/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "9/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "11/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "11/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "13/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "13/16")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "15/16", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "15/16")
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(onClick = {operation(updatetext = updatetext, symbol = "1/8", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1/8")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "1/4", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1/4")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "3/8", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3/8")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "1/2", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1/2")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "5/8", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "5/8")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "3/4", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3/4")
                        }
                        Button(onClick = {operation(updatetext = updatetext, symbol = "7/8", text = text)},
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxWidth()) {
                            Text(text = "7/8")
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewMyApp(){
    ImperialCalculatorTheme {
        MyApp()
    }
}
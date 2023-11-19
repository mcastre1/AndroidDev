package com.example.imperialcalculator

import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperialcalculator.ui.theme.ImperialCalculatorTheme

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

val operators = listOf<String>("-", "+", "/", "x")

@Composable
fun MyApp(){
    var userInputOutput by remember {mutableStateOf("")}
    Column (modifier = Modifier.padding(5.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.primary, CutCornerShape(2.dp))
        ) {
            Text(text = userInputOutput, modifier = Modifier.padding(5.dp))
        }
        Divider(
            thickness = 2.dp, color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, top = 5.dp)
        )
        UI(updatetext = {userInputOutput = it}, userInputOutput) // Send in lambda function to update text
    }
}

// Function used to check what to do when a certain button is pressed
fun operation(updatetext: (String) -> Unit, operation: String, text: String){
    if (operation == "Del"){
        updatetext(text.dropLast(1))
    }else if(operation == "C"){
        updatetext("")
    }else if (operation in operators){
        print("operator")
    }
    else{
        updatetext(text + operation)
    }
}

@Composable
fun UI(updatetext: (String) -> Unit, text : String){

    // Whole UI layout
    Column (modifier = Modifier.padding(5.dp)) {

        // Clear and backspace buttons
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)){
            Button(onClick = { operation(updatetext = updatetext, operation = "C", text = text) }, modifier = Modifier.weight(2f)) {
                Text(text = "Clear")
            }
            Button(onClick = { operation(updatetext = updatetext, operation = "Del", text = text)}, modifier = Modifier.weight(1f)) {
                Text(text = "Del")
            }
        }

        // Buttons
        Row(modifier = Modifier.fillMaxWidth()){
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1.5f)){
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, operation = "1", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("1")
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "2", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("2")
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "3", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("3")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, operation = "4", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("4")
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "5", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("5")
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "6", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("6")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = {operation(updatetext = updatetext, operation = "7", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("7")
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "8", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("8")
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "9", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("9")
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(1.dp), enabled = false) {
                    }
                    Button(onClick = {operation(updatetext = updatetext, operation = "0", text = text)}, modifier = Modifier.padding(1.dp)) {
                        Text("0")
                    }
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(1.dp), enabled = false) {
                    }
                }

                Divider(color = Color.Black, thickness = 2.dp)
            }
            Divider(color = Color.Black,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(2.dp)
                    .width(2.dp))
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)){

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
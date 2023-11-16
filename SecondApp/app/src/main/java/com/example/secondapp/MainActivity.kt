package com.example.secondapp

import SampleData
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.secondapp.ui.theme.SecondAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondAppTheme {
                Surface (modifier = Modifier.fillMaxSize()){
                    Conversation(messages = SampleData.conversationSample)
                }
            }
        }
    }
}

// This is something like a dictionary
data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message){ // This is expecting a Message data class
    Row (modifier = Modifier.padding(all = 8.dp)){
        // Ads Image from res/drawable folder,
        // More images can be added by going to the resource manager
        // and importing a drawable
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp)) // Adds a horizontal space between the Image and Column

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        
        Column (
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        ){// This is one of the many layouts in kotlin, used to stack elements
            // inside one above the other.

            Text(text = msg.author, // Referencing author from data class above
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp)) // Adds vertical space between texts

            Surface (
                shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp
            ) {
                Text(text = msg.body, // Referencing body from data class above
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }


}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{
        items(messages) {
            message -> MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    SecondAppTheme {
        Conversation(SampleData.conversationSample)
    }
}

// These attributes allows us to preview the app under dark mode.
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    SecondAppTheme {
        Surface (modifier = Modifier.fillMaxSize()){
            MessageCard(msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!"))
        }
    }
}

@Composable
fun Greetings(name:String){
    Text(text = "Hello, $name!")
}

@Composable
fun PreviewGreetings(){
    Greetings(name = "Miguel")
}



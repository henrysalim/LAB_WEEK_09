package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // here we wrap our content with the theme
            LAB_WEEK_09Theme {
                // a surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // we use MaterialTheme.colorScheme.background to get the background color
                    // and set it as the color of the surface
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = listOf("Tanu", "Tina", "Tono")
                    // here we call the Home composable
                    Home(list)
                }
            }
        }
    }
}

// @Composable is used to tell the compiler that this is a composable function
// It's a way of defining a composable
@Composable
fun Home(
    // here we define a parameter called items
    items: List<String>
) {
    // Here we can use LazyColumn to lazily display a list of items horizontally
    // LazyColumn is more efficient than column
    // because it only composes and lays out the currently visible items much like a RecyclerView
    // You can also use LazyRow to lazily display a list of items horizontally
    LazyColumn {
        // here we use item to display an item inside the LazyColumn
        item {
            Column(
                // Modifier.padding(16.dp) is used to add padding to the Column
                // You can also use Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                // to add padding horizontally and vertically
                // or Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                // to add padding to each side
                modifier = Modifier.padding(16.dp).fillMaxSize(),
                // Alignment.CenterHorizontally is used to align the Column horizontally
                // you can also use verticalAlignment = Arrangement.Center to align the Column vertically
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        id = R.string.enter_item
                    )
                )
                // here we use TextField to display a text input field
                TextField(
                    // set the value of the input field
                    value = "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    // set what happens when the value of the input field changes
                    onValueChange = {}
                )
                // here we use Button to display a button
                // the onClick parameter is used to set what happens when the button is clicked
                Button(onClick = {}) {
                    // set the text of the button
                    Text(
                        text = stringResource(
                            id = R.string.button_click
                        )
                    )
                }
            }
        }
        // here we use items to display a list of items inside the LazyColumn
        // this is the RecyclerView replacement
        items(items) { item ->
            Column(
                modifier = Modifier.padding(vertical = 4.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item)
            }
        }
    }
}

// here we create a preview function of the Home composable
// This function is specifically used to show a preview of the Home composable
// This is only for development purpose
@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home(listOf("Tanu", "Tina", "Tono"))
}
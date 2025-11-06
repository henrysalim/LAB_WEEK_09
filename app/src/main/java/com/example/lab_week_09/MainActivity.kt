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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme
import com.example.lab_week_09.ui.theme.OnBackgroundItemText
import com.example.lab_week_09.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09.ui.theme.PrimaryTextButton

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
                    Home()
                }
            }
        }
    }
}

// declare a data class called Student
data class Student(
    var name: String
)

@Composable
fun Home() {
    // here we create a mutable state list of Student
    // we use remember to make the list remember its value
    // this is so that the list won't be recreated when the composable recomposes
    // we use MutableStateListOf to make the list mutable
    // this is so that we can add or remove items from the list
    // basically this is the same concept as using useState in React

    val listData = remember {
        mutableStateListOf(
            Student("Tanu"),
            Student("Tina"),
            Student("Tono")
        )
    }
    // here we create a mutable state of Student
    // this is so that we can get the value of the input field
    var inputField = remember { mutableStateOf(Student("")) }

    // we call the HomeContent composable
    // here we pass:
    // listData to show the list of items inside the HomeContent
    // inputField to show the input field value inside HomeContent
    // a Lambda function to update the value of the inputField
    // A Lambda function to add the inputField to the listData

    HomeContent(
        listData,
        inputField.value,
        { input -> inputField.value = inputField.value.copy(input) },
        {
            if (inputField.value.name.isNotBlank()) {
                listData.add(inputField.value)
                inputField.value = Student("")
            }
        }
    )
}

// here we create a composable function called HomeContent
// HomeContent is used to display the content of the Home composable
@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
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
                // here we call the OnBackgroundTitleText UI element
                OnBackgroundTitleText(
                    text = stringResource(
                        id = R.string.enter_item
                    )
                )
                // here we use TextField to display a text input field
                TextField(
                    // set the value of the input field
                    value = inputField.name,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    // set what happens when the value of the input field changes
                    onValueChange = {
                        // here we call the onInputValueChange Lambda function
                        // and pass the value of the input field as a parameter
                        // this is so that we can update the value of the inputField
                        onInputValueChange(it)
                    }
                )

                // here we call the PrimaryTextButton UI Element
                PrimaryTextButton(
                    text = stringResource(
                        id = R.string.button_click
                    )
                ) {
                    onButtonClick()
                }
                // here we use Button to display a button
                // the onClick parameter is used to set what happens when the button is clicked
                Button(onClick = {
                    // here we call the onButtonClick Lambda function
                    // this is so that we can add the inputField value to the listData
                    // and reset the value of the inputField
                    onButtonClick()
                }) {
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
        items(listData) { item ->
            Column(
                modifier = Modifier.padding(vertical = 4.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // here we call the OnBackgroundItemText UI Element
                OnBackgroundItemText(text = item.name)
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
    Home()
}
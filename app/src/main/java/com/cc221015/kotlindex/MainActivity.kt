package com.cc221015.kotlindex

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221015.kotlindex.ui.theme.KotlinDexTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinDexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetBackgroundMain()
                    mainScreen(){
                        userName, password->
                        var myIntent = Intent(this, MainActivity2::class.java).apply{
                            putExtra("keyUser", userName)
                            putExtra("keyPassword", password)
                        }
                        startActivity(myIntent)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScreen(onClick: (String,String) -> Unit){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,)
    {
        LoginBox(onClick = onClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBox(onClick: (String,String)->Unit){
    var userName by remember{mutableStateOf(TextFieldValue(""))}
    var password by remember{mutableStateOf(TextFieldValue(""))}
    Text(
        text = "Login",
        modifier = Modifier.padding(10.dp),
        fontSize = 20.sp
    )
    TextField(
        value = userName,
        onValueChange = { new -> userName = new},
        label = { Text(text = stringResource(id = R.string.field_email))}
    )
    TextField(
        value = password,
        onValueChange = { new -> password = new},
        label = { Text(text = stringResource(id = R.string.field_password))}
    )

    Button(
        onClick = {onClick(
            userName.text,
            password.text
        )}
    ){
        Text(text = stringResource(R.string.button_send))
    }
}
@Preview
@Composable
fun SetBackgroundMain() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_image_main),
            contentDescription = "Login_Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
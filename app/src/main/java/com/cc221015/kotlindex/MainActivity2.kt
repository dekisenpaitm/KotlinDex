package com.cc221015.kotlindex

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221015.kotlindex.ui.theme.KotlinDexTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userName = intent.getStringExtra("keyUser")?:"User"
            KotlinDexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetBackground()
                    Navigation()
                    InfoScreen(userName)
                }
            }
        }
    }
}
@Composable
fun SetBackground() {
    Image(
        painter = painterResource(id = R.drawable.background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxHeight()
    )
}
@Composable
fun InfoScreen(userName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Add this for scrolling if needed
    ) {
        TextBox("Welcome Back, $userName", 20, true)
        TextBox(text = "", 5, trans = false)
        TextBox(stringResource(R.string.InfoPageText), 20, true)
    }
}

@Composable
fun TextBox(text: String, size: Int, trans: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (trans) {
                    Color(255, 255, 255, 200)
                } else {
                    Color(0, 0, 0, 0)
                },
                shape = MaterialTheme.shapes.medium
            )
            .padding(10.dp)
    ) {
        Text(
            text = text,
            fontSize = size.sp,
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold (bottomBar = {BottomNavigationBar(navController)}
    ){
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = "first"
        ){
        composable("first"){ Text(text="first")}
        composable("second"){ Text(text="second")}
        }

    }
}

@Composable
fun BottomNavigationBar(navController:NavHostController){
    BottomNavigation {
        NavigationBarItem(selected = true,
            onClick = { navController.navigate("first")},
            label ={ Text(text ="Home")},
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription="") })

        NavigationBarItem(selected = false,
            onClick = {navController.navigate("second")},
            label ={ Text(text ="Second")},
            icon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription="") })
    }
}

sealed class Screen(route: String){
    object First: Screen("first")
    object Second: Screen("second")
}






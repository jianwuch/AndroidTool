package com.jove.androidtool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.jove.androidtool.ui.theme.AndroidToolTheme
import com.jove.netserver.PostAndGetUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidToolTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Button(onClick = {
            PostAndGetUtil.post(
                "www.baidu.com",
                Gson().toJson(mapOf<String, Any>("key1" to 1, "key2" to "2")),
            ){success, msg->{}}
        }) {
            Text(
                text = "Post",
                modifier = modifier,
            )
        }

        Button(onClick = {
            PostAndGetUtil.get(
                "www.baidu.com",
                Gson().toJson(mapOf<String, Any>("key1" to 1, "key2" to "2"))
            ){success, msg->{}}
        }) {
            Text(
                text = "Get",
                modifier = modifier,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidToolTheme {
        Greeting("Android")
    }
}
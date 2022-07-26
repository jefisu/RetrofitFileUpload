package com.jefisu.retrofitfileupload

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<FileViewModel>()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                if (viewModel.text.isNotBlank()) {
                    Text(text = viewModel.text)
                }
                if (viewModel.isLoading) {
                    LinearProgressIndicator()
                }
                Button(
                    onClick = {
                        val file = File(cacheDir, "newPikachu.png")
                        file.createNewFile()
                        file.outputStream().use {
                            assets.open("pikachu.png").copyTo(it)
                        }
                        viewModel.uploadImage(file)
                    },
                    content = {
                        Text(text = "Upload image")
                    }
                )
            }
        }
    }
}
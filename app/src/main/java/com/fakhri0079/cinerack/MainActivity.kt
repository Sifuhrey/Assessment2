package com.fakhri0079.cinerack

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fakhri0079.cinerack.navigation.SetupNavGraph
import com.fakhri0079.cinerack.ui.screen.MainScreen
import com.fakhri0079.cinerack.ui.theme.CineRackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CineRackTheme {
                SetupNavGraph()
            }
        }
    }
}




package com.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge immersive full-screen dark layout
        enableEdgeToEdge()
        setContent {
            DailyLogsApp()
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DailyLogsApp() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                // Set solid black background to eliminate white launch flicker
                setBackgroundColor(android.graphics.Color.BLACK)
                
                // Force link and load navigation to stay in-app
                webViewClient = WebViewClient()
                
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    databaseEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
                
                loadUrl("file:///android_asset/index.html")
            }
        }
    )
}

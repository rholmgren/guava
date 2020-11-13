package com.example.guava

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {
    @Inject lateinit var webViewClientWrapper: WebViewClientWrapper

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        val authorizeUrl = "https://www.strava.com/oath/mobile/authorize"
        val clientId = "1234321"
        val redirectUri = "https://www.yourapp.com"
        val responseType = "code"
        val approvalPrompt = "auto"
        val scope = "activity:write,read"
        val state = "test"

        webViewClientWrapper.constructAuthUrl(
            authorizeUrl = authorizeUrl,
            clientId = clientId,
            redirectUri = redirectUri,
            responseType = responseType,
            approvalPrompt = approvalPrompt,
            scope = scope,
            state = state
        )

        super.onCreate(savedInstanceState, persistentState)
    }
}
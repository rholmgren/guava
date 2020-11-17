package com.example.guava

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var stravaAuthService: StravaAuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Button(onClick = {
                val intentUri = Uri.parse(StravaOAuthConfig.AUTHORIZE_URL)
                    .buildUpon()
                    .appendQueryParameter("client_id", StravaOAuthConfig.CLIENT_ID)
                    .appendQueryParameter("redirect_uri", StravaOAuthConfig.REDIRECT_URI)
                    .appendQueryParameter("response_type", StravaOAuthConfig.RESPONSE_TYPE)
                    .appendQueryParameter("approval_prompt", StravaOAuthConfig.APPROVAL_PROMPT)
                    .appendQueryParameter("scope", StravaOAuthConfig.SCOPE)
                    .build()

                val intent = Intent(Intent.ACTION_VIEW, intentUri)
                startActivity(intent)
            }) { Text("Sign In")}
        }
    }

}
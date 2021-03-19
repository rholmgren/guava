package com.example.guava.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    val intentUri = Uri.parse(OAuthConfig.AUTHORIZE_URL)
                        .buildUpon()
                        .appendQueryParameter("client_id", OAuthConfig.CLIENT_ID)
                        .appendQueryParameter("redirect_uri", OAuthConfig.REDIRECT_URI)
                        .appendQueryParameter("response_type", OAuthConfig.RESPONSE_TYPE)
                        .appendQueryParameter("approval_prompt", OAuthConfig.APPROVAL_PROMPT)
                        .appendQueryParameter("scope", OAuthConfig.SCOPE)
                        .build()

                    val intent = Intent(Intent.ACTION_VIEW, intentUri)
                    startActivity(intent)
                }) {
                    Text("Authorize Strava")
                }
            }
        }
    }
}
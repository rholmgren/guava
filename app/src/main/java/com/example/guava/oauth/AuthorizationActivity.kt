package com.example.guava.oauth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

    //check if strava is already authorized.
    // if so, check if there is an unexpired access token. if not, start webview to authorize strava. then request access token. then store in shared preferences
    // if access token is expired, request a refresh token. then store in shared preferences
    // convert to view actions pattern


}
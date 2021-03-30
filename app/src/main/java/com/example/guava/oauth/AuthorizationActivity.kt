package com.example.guava.oauth

import android.app.PendingIntent
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
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration

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
                    authorizeRequest()
                }) {
                    Text("Authorize Strava")
                }
            }
        }
    }

    private fun authorizeRequest() {
        val serviceConfiguration = AuthorizationServiceConfiguration(
            Uri.parse(OAuthConfig.AUTHORIZE_URL) /* auth endpoint */,
            Uri.parse(OAuthConfig.TOKEN_URL) /* token endpoint */
        )
        val clientId = OAuthConfig.CLIENT_ID
        val redirectUri = Uri.parse("https://guava-mobile.web.app")
        val requestBuilder = AuthorizationRequest.Builder(
            serviceConfiguration,
            clientId,
            AuthorizationRequest.RESPONSE_TYPE_CODE,
            redirectUri
        )

        requestBuilder.setScope(OAuthConfig.SCOPE)
        val request = requestBuilder.build()

        val authorizationService = AuthorizationService(this)

        val action = "com.example.guava.oauth.HANDLE_AUTHORIZATION_RESPONSE"
        val postAuthorizationIntent = Intent(action)
        val pendingIntent = PendingIntent.getActivity(this, request.hashCode(), postAuthorizationIntent, 0)
        authorizationService.performAuthorizationRequest(request, pendingIntent)
    }
}
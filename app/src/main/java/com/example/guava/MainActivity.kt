package com.example.guava

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guava.oauth.OAuthService
import com.example.guava.oauth.OAuthNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var oAuthService: OAuthService

    @Inject
    lateinit var oAuthNavigator: OAuthNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        oAuthNavigator.navigate(this)

//        // create an oauth nav controller that has a tokenRepository injected
//        // - no token: needs to request auth token then redirect to feed
//        // - not the correct scope: go back to strava oauth page
//        // - valid token: go to feed
//
//        // create a workoutFeedActivity
//        // - move logic and compose functions into that
//        //  - move workoutRepository dependency into that
//
//
//        // extract parser to be able to write unit tests
//        val requestParams = intent.data?.query?.split("&")
//        val code = requestParams?.get(0)?.split("=")?.get(1)
//        val scope = requestParams?.get(1)?.split("=")?.get(1)
//
//
//        //if athlete did not give required scope for app
//        //  then show a message and send them back to oauth url
//
//        //otherwise...
//        code?.let { it ->
//            lifecycleScope.launch {
//                val response = oAuthService.requestAccessToken(
//                    clientId = OAuthConfig.CLIENT_ID,
//                    clientSecret = OAuthConfig.CLIENT_SECRET,
//                    grantType = "authorization_code",
//                    code = it
//                )
//                if (response.isSuccessful) {
//                    // store in database
//                    // tokenType
//                    // expiresAt
//                    // refreshToken
//                    // accessToken
//                    // athlete
//                } else {
//                    //show error and go back Authorization Activity
//                }
//            }
//        }
    }
}

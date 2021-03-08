package com.example.guava.oauth

import android.content.Context
import com.example.guava.WorkoutFeedActivity
import javax.inject.Inject


class OAuthNavigator @Inject constructor(
    private val oAuthService: OAuthService
){
    fun navigate(context: Context) {

        // create an oauth nav controller that has a tokenRepository injected
        // - no token: needs to request auth token then redirect to feed
        // - not the correct scope: go back to strava oauth page
        // - valid token: go to feed

        val intent = WorkoutFeedActivity.newIntent(context = context)
        context.startActivity(intent)
    }
}
package com.example.guava

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import javax.inject.Inject

class WebViewClientWrapper @Inject constructor(val stravaAuthService: StravaAuthService): WebViewClient() {

    @Override
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val uri = Uri.parse(request?.url.toString())

        val code = uri.getQueryParameter("code").toString()

        stravaAuthService.postAccessToken("clientId", "clientSecret", code, "authorization_code")
        return super.shouldOverrideUrlLoading(view, request)
    }


     fun constructAuthUrl(
        authorizeUrl: String,
        clientId: String,
        redirectUri: String,
        responseType: String,
        approvalPrompt: String,
        scope: String,
        state: String
    ) : String {

        return "$authorizeUrl?client_id=$clientId&redirect_uri=$redirectUri" +
                "&response_type=$responseType&approval_prompt=$approvalPrompt&scope=$scope&state=$state"
    }

}
package com.example.guava

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import com.example.guava.oauth.OAuthService
import com.example.guava.oauth.OAuthConfig
import com.example.guava.ui.ActivityDetails
import com.example.guava.ui.GuavaTheme
import com.example.guava.ui.ProfileRow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var activityRepository: ActivityRepository

    @Inject
    lateinit var oAuthService: OAuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // extract parser to be able to write unit tests
        val requestParams = intent.data?.query?.split("&")
        val code = requestParams?.get(0)?.split("=")?.get(1)
        val scope = requestParams?.get(1)?.split("=")?.get(1)


        code?.let { it ->
            lifecycleScope.launch {
                val response = oAuthService.requestAccessToken(
                    OAuthConfig.CLIENT_ID,
                    OAuthConfig.CLIENT_SECRET,
                    it,
                    OAuthConfig.AUTHORIZATION_CODE
                )
                if (response.isSuccessful) {
                    // store in shared preferences
                } else {
                    //show error and go back Authorization Activity
                }
            }
        }

        setContent {
            GuavaTheme {
                LazyColumnFor(items = activityRepository.getAllActivities()) {
                    ActivityItem(
                        it.owner,
                        it.activityTime,
                        it.title,
                        it.distance,
                        it.pace,
                        it.totalLength
                    )
                }
            }
        }
    }


    @Composable
    fun ActivityItem(
        owner: String,
        activityTime: String,
        title: String,
        distance: String,
        pace: String,
        time: String
    ) {
        MaterialTheme {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileRow(
                    imageRes = R.drawable.header,
                    titleText = owner,
                    subtitleText = activityTime
                )

                Spacer(modifier = Modifier.preferredHeight(16.dp))

                Text(text = title, style = MaterialTheme.typography.h6)

                Spacer(modifier = Modifier.preferredHeight(16.dp))

                ActivityDetails(distance = distance, pace = pace, time = time)
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ActivityItem(
            "Rachelle Holmgren",
            "Today at 11:34 AM",
            "Hilly Bernal Heights Run",
            "3.05 mi",
            "8:32/mi",
            "25m 22s"
        )
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}

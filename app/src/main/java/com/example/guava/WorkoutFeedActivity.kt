package com.example.guava

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.guava.ui.ActivityDetails
import com.example.guava.ui.GuavaTheme
import com.example.guava.ui.ProfileRow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorkoutFeedActivity : AppCompatActivity() {

    @Inject
    lateinit var workoutRepository: WorkoutRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuavaTheme {
                LazyColumnFor(items = workoutRepository.getAllActivities()) {
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
        fun newIntent(context: Context) = Intent(context, WorkoutFeedActivity::class.java)
    }
}
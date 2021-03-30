package com.example.guava.workout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
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
import com.example.guava.R
import com.example.guava.ui.ActivityDetails
import com.example.guava.ui.GuavaTheme
import com.example.guava.ui.ProfileRow
import com.example.guava.workout.WorkoutFeedViewModel.ViewAction.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutFeedActivity : AppCompatActivity() {
    private val workoutFeedViewModel: WorkoutFeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workoutFeedViewModel.handleAction(LoadActivities)

        val activities = workoutFeedViewModel.viewState.activities.value
        setContent {
            GuavaTheme {
                WorkoutList(workoutList = activities!!)
            }
        }
    }


    @Composable
    private fun WorkoutList(workoutList: List<Activity>) {
        LazyColumnFor(items = workoutList) { item ->
            ActivityItem(
                item.owner,
                item.activityTime,
                item.title,
                item.distance,
                item.pace,
                item.totalLength
            )
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
package com.example.guava

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.guava.MainActivityViewModel.ViewAction
import com.example.guava.workout.WorkoutFeedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestParams = intent.data?.query?.split("&")
        val code = requestParams?.get(1)?.split("=")?.get(1)
        val scope = requestParams?.get(2)?.split("=")?.get(1)

        observeViewEffects(mainActivityViewModel.viewEffects)

        mainActivityViewModel.handleAction(
            ViewAction.InitializeOAuthNavigation(
                code = code!!,
                scope = scope!!
            )
        )
    }

    private fun observeViewEffects(viewEffects: LiveData<LiveEvent<MainActivityViewModel.ViewEffect>>) {
        viewEffects.observe(this@MainActivity, LiveEventObserver { viewEffect ->
            when (viewEffect) {
                is MainActivityViewModel.ViewEffect.OpenWorkoutFeed -> {
                    val intent = Intent(this, WorkoutFeedActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }
}

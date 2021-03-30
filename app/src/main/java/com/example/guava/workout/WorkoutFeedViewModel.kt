package com.example.guava.workout

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guava.NonNullMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutFeedViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
): ViewModel() {

    private val activities = NonNullMutableLiveData<List<Activity>>(emptyList())

    val viewState: ViewState = ViewState(
        activities = activities
    )

    fun handleAction(action: ViewAction) {
        when (action) {
            ViewAction.LoadActivities -> {
                viewModelScope.launch {
                    val activitiesToLoad = workoutRepository.getAllActivities()
                    activities.value = activitiesToLoad
                }
            }
        }
    }

    data class ViewState(
        val activities: LiveData<List<Activity>>
    )

    sealed class ViewAction {
        object LoadActivities : ViewAction()
    }
}
package com.example.guava

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guava.oauth.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _viewEffects = MutableLiveData<ViewEffect>()
    val viewEffects = _viewEffects.toLiveEvents()

    fun handleAction(viewAction: ViewAction) {
        when (viewAction) {
            is ViewAction.InitializeOAuthNavigation -> {
                viewModelScope.launch {
                    tokenRepository.getToken(code = viewAction.code)
                    _viewEffects.value = ViewEffect.OpenWorkoutFeed
                }
            }
        }
    }

    sealed class ViewEffect {
        object OpenWorkoutFeed : ViewEffect()
    }

    sealed class ViewAction {
        data class InitializeOAuthNavigation(val code: String, val scope: String) : ViewAction()
    }
}
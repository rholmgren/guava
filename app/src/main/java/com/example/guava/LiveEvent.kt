package com.example.guava

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations.map

/**
 * ### The problem
 * Using [LiveData] to send "events" can be problematic because events are expected to be handled only once.
 * Livedata retains the last seen value, making it possible to get values that have already been handled.
 *
 * E.g. if an observer of LiveData<DisplayableError> has a risk of displaying its error twice
 * unless it has a way to let the emitter know that the event has been handled.
 *
 * [This article](https://medium.com/google-developers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150)
 * sums up the reasoning pretty well. Credits to the author for providing the convenience classes we use below.
 *
 * ### [LiveEvent]
 * This class is used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
data class LiveEvent<out T>(private val content: T) {
    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun get(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it has already been handled
     */
    fun peek(): T = content
}

/**
 * An [Observer] for [LiveEvent]s, simplifying the pattern of checking if the [LiveEvent]'s content has
 * already been handled.
 *
 * [onUnhandledEventContent] is *only* called if the [LiveEvent]'s contents has not been handled.
 *
 * [Github Source](https://gist.github.com/JoseAlcerreca/e0bba240d9b3cffa258777f12e5c0ae9)
 */
class LiveEventObserver<T>(private val onUnhandledEventContent: (T) -> Unit) : Observer<LiveEvent<T>> {
    override fun onChanged(liveEvent: LiveEvent<T>?) {
        liveEvent?.get()?.let { onUnhandledEventContent(it) }
    }
}

fun <T> LiveData<T>.toLiveEvents(): LiveData<LiveEvent<T>> {
    return map(this) { LiveEvent(it) }
}

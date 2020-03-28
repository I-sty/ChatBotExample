package com.example.chatbot.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        Timber.d("${javaClass.simpleName} onCreated()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStarted() {
        Timber.d("${javaClass.simpleName} onStarted()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopped() {
        Timber.d("${javaClass.simpleName} onStopped()")
    }

    override fun onCleared() {
        Timber.d("${javaClass.simpleName} onCleared()")
    }
}
package com.example.guava

import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T : Any>(initValue: T) : MutableLiveData<T>(initValue) {
    override fun getValue(): T {
        return super.getValue()!!
    }
}
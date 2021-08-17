package com.ch.movie.util

import androidx.lifecycle.MutableLiveData
object ExtensionFunctions {
    fun <T> MutableLiveData<Array<T>>.append(array: Array<T>) {
        this.value = this.value?.plus(array)
    }
}
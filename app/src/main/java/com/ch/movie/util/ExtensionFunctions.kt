package com.ch.movie.util

import androidx.lifecycle.MutableLiveData
public object ExtensionFunctions {
    public fun <T> MutableLiveData<Array<T>>.append(array: Array<T>) {
        this.value = this.value?.plus(array)
    }
}
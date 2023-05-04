package com.example.pocket_rpg.tests_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TestViewModel
@Inject
constructor() : ViewModel() {
    private val _number = MutableStateFlow(1)
    val number = _number.asStateFlow()

    var number2 by mutableStateOf(1)
        private set

    fun inc() {
        _number.value += 1
        number2 += 1
    }
}
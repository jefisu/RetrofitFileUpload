package com.jefisu.retrofitfileupload

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class FileViewModel(
    private val repository: FileRepository = FileRepository()
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var text by mutableStateOf("")
        private set

    fun uploadImage(file: File) {
        isLoading = true
        viewModelScope.launch {
            val result = repository.uploadImage(file)
            delay(300)
            text = result
            isLoading = false
        }
    }
}
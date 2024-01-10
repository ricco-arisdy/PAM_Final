package com.example.pam_final.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_final.data.KontakRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: KontakRepository
) : ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}
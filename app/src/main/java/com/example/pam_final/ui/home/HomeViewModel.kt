package com.example.pam_final.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_final.data.KontakRepository
import com.example.pam_final.model.Kontak
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


sealed class KontakUIState {
    data class Success(val kontak: Flow<List<Kontak>>) : KontakUIState()
    object Error : KontakUIState()
    object Loading : KontakUIState()
}
class HomeViewModel(private val kontakRepository: KontakRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery


    val homeUiState: StateFlow<HomeUiState> = kontakRepository.getAllSiswaStream()
        .filterNotNull()
        .map { kontakList ->
            // Filter siswaList based on the search query
            val filteredList = kontakList.filter { kontak ->
                kontak.nama.contains(_searchQuery.value, ignoreCase = true) ||
                        kontak.alamat.contains(_searchQuery.value, ignoreCase = true) ||
                        kontak.telepon.contains(_searchQuery.value, ignoreCase = true)
            }
            HomeUiState(listKontak = filteredList)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

data class HomeUiState(
    val listKontak: List<Kontak> = listOf()
)
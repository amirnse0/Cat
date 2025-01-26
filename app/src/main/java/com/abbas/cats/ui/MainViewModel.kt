package com.abbas.cats.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abbas.cats.data.model.CatResponse
import com.abbas.cats.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val catsRepository: CatsRepository
): ViewModel() {
    private val _catsDataStateFlow = MutableStateFlow<List<CatResponse>>(emptyList<CatResponse>())
    val catsDataStateFlow = _catsDataStateFlow

    fun getCats() {
        viewModelScope.launch {
            catsRepository.getCats(1,1, true).collect {
                _catsDataStateFlow.emit(it)
            }
        }
    }
}
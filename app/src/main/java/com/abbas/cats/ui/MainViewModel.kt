package com.abbas.cats.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abbas.cats.usecase.GetCatsUseCase
import com.abbas.cats.usecase.Result
import com.abbas.cats.usecase.presentationmodel.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
): ViewModel() {
    companion object RequestConfig {
        const val LIMIT = 20
        const val CONTAINS_BREED = true
    }

    init {
        getCats(1)
    }

    private val _catsDataStateFlow: MutableStateFlow<Result<List<Cat>>> = MutableStateFlow(Result.Loading)
    val catsDataStateFlow = _catsDataStateFlow

    private val _catSelectedItemStateFlow: MutableStateFlow<Cat?> = MutableStateFlow(null)
    val catSelectedItemStateFlow = _catSelectedItemStateFlow

    fun clickOnCatItem(cat: Cat) {
        viewModelScope.launch {
            _catSelectedItemStateFlow.emit(cat)
        }
    }

    fun getCats(page: Int) {
        viewModelScope.launch {
            getCatsUseCase.execute(GetCatsUseCase.Request(
                page = page,
                limit = LIMIT,
                containsBreed = CONTAINS_BREED
            )).collect{
                when(it) {
                    is Result.Success -> {
                        _catsDataStateFlow.emit(Result.Success(it.data.data))
                    }
                    is Result.Error -> {
                        _catsDataStateFlow.emit(Result.Error(it.throwable))
                    }
                    else -> {

                    }
                }
            }
        }
    }
}
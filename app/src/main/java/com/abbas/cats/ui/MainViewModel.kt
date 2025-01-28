package com.abbas.cats.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abbas.cats.usecase.DeleteFavoriteUseCase
import com.abbas.cats.usecase.GetCatsUseCase
import com.abbas.cats.usecase.IsItemFavoriteUseCase
import com.abbas.cats.usecase.Result
import com.abbas.cats.usecase.SelectFavoriteUseCase
import com.abbas.cats.usecase.presentationmodel.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val selectFavoriteUseCase: SelectFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val isItemFavoriteUseCase: IsItemFavoriteUseCase
): ViewModel() {
    companion object RequestConfig {
        const val LIMIT = 10
        const val CONTAINS_BREED = true
    }

    private var page = 0
    val oldData = mutableListOf<Cat>()
        get() = field

    fun isCacheEmpty() = oldData.size == 0

    init {
        getCats()
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

    fun getCats() {
        viewModelScope.launch {
            if (page > 0) {
                _catsDataStateFlow.emit(Result.Loading)
            }
            getCatsUseCase.execute(GetCatsUseCase.Request(
                page = page,
                limit = LIMIT,
                containsBreed = CONTAINS_BREED
            )).collect{
                when(it) {
                    is Result.Success -> {
                        oldData.addAll(it.data.data)
                        delay(500)
                        _catsDataStateFlow.emit(Result.Success(oldData))
                        page++
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

    suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        if (isFavorite) selectFavoriteUseCase.execute(SelectFavoriteUseCase.Request(
            id = id
        ))
        else deleteFavoriteUseCase.execute(DeleteFavoriteUseCase.Request(id))
    }
}
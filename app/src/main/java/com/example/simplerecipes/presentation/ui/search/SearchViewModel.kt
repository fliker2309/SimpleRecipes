package com.example.simplerecipes.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.repository.SearchPagingSource
import com.example.simplerecipes.domain.entity.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 10

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val service: RecipeService
) : ViewModel() {


    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    val recipesFlow = query
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { currentQuery ->
            if (currentQuery.isBlank()) emptyFlow()
            else {
                Pager(
                    config = PagingConfig(DEFAULT_PAGE_SIZE),
                    pagingSourceFactory = { SearchPagingSource(service, currentQuery) }
                ).flow
            }
        }
        .cachedIn(viewModelScope)

    fun search(text: String) {
        _query.value = text
    }

}

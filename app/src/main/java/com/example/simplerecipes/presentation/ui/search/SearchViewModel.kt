package com.example.simplerecipes.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.domain.entity.Recipe
import com.example.simplerecipes.data.repository.SearchPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 10

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val service: RecipeService
) : ViewModel() {

    val recipesFlow: Flow<PagingData<Recipe>>
    var query = ""

    init {
        recipesFlow = Pager(
            config = PagingConfig(DEFAULT_PAGE_SIZE),
            pagingSourceFactory = ::createPagingSource
        ).flow
            .cachedIn(viewModelScope)
    }

    private fun createPagingSource(): SearchPagingSource {
        return SearchPagingSource(service, query)
    }
}

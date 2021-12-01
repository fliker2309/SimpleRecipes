package com.example.simplerecipes.presentation.ui.search

import android.util.Log
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
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 10
private const val TAG = "tag"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val service: RecipeService
) : ViewModel() {

    val recipesFlow: Flow<PagingData<Recipe>>
    var query = ""

    init {
        Log.d(TAG, "SearchVM created")
        recipesFlow = Pager(
            config = PagingConfig(DEFAULT_PAGE_SIZE),
            pagingSourceFactory = ::createPagingSource
        ).flow
            .cachedIn(viewModelScope)
    }

    override fun onCleared() {
        Log.d(TAG, "SearchVM cleared")
        super.onCleared()
    }

    private fun createPagingSource(): SearchPagingSource {
        return SearchPagingSource(service, query)
    }
}

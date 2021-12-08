package com.example.simplerecipes.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.network.dto.toDomainModel
import com.example.simplerecipes.domain.dto.Recipe
import com.example.simplerecipes.utils.Constants
import retrofit2.HttpException

class FilteredRecipesPagingSource(
    private val service: RecipeService,
    private val options: Map<String, String>
) : PagingSource<Int, Recipe>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        try {
            val pageNumber = params.key ?: 1
            val pageSize = Constants.RECIPES_PER_PAGE
            val offset = (pageNumber - 1) * pageSize
            val response = service.searchRecipesByIngredient(
                options = options,
                addRecipeInformation = true,
                number = pageSize,
                offset = offset
            )
            val nextPageNumber = if (response.totalResults - pageSize > offset && offset < 900) {
                pageNumber + 1
            } else {
                null
            }
            return LoadResult.Page(
                data = response.results.map { it.toDomainModel() },
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

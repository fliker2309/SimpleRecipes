package com.example.simplerecipes.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simplerecipes.data.network.RecipeService
import com.example.simplerecipes.data.network.model.toDomainModel
import com.example.simplerecipes.domain.entity.Recipe
import retrofit2.HttpException
import java.io.IOException

private const val RECIPES_PER_PAGE = 10

class SearchPagingSource(
    private val service: RecipeService,
    private val query: String
) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val pageNumber = params.key ?: 1
            val pageSize = RECIPES_PER_PAGE
            val offset = (pageNumber - 1) * pageSize
            val response = service.searchRecipes(
                query = query,
                addRecipeInformation = true,
                number = pageSize,
                offset = offset
            )
            val nextPageNumber = if (response.totalResults - pageSize > offset && offset < 900) {
                pageNumber + 1
            } else {
                null
            }
            LoadResult.Page(
                data = response.results.map { it.toDomainModel() },
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}

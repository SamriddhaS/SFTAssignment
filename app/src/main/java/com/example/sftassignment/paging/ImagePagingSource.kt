package com.example.sftassignment.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.data.network.ApiService

class ImagePagingSource(private val api:ApiService): PagingSource<Int, ImageItem>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageItem> {
        try {
            val position = params.key ?: 1
            val previousKey = if (position == 1) null else position - 1
            /*
             * We don't have the total page key in this api response.
             * So restricting the call after page 10 statically.
             * */
            val nextKey = if (position == 10) null else position + 1
            val response = api.getImageList(position)
            if (!response.isSuccessful) return LoadResult.Error(Exception("Api Error : ${response.message()}"))
            if (response.body().isNullOrEmpty()) return LoadResult.Error(Exception("No Image Found"))
            return LoadResult.Page(
                data = response.body()!!,
                prevKey = previousKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
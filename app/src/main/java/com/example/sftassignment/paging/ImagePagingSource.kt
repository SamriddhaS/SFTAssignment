package com.example.sftassignment.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.data.model.ImageListResponse
import com.example.sftassignment.data.network.ApiService

class ImagePagingSource(private val api:ApiService): PagingSource<Int, ImageListResponse>(){
    override fun getRefreshKey(state: PagingState<Int, ImageListResponse>): Int? {

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageListResponse> {
        /*return try {
            val position = params.key ?: 1
            val response = api.getImageList(position)

            return LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                *//*
                * We don't have the total page key in this api response.
                * So restricting the call after page 10 statically.
                * *//*
                nextKey = if (position == 10) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }*/
    }

}
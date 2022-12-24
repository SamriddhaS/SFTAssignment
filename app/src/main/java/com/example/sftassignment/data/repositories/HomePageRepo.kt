package com.example.sftassignment.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.paging.liveData
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.data.model.ImageListResponse
import com.example.sftassignment.data.network.ApiService
import com.example.sftassignment.paging.ImagePagingSource
import com.example.sftassignment.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class HomePageRepo
@Inject
constructor(
    private val apiService: ApiService
)
{
    fun getImages() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { ImagePagingSource(apiService) }
    ).liveData

}
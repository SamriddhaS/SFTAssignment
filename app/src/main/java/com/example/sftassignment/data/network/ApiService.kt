package com.example.sftassignment.data.network

import com.example.sftassignment.data.model.ImageListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    companion object{
        const val BASE_URL = "https://picsum.photos/v2/"
    }

    @GET("list")
    suspend fun getImageList(
        @Query("page") pageNo:Int,
        @Query("limit") limit: Int=20
    ):Response<ImageListResponse>

}
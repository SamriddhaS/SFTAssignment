package com.example.sftassignment.data.repositories

import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.data.model.ImageListResponse
import com.example.sftassignment.data.network.ApiService
import com.example.sftassignment.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class HomePageRepo
@Inject
constructor(
    private val apiService: ApiService
)
{
    suspend fun getImageList()
            : Resource<ArrayList<ImageItem>> {
        val response = try{
            val response = apiService.getTopRatedMovieList(1)
            if (response.code()!=200) return Resource.Error(message = "Api error")
            val result = response.body() ?: return Resource.Error(message = "Api error")
            if (result.isNullOrEmpty()) return Resource.Error(message = "No Movies Found")
            result
        }catch (e: Exception){
            return Resource.Error(message = e.message.toString())
        }
        return Resource.Success(response)
    }

}
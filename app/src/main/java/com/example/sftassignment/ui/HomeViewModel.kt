package com.example.sftassignment.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.data.repositories.HomePageRepo
import com.example.sftassignment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel

@Inject
constructor(
        private val repo: HomePageRepo
    ) :ViewModel() {

    var loadError = MutableSharedFlow<String>()
    var isLoading = MutableLiveData(false)
    private var topMovieListData = MutableLiveData(listOf<ImageItem>())
    val _topMovieListData = topMovieListData

    init {
        //makeApiCalls()
    }

    private fun makeApiCalls() {
        viewModelScope.launch {
            supervisorScope {
                try {
                    topRatedMoviesApiCall()
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun topRatedMoviesApiCall(){
        viewModelScope.launch {
            isLoading.value=true
            Log.d("ViewModel","3st Start")
            when(val result = repo.getImageList()){
                is Resource.Success -> {
                    val data = result.data
                    loadError.emit("")
                    isLoading.value=false
                    topMovieListData.value = data
                    Log.d("ViewModel","3st Done")
                }
                is Resource.Error -> {
                    loadError.emit(result.message?:"")
                    isLoading.value=false
                }
            }
        }
    }
}
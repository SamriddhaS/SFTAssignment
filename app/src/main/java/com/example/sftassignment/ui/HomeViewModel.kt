package com.example.sftassignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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
    val _topMovieListData = repo.getImages().cachedIn(viewModelScope)

}
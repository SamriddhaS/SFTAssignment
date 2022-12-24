package com.example.sftassignment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sftassignment.data.repositories.HomePageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
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
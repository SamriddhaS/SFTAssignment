package com.example.sftassignment.ui.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sftassignment.data.repositories.HomePageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel

@Inject
constructor(
        private val repo: HomePageRepo
    ) :ViewModel() {

    val _imageListData = repo.getImages().cachedIn(viewModelScope)

}
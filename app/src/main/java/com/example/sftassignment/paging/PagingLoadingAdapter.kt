package com.example.sftassignment.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sftassignment.databinding.PagingLoaderItemBinding

class PagingLoadingAdapter:LoadStateAdapter<PagingLoadingAdapter.ImageLoadingViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ImageLoadingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PagingLoaderItemBinding.inflate(inflater, parent, false)
        return ImageLoadingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState = loadState)
    }

    inner class ImageLoadingViewHolder(val binding: PagingLoaderItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState){
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

}
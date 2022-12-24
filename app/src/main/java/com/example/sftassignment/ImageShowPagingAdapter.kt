package com.example.sftassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.databinding.RecViewItemImagesBinding

class ImageShowPagingAdapter(private val context:Context) : PagingDataAdapter<
        ImageItem,
        ImageShowPagingAdapter.ImageShowViewHolder
        >(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageShowViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = RecViewItemImagesBinding.inflate(inflater, parent, false)
        return ImageShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageShowViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(context)
            .load(item?.download_url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.all_type_content_default)
                    .error(R.drawable.all_type_content_default)
            )
            .into(holder.binding.ivImage)
    }

    inner class ImageShowViewHolder(val binding: RecViewItemImagesBinding) : RecyclerView.ViewHolder(binding.root)
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
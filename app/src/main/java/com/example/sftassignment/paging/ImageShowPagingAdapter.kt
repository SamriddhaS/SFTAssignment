package com.example.sftassignment.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sftassignment.R
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.data.model.ImageItem.Companion.DEFAULT_DESC
import com.example.sftassignment.databinding.RecViewItemImagesBinding

class ImageShowPagingAdapter(
    private val context:Context,
    private val mInterface:OnRecItemClickListener
    ) : PagingDataAdapter<
        ImageItem,
        ImageShowPagingAdapter.ImageShowViewHolder
        >(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageShowViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = RecViewItemImagesBinding.inflate(inflater, parent, false)
        val viewHolder = ImageShowViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            mInterface.onRecItemClicked(getItem(viewHolder.absoluteAdapterPosition))
        }
        return viewHolder
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

        holder.binding.tvTitle.text = item?.author ?: ""
        holder.binding.tvDesc.text = item?.description ?: DEFAULT_DESC
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
    interface OnRecItemClickListener{
        fun onRecItemClicked(data:ImageItem?)
    }
}
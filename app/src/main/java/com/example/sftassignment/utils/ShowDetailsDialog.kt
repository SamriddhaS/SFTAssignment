package com.example.sftassignment.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sftassignment.R
import com.example.sftassignment.data.model.ImageItem
import com.example.sftassignment.databinding.LayoutDetailsDialogBinding

class ShowDetailsDialog(
    context: Context,
    private val data:ImageItem,
    private val onCloseBtnClicked:(Dialog)->Unit
) : Dialog(context) {

    private lateinit var binding: LayoutDetailsDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = LayoutDetailsDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window!!.setBackgroundDrawableResource(R.color.transparent)
        setCancelable(false)
        setCanceledOnTouchOutside(true)

        Glide.with(context)
            .load(data.download_url)
            .apply(
                RequestOptions()
                    .placeholder(R.color.black)
                    .error(R.color.black)
            )
            .into(binding.ivBackgroundImg)

        binding.tvDesc.text = data.description ?: ImageItem.DEFAULT_DESC

        binding.btnCross.setOnClickListener {
            onCloseBtnClicked(this)
        }

    }
}
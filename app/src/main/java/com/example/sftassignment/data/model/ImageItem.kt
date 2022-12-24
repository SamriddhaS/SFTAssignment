package com.example.sftassignment.data.model

data class ImageItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int,
    val description:String
){
    companion object{
        val DEFAULT_DESC = "There is no field available for description.So using this as description."
    }
}
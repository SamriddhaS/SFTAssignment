package com.example.sftassignment.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import android.widget.ImageView.ScaleType
import android.view.ViewGroup
import android.view.View.MeasureSpec

class AspectRatioImageViewKt : AppCompatImageView {

    constructor(context: Context) : super(context) {
        this.scaleType = ScaleType.FIT_XY
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        this.scaleType = ScaleType.FIT_XY
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        this.scaleType = ScaleType.FIT_XY
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val d = drawable
        if (d != null && d.intrinsicHeight > 0) {
            var width = layoutParams.width
            var height = layoutParams.height

            if (height == ViewGroup.LayoutParams.WRAP_CONTENT || width in 1..9999) {
                if (width <= 0) width = MeasureSpec.getSize(widthMeasureSpec)
                if (width > 0) height = width * d.intrinsicHeight / d.intrinsicWidth

                if (height > maxHeight) {
                    height = maxHeight
                    width = height * d.intrinsicWidth / d.intrinsicHeight
                }
            } else if (width == ViewGroup.LayoutParams.WRAP_CONTENT || height in 1..9999) {
                if (height <= 0) height = MeasureSpec.getSize(heightMeasureSpec)
                if (height > 0) width = height * d.intrinsicWidth / d.intrinsicHeight
                if (width > maxWidth) {
                    width = maxWidth
                    height = width * d.intrinsicHeight / d.intrinsicWidth
                }
            }
            if (width > 0 && height > 0) setMeasuredDimension(width, height) else super.onMeasure(
                widthMeasureSpec,
                heightMeasureSpec
            )
        } else super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
package com.aelsi2.practice11

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

class RoundedImageButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageButton(context, attrs) {


    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable is RoundedBitmapDrawable){
            super.setImageDrawable(drawable)
            return
        }
        val drawableOriginal = drawable as? BitmapDrawable ?: return
        val bmp = drawableOriginal.bitmap
        val drawableNew = RoundedBitmapDrawableFactory.create(resources, bmp)
        drawableNew.isCircular = true
        super.setImageDrawable(drawableNew)
    }
}
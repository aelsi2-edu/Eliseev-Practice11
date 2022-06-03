package com.aelsi2.practice11

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RandomAvatar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    init {
        lifecycleScope.launchWhenCreated {
            val def = async(Dispatchers.Default) { randomFace() }
            val drawable = def.await() ?: return@launchWhenCreated
            setImageDrawable(drawable)
        }
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
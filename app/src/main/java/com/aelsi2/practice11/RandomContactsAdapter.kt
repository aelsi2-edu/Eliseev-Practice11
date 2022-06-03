package com.aelsi2.practice11

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RandomContactsAdapter(private val contactsCount: Int) : RecyclerView.Adapter<RandomContactsAdapter.ViewHolder>() {
    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)
        private var imageButton : RoundedImageButton? = itemView.findViewById(R.id.ContactPfp)
        private var textView : TextView? = itemView.findViewById(R.id.ContactName)
        fun setImage(drawable: Drawable?) {
            imageButton?.setImageDrawable(drawable)
        }
        fun setText(text : String) {
            textView?.text = text
        }
        init {
            lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
            lifecycleScope.launchWhenCreated {
                val def = async(Dispatchers.Default) { randomFace() }
                val drawable = def.await() ?: return@launchWhenCreated
                imageButton?.setImageDrawable(drawable)
            }
            lifecycleScope.launchWhenCreated {
                val def = async(Dispatchers.Default) { randomName() }
                val string = def.await()
                textView?.text = string
            }
        }
        fun lifecycleCreate() {
            lifecycleRegistry.currentState = Lifecycle.State.CREATED
        }
        override fun getLifecycle(): Lifecycle {
            return lifecycleRegistry
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_row, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.lifecycleCreate()
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return contactsCount
    }
}
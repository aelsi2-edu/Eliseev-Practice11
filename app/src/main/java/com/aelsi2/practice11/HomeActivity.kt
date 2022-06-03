package com.aelsi2.practice11

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.URL

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var pfp : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()
    }
    private fun initViews() {
        lifecycleScope.launch {
            val def = async(Dispatchers.Default) { randomFace() }
            val drawable = def.await() ?: return@launch
            findViewById<ImageButton>(R.id.pfp).setImageDrawable(drawable)
        }
        findViewById<ImageButton>(R.id.contactsButton).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, ContactsActivity::class.java)
        startActivity(intent)
    }
}
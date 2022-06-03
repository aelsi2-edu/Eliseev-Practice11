package com.aelsi2.practice11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private val randomContactsAdapter = RandomContactsAdapter(10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        recyclerView = findViewById(R.id.ContactView)
        recyclerView.adapter = randomContactsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
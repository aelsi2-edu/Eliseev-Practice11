package com.aelsi2.practice11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.aelsi2.practice11.utils.debouncers.Debouncer
import com.aelsi2.practice11.utils.debouncers.OnClickDebouncer

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var firstNameField : EditText
    private lateinit var lastNameField : EditText
    private lateinit var phoneField : EditText
    private lateinit var acceptTOSCheckbox : CheckBox
    private lateinit var registerButton : Button
    private var tosChecked : Boolean = false;
    private lateinit var registerDebouncer: OnClickDebouncer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerDebouncer = OnClickDebouncer(this, 500)
        initViews()
    }
    private fun initViews() {
        setContentView(R.layout.activity_main)
        acceptTOSCheckbox = findViewById(R.id.tosCheckbox)
        registerButton = findViewById(R.id.registerButton)
        acceptTOSCheckbox.setOnClickListener(this)
        registerButton.setOnClickListener(registerDebouncer)
        firstNameField = findViewById(R.id.firstNameField)
        lastNameField = findViewById(R.id.lastNameField)
        phoneField = findViewById(R.id.phoneNumberField)
    }
    private fun fieldsFilled() : Boolean {
        if (firstNameField.text.toString().isEmpty()) return false
        if (lastNameField.text.toString().isEmpty()) return false
        if (phoneField.text.toString().isEmpty()) return false
        return true
    }
    private fun register() {
        if (!fieldsFilled())
        {
            Toast.makeText(this, getString(R.string.ask_fill_fields), Toast.LENGTH_SHORT).show()
            return
        }
        if (!tosChecked) {
            Toast.makeText(this, getString(R.string.ask_accept_tos), Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        when (view)
        {
            acceptTOSCheckbox -> {
                tosChecked = (view as CheckBox).isChecked
                return
            }
            registerButton -> {
                register()
            }
        }
    }
}
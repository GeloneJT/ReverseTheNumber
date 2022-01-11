package com.example.reversethenumber

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.reversethenumber.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.reverseButton.setOnClickListener{ reversedNumber()}
        binding.enteredNumber.setOnKeyListener{ view, keyCode, _ ->handleKeyEvent(view, keyCode)}
    }

    private fun reversedNumber() {
        val stringEditNumber = binding.enteredNumber.text.toString()

        if (stringEditNumber.matches("^[a-zA-Z]*$".toRegex())) {
            showAlertFail()
        }
        if (!stringEditNumber.matches("^[a-zA-Z]*$".toRegex())) {
            val result = stringEditNumber.reversed()
            "Reversed number is $result".also { binding.reverseResult.text = it }
        }
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
    private fun showAlertFail() {
        val alertFail = AlertDialog.Builder(this)
        alertFail.setTitle("Invalid characters")
        alertFail.setMessage("Cannot reverse an empty field")
        alertFail.setIcon(android.R.drawable.ic_dialog_alert)

        alertFail.setPositiveButton("Try Again") { _, _ ->
            Toast.makeText(this, "Reenter with numbers", Toast.LENGTH_LONG).show()
        }
        val myAlertFail: AlertDialog = alertFail.create()
        myAlertFail.show()
    }

}
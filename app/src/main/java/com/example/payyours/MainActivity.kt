package com.example.payyours

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlacePlus.setOnClickListener { addToValue(fieldPlace) }
        buttonPlayerPlus.setOnClickListener { addToValue(fieldPlayer) }
        buttonPlayerMinus.setOnClickListener { substractFromValue(fieldPlayer) }
        buttonPlaceMinus.setOnClickListener { substractFromValue(fieldPlace) }

    }

    private fun substractFromValue(field: EditText?) {
        changeValue(field, -1)
    }

    private fun addToValue(field: EditText?) {
        changeValue(field, +1)
    }

    private fun changeValue(field: EditText?, factor: Int) {
        val current = Integer.parseInt(field?.text.toString())
        val new = current + factor
        field?.setText("$new")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when(item.itemId) {
            R.id.menu_item_quit -> {
                finish()
                true
            }

            R.id.menu_item_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        return result
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val value = it.data?.getStringExtra("input")
        }

        // payment = SettingsActivity.getStoredPayment(this)
    }
}
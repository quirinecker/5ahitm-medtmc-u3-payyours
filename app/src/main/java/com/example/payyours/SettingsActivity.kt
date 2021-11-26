package com.example.payyours

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import java.text.DecimalFormat
import java.util.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        bt_ok.setOnClickListener {
            save();
        }

        val payment = getStoredPayment(this);
        et_price.setText(payment.pricePerUnitText)
        et_price.setText(payment.players)
    }

    private fun save() {
        val decimalFormat = DecimalFormat.getInstance(Locale.getDefault())
        val price = decimalFormat.parse(this.et_price.text.toString()).toFloat()
        val players = decimalFormat.parse(this.et_player.text.toString()).toInt()
        val preferences = this.applicationContext
            .getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)
            .edit();

        preferences.putFloat(PRICE_PER_UNIT_KEY, price)
        preferences.putInt(PLAYERS_KEY, players)

        preferences.apply()
        finish()
    }

    companion object {
        private const val PREFERENCES_FILENAME = "PayYoursPreferences"
        private const val PRICE_PER_UNIT_KEY = "PRICE_PER_UNIT"
        private const val PLAYERS_KEY = "DEFAULT_PLAYERS"
        private const val COURTS_KEY = "DEFAULT_COURTS"
        private const val DEFAULT_VALUE = 15

        fun getStoredPayment(context: Context): Payment {
            val preferences = context.applicationContext
                .getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

            return Payment(
                preferences.getInt(COURTS_KEY, DEFAULT_VALUE),
                preferences.getInt(PLAYERS_KEY, DEFAULT_VALUE),
                preferences.getInt(PRICE_PER_UNIT_KEY, DEFAULT_VALUE).toString()
            )
        }
    }
}
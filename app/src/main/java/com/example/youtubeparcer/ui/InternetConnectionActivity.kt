package com.example.youtubeparcer.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.youtubeparcer.R
import kotlinx.android.synthetic.main.activity_internet_connection.*

class InternetConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_connection)
        checkInet()
    }
    private fun isNetworkConnected() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnected == true
        if (isConnected) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Подключите интернет! и попробуйте еще раз", Toast.LENGTH_LONG)
                .show()
            return
        }
    }

    private fun checkInet() {
        btn_try.setOnClickListener {
            isNetworkConnected()
        }
    }
}

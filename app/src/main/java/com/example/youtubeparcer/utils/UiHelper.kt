package com.example.youtubeparcer.utils

import android.content.Context
import android.widget.Toast

class UiHelper {
    fun showToast(contex: Context, name: String) {
        Toast.makeText(contex, name, Toast.LENGTH_LONG).show()
    }
}
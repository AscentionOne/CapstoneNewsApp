package com.kenchen.capstonenewsapp.utils

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * Helper functions for the View layer of the app
 * */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

// Show message toast
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

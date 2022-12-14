package com.muhammetkdr.myrecipeapp.common.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnackbar(msg: String) {
    Snackbar.make(this, msg, 10000).show()
}

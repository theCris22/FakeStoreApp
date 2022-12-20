package com.crisnavarro.fakestore.core

import android.view.View

fun View.hide(vararg views: View) {
    views.map { it.visibility = View.GONE }
}

fun View.show(vararg views: View) {
    views.map { it.visibility = View.VISIBLE }
}
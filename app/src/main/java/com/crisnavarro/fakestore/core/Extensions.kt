package com.crisnavarro.fakestore.core

import android.content.Context
import android.view.View
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = FAKE_STORE)

fun View.hide(vararg views: View) {
    views.map { it.visibility = View.GONE }
}

fun View.show(vararg views: View) {
    views.map { it.visibility = View.VISIBLE }
}
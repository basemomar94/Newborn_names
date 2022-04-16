package com.bassem.newbornnames.utilities

import android.content.Context
import android.content.SharedPreferences

class Pref {
    companion object {
        private val preFileName = "Newborn"
        private var preferences: SharedPreferences? = null
        private var pref: Pref? = null

        fun init(context: Context) {
            preferences = context.getSharedPreferences(preFileName, Context.MODE_PRIVATE)
            pref = Pref()
        }

        fun getPref(): Pref {
            return pref ?: Pref()
        }
    }

}
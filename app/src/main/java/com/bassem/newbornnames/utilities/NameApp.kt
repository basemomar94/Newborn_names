package com.bassem.newbornnames.utilities

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.bassem.newbornnames.utilities.ThemeState.isDark

class NameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val pref = applicationContext?.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val s  = pref?.getBoolean("isDark", false)
        s?.let {
            changeMode(it)
        isDark=s
        }


    }

    private fun changeMode(status: Boolean) {
        val theme: Int = if (status) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO

        }
        AppCompatDelegate.setDefaultNightMode(theme)

    }
}
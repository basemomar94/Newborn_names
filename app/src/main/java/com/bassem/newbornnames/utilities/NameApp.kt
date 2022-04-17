package com.bassem.newbornnames.utilities

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.bassem.newbornnames.utilities.CONSTANTS.babySex
import com.bassem.newbornnames.utilities.CONSTANTS.isDark

class NameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        checkDeviceMood()
        val pref = applicationContext?.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val mood = pref?.getBoolean("isDark", false)
        mood?.let {
            changeMode(it)
            isDark = mood
        }
        val sex = pref?.getString("sex", "none")
        babySex = sex


    }

    private fun changeMode(status: Boolean) {
        val theme: Int = if (status) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO

        }
        AppCompatDelegate.setDefaultNightMode(theme)

    }

    private fun checkDeviceMood() {
        when (resources.configuration.uiMode) {
            Configuration.UI_MODE_NIGHT_YES -> isDark = true
            Configuration.UI_MODE_NIGHT_NO -> isDark = false

        }
        println(isDark)
    }
}
package com.bassem.newbornnames.utilities

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.bassem.newbornnames.utilities.CONSTANTS.babySex
import com.bassem.newbornnames.utilities.CONSTANTS.isDark
import com.bassem.newbornnames.utilities.CONSTANTS.isFirst

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
        val first = pref?.getBoolean("isFirst", true)
        isFirst = first!!

        /*   val first = pref?.getBoolean("isFirst", false)
           if (first != null) {
               isFirst = first
           }*/


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
        when (applicationContext?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        ) {
            Configuration.UI_MODE_NIGHT_YES -> isDark = true
            Configuration.UI_MODE_NIGHT_NO -> isDark = false

        }

        Log.d("App", isDark.toString())
    }
}
package com.bassem.newbornnames.ui.splach

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bassem.newbornnames.R
import com.bassem.newbornnames.utilities.CONSTANTS.babySex

class SplashFragment : Fragment(R.layout.splash_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            when (babySex) {
                "male" -> findNavController().navigate(R.id.action_splashFragment_to_names)
                "female" -> findNavController().navigate(R.id.action_splashFragment_to_names)
                else -> findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }

        }, 1000)
    }


}
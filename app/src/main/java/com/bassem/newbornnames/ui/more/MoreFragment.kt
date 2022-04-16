package com.bassem.newbornnames.ui.more

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.bassem.newbornnames.R
import com.bassem.newbornnames.databinding.MoreFragmentBinding
import com.bassem.newbornnames.utilities.Pref
import com.bassem.newbornnames.utilities.ThemeState.isDark

class MoreFragment : Fragment(R.layout.more_fragment) {
    var binding: MoreFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoreFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.switchB?.isChecked = isDark

        binding?.switchB?.setOnCheckedChangeListener { compoundButton, b ->
            changeMode(b)
            isDark = b
            val pref = context?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val edit = pref?.edit()
            edit?.putBoolean("isDark", b)
            edit?.apply()

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
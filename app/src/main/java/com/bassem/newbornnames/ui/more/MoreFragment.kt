package com.bassem.newbornnames.ui.more

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.bassem.newbornnames.R
import com.bassem.newbornnames.databinding.MoreFragmentBinding
import com.bassem.newbornnames.utilities.CONSTANTS.babySex
import com.bassem.newbornnames.utilities.CONSTANTS.isDark
import com.google.android.material.button.MaterialButtonToggleGroup

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
        isDark?.let {
            binding?.switchB?.isChecked = it
        }

        binding?.switchB?.setOnCheckedChangeListener { compoundButton, b ->
            changeMode(b)
            isDark = b
            val pref = context?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val edit = pref?.edit()
            edit?.putBoolean("isDark", b)
            edit?.apply()

        }
        binding?.toggleGroup?.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (view.findViewById<Button>(checkedId).text) {
                    "ولد" -> changeBabySex("male")
                    "بنت" -> changeBabySex("female")
                }
            }


        }

     // binding?.toggleGroup.setche


    }

    private fun changeMode(status: Boolean) {
        val theme: Int = if (status) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO

        }
        AppCompatDelegate.setDefaultNightMode(theme)

    }

    private fun changeBabySex(sex: String) {
        val pref: SharedPreferences =
            requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("sex", sex)
        babySex = sex

    }
}
package com.bassem.newbornnames.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.animation.content.Content
import com.bassem.newbornnames.R
import com.bassem.newbornnames.databinding.HomeFragmentBinding
import com.bassem.newbornnames.ui.names.NamesFragment
import com.bassem.newbornnames.utilities.CONSTANTS.babySex

class HomeFragment : Fragment(R.layout.home_fragment) {
    var binding: HomeFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.maleCard?.setOnClickListener {
            //goToNames("male")
            chooseBabySex("male")
        }
        binding?.femaleCard?.setOnClickListener {
           // goToNames("female")
            chooseBabySex("female")

        }
    }


    private fun goToNames(key: String) {
        val nav = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
        val bundle = Bundle()
        bundle.putString("key", key)
        nav.navigate(R.id.action_homeFragment_to_namesFragment, bundle)

    }

    private fun chooseBabySex(sex: String) {
        val pref = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("sex", sex)
        editor.apply()
        babySex = sex
        findNavController().navigate(R.id.action_homeFragment_to_namesFragment)

    }
}
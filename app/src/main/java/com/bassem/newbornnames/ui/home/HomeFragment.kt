package com.bassem.newbornnames.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bassem.newbornnames.R
import com.bassem.newbornnames.databinding.HomeFragmentBinding
import com.bassem.newbornnames.ui.names.NamesFragment

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
            replaceFragement(NamesFragment())

        }
    }

    private fun replaceFragement(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainerView, fragment)
        transaction?.commit()
    }
}
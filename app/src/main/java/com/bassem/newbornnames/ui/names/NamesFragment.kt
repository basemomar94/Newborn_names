package com.bassem.newbornnames.ui.names

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bassem.newbornnames.R
import com.bassem.newbornnames.adapters.SwipeAdapter
import com.bassem.newbornnames.databinding.NamesFragmentBinding
import com.bassem.newbornnames.entities.NameClass
import com.google.android.material.bottomnavigation.BottomNavigationView

class NamesFragment : Fragment(R.layout.names_fragment) {
    var binding: NamesFragmentBinding? = null
    lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NamesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView = requireActivity().findViewById(R.id.bottomAppBar)
        bottomNavigationView.visibility = View.VISIBLE
        val viewmodel = ViewModelProvider(this)[NamesViewModel::class.java]
        var key = this.arguments?.getString("key")
        when (key) {
            "male" -> {
                viewmodel.getBoyssNames()
                binding?.nameLayout?.background = requireActivity().getDrawable(R.drawable.babyboy)

            }
            "female" -> {
                viewmodel.getGirlsNames()
                binding?.nameLayout?.background = requireActivity().getDrawable(R.drawable.babygi)
            }
        }

        viewmodel.namesList.observe(viewLifecycleOwner) {
            if (it != null) {
                initSwipeView(it)
            }
        }


    }


    private fun initSwipeView(list: MutableList<NameClass>) {
        val swipeAdapter = SwipeAdapter(list)
        binding?.stackView?.apply {
            adapter = swipeAdapter

        }
    }

}
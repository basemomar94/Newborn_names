package com.bassem.newbornnames.ui.favorite

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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Favorite_Fragment : Fragment(R.layout.names_fragment), SwipeAdapter.Click {
    var binding: NamesFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NamesFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        Thread(Runnable {
            viewModel.getFavNames(requireContext())

        }).start()
        viewModel.favNames.observe(viewLifecycleOwner) {
            initSwipeView(it)
            println(it)
        }
    }


    private fun initSwipeView(list: MutableList<NameClass>) {
        val swipeAdapter = SwipeAdapter(list, this)
        binding?.stackView?.apply {
            adapter = swipeAdapter

        }
    }

    override fun onfavClick(item: NameClass) {

    }

    override fun onshareClick(item: NameClass) {
    }


}
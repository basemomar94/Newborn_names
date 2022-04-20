package com.bassem.newbornnames.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassem.newbornnames.R
import com.bassem.newbornnames.adapters.SearchAdapter
import com.bassem.newbornnames.databinding.SearchFragmentBinding
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.utilities.CONSTANTS.babySex

class SearchFragment : Fragment(R.layout.search_fragment) {
    var binding: SearchFragmentBinding? = null
    var searchAdapter: SearchAdapter? = null
    var viewHolder: SearchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHolder = ViewModelProvider(this)[SearchViewModel::class.java]
        when (babySex) {
            "male" -> viewHolder?.getNames("boys")
            "female" -> viewHolder?.getNames("girls")
        }
        viewHolder?.namesList?.observe(viewLifecycleOwner) {
            initRv(it)
            println(it)
        }
    }

    private fun initRv(list: MutableList<NameClass>) {
        searchAdapter = SearchAdapter(list)
        binding?.searchRv?.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)

        }
    }
}
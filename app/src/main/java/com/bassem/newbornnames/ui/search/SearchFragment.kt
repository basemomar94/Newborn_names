package com.bassem.newbornnames.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassem.newbornnames.R
import com.bassem.newbornnames.adapters.SearchAdapter
import com.bassem.newbornnames.databinding.SearchFragmentBinding
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.utilities.CONSTANTS.babySex

class SearchFragment : Fragment(R.layout.search_fragment), SearchAdapter.OnClick {
    var binding: SearchFragmentBinding? = null
    var searchAdapter: SearchAdapter? = null
    var viewModel: SearchViewModel? = null
    var namesList: MutableList<NameClass>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        namesList = mutableListOf()
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
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        checkSex()
        viewModel?.namesList?.observe(viewLifecycleOwner) {
            namesList = it
            initRv(namesList!!)
            endLoading()
        }

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    search(p0)
                }

                return true

            }
        })
    }

    private fun initRv(list: MutableList<NameClass>) {
        searchAdapter = SearchAdapter(list, this)
        binding?.searchRv?.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)

        }
    }

    private fun endLoading() {
        binding?.apply {
            loading2.visibility = View.GONE
            searchRv.visibility = View.VISIBLE
        }
    }

    private fun checkSex() {
        when (babySex) {
            "male" -> {
                viewModel?.getNames("boys")
                binding?.searchLayout?.setImageResource(R.drawable.babyboy)
            }
            "female" -> {
                viewModel?.getNames("girls")
                binding?.searchLayout?.setImageResource(R.drawable.babygi)

            }
        }
    }

    private fun search(input: String) {
        var searchOutput: MutableList<NameClass> = mutableListOf()
        for (name in namesList!!) {
            if (name.title.contains(input)) {
                searchOutput.add(name)
            }
        }
        searchAdapter?.addList(searchOutput)

    }

    override fun makeFav(name: NameClass, position: Int) {
        Thread {
            name.isFavorite = true
            viewModel?.addtoFav(name, requireContext())
            requireActivity().runOnUiThread {
                searchAdapter?.notifyItemChanged(position)
            }


        }.start()

    }

    override fun expandName(name: NameClass, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("name", name)
        val nav = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
        nav.navigate(R.id.action_search_to_nameViewFragment, bundle)

    }


}
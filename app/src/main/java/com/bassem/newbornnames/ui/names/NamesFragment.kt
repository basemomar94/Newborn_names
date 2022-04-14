package com.bassem.newbornnames.ui.names

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassem.newbornnames.R
import com.bassem.newbornnames.adapters.NamesAdapter
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

        val namesList: MutableList<NameClass> = mutableListOf()
        namesList.add(NameClass("باسم", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        namesList.add(NameClass("محمد", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        namesList.add(NameClass("محسن", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        namesList.add(NameClass("سعيد", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        namesList.add(NameClass("يحي", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        namesList.add(NameClass("باسم", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        namesList.add(NameClass("باسم", "اسم جميل جدا للمولود لا ينفع يكون اسم وحش "))
        initSwipeView(namesList)


    }

    private fun initStack(list: MutableList<NameClass>) {
        val namesAdapter = NamesAdapter(list)


    }


    private fun initRv(list: MutableList<NameClass>) {
        val namesAdapter = NamesAdapter(list)
        binding?.namesRv?.apply {
            adapter = namesAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initSwipeView(list: MutableList<NameClass>) {
        val swipeAdapter=SwipeAdapter(list)
        binding?.stackView?.apply {
            adapter=swipeAdapter

        }
    }

}
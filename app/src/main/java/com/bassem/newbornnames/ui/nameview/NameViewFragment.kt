package com.bassem.newbornnames.ui.nameview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bassem.newbornnames.R
import com.bassem.newbornnames.databinding.NameItemBinding
import com.bassem.newbornnames.entities.NameClass

class NameViewFragment : Fragment(R.layout.name_item) {
    var binding: NameItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NameItemBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = this.arguments?.get("name") as NameClass
        updateUi(name)
    }


    private fun updateUi(name: NameClass) {

        binding?.name?.text = name.title
        binding?.description?.text = name.description

    }
}
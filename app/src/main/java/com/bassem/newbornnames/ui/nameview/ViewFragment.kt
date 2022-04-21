package com.bassem.newbornnames.ui.nameview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bassem.newbornnames.R
import com.bassem.newbornnames.databinding.NameItemBinding
import com.bassem.newbornnames.entities.NameClass

class ViewFragment : Fragment(R.layout.name_item) {
    var binding: NameItemBinding? = null
    var viewModel: ViewViewModel? = null
    var name: NameClass? = null

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
        viewModel = ViewModelProvider(this)[ViewViewModel::class.java]
        name = this.arguments?.get("name") as NameClass
        updateUi(name!!)

        binding?.fav?.setOnClickListener {
            makeFav(name!!)
        }

        binding?.cancel?.setOnClickListener {
            removeFav(name!!)
        }
    }


    private fun updateUi(name: NameClass) {

        binding?.name?.text = name.title
        binding?.description?.text = name.description
        if (name.isFavorite) {
            binding?.fav?.setImageResource(R.drawable.red_favorite_24)
        }


    }

    private fun makeFav(name: NameClass) {
        if (!name.isFavorite) {
            binding?.fav?.setImageResource(R.drawable.red_favorite_24)
            Thread(Runnable {
                viewModel?.saveName(name, requireContext())
                requireActivity().runOnUiThread {
                    Toast.makeText(context, "تم إضافة اسم ${name.title}  للمفضلة", Toast.LENGTH_SHORT).show()
                }
            }).start()
        }
    }

    private fun removeFav(name: NameClass) {
        if (name.isFavorite) {
            binding?.fav?.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            Thread(Runnable {
                viewModel?.removeName(name, requireContext())
                requireActivity().runOnUiThread {
                    Toast.makeText(context, "تم حذف اسم ${name.title} من المفضلة", Toast.LENGTH_SHORT).show()
                }
            }).start()

        }

    }
}
package com.bassem.newbornnames.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bassem.newbornnames.R
import com.bassem.newbornnames.adapters.SwipeAdapter
import com.bassem.newbornnames.databinding.NamesFragmentBinding
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.utilities.CONSTANTS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Favorite_Fragment : Fragment(R.layout.names_fragment), SwipeAdapter.Click {
    var binding: NamesFragmentBinding? = null
    var viewModel: FavoriteViewModel? = null
    var namesList: MutableList<NameClass>? = null

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
        checkBabySex()

        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        Thread(Runnable {
            viewModel!!.getFavNames(requireContext())

        }).start()
        viewModel!!.favNames.observe(viewLifecycleOwner) {
            namesList = it
            initSwipeView(it)

            endLoading()
        }
        binding?.reloadBtu?.setOnClickListener {
            binding?.stackView?.reloadAdapterData()
            animate(it)
        }
    }


    private fun initSwipeView(list: MutableList<NameClass>) {
        val swipeAdapter = SwipeAdapter(list, this, requireContext())
        binding?.stackView?.apply {
            adapter = swipeAdapter

        }
    }

    override fun onfavClick(item: NameClass) {
        binding?.stackView?.onButtonClick(true)


    }

    override fun onshareClick(item: NameClass) {
        shareName(item)

    }

    override fun onCancel(item: NameClass) {
        binding?.stackView?.onButtonClick(false)
        Thread(Runnable {
            item.isFavorite = false
            viewModel?.removeFav(item, requireContext())


        }).start()

    }

    private fun endLoading() {
        binding?.apply {
            stackView.visibility = View.VISIBLE
            loading.visibility = View.GONE
        }
    }

    private fun shareName(name: NameClass) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "ايه رايك في اسم '${name.title}' "
                    + " معناه ${name.description}"
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun checkBabySex() {
        when (CONSTANTS.babySex) {
            "male" -> {
                binding?.nameLayout?.setImageResource(R.drawable.babyboy)

            }
            "female" -> {
                binding?.nameLayout?.setImageResource(R.drawable.babygi)
            }
        }
    }

    private fun animate(view: View) {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        view.startAnimation(animation)
    }


}
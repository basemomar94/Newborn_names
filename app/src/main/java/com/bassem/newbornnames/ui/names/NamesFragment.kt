package com.bassem.newbornnames.ui.names

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.bassem.newbornnames.utilities.CONSTANTS.babySex
import com.bassem.newbornnames.utilities.CONSTANTS.isFirst
import com.bassem.newbornnames.utilities.CONSTANTS.isUpdate
import com.google.android.material.bottomnavigation.BottomNavigationView

class NamesFragment : Fragment(R.layout.names_fragment), SwipeAdapter.Click {
    var binding: NamesFragmentBinding? = null
    lateinit var bottomNavigationView: BottomNavigationView
    var viewmodel: NamesViewModel? = null
    var swipeAdapter: SwipeAdapter? = null


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
        viewmodel = ViewModelProvider(this)[NamesViewModel::class.java]

        updateUi()
        Thread(Runnable {
            if (isFirst) {
                viewmodel!!.getDataFirebase()

            } else {
                babySex?.let { it1 -> viewmodel?.getDataRoom(requireContext(), it1) }

            }
        }).start()



        viewmodel!!.namesList.observe(viewLifecycleOwner) {
            if (it != null) {
                Thread(Runnable {
                    if (isFirst) {
                        viewmodel?.addtoDatabase(it, requireContext())
                        babySex?.let { it1 -> viewmodel?.getDataRoom(requireContext(), it1) }
                    }


                }).start()
            }
        }
        viewmodel!!.namesRoom.observe(viewLifecycleOwner) {
            Log.d("Room", it.toString())
            initSwipeView(it)
            endLoading()
            updateIsFirst()

        }

        binding?.cancelBu?.setOnClickListener {
            binding?.stackView?.onButtonClick(false)


        }

        binding?.reloadBtu?.setOnClickListener {
            binding?.stackView?.reloadAdapterData()
            animate(it)
        }

        binding?.favBtu?.setOnClickListener {
            binding?.stackView?.onButtonClick(true)
            // val currentCard=
            val n = swipeAdapter?.count
            println(swipeAdapter?.namesList?.size)

        }


    }


    private fun initSwipeView(list: MutableList<NameClass>) {
        swipeAdapter = SwipeAdapter(list, this, requireContext())
        binding?.stackView?.apply {
            adapter = swipeAdapter
        }

    }

    override fun onfavClick(item: NameClass) {
        Thread {
            item.isFavorite = true
            viewmodel?.addtoFav(item, requireContext())
            requireActivity().runOnUiThread {
                swipeAdapter?.notifyDataSetChanged()
                binding?.stackView?.onButtonClick(true)
            }

        }.start()

    }

    override fun onshareClick(item: NameClass) {

        shareName(item)
        animate(requireView().findViewById(R.id.share))
    }

    override fun onCancel(item: NameClass) {
        binding?.stackView?.onButtonClick(false)
        Thread(Runnable {
            viewmodel?.removeFav(item, requireContext())

        }).start()
    }

    private fun endLoading() {
        binding?.apply {
            stackbtuLayout.visibility = View.VISIBLE
            loading.visibility = View.GONE
        }
        //  updateIsFirst()
    }

    private fun shareName(name: NameClass) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "?????? ???????? ???? ?????? '${name.title}' "
                    + " ?????????? ${name.description}"
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun updateUi() {
        when (babySex) {
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

    private fun updateIsFirst() {
        val pref = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isFirst", false)
        editor.apply()
        isFirst = false
    }

}
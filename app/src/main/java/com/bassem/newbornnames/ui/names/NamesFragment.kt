package com.bassem.newbornnames.ui.names

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class NamesFragment : Fragment(R.layout.names_fragment), SwipeAdapter.Click {
    var binding: NamesFragmentBinding? = null
    lateinit var bottomNavigationView: BottomNavigationView
    var viewmodel: NamesViewModel? = null
    var swipeAdapter: SwipeAdapter? = null
    var namesList: MutableList<NameClass>? = null


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
        when (this.arguments?.getString("key")) {
            "male" -> {
                viewmodel!!.getBoyssNames()
                binding?.nameLayout?.setImageResource(R.drawable.babyboy)

            }
            "female" -> {
                viewmodel!!.getGirlsNames()
                binding?.nameLayout?.setImageResource(R.drawable.babygi)
            }
        }
        namesList = mutableListOf()

        viewmodel!!.namesList.observe(viewLifecycleOwner) {
            if (it != null) {
                endLoading()
                namesList = it
                initSwipeView(namesList!!)
            }
        }

        binding?.cancelBu?.setOnClickListener {
            Log.d("TAG", namesList!!.size.toString())
            binding?.stackView?.onButtonClick(false)
            showSnackbar()


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
        swipeAdapter = SwipeAdapter(list, this,requireContext())
        binding?.stackView?.apply {
            adapter = swipeAdapter
        }

    }

    override fun onfavClick(item: NameClass) {
        Thread {
            item.isFavorite = true
            viewmodel?.addtoFav(item, requireContext())
            requireActivity().runOnUiThread {
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
    }

    private fun endLoading() {
        binding?.apply {
            stackbtuLayout.visibility = View.VISIBLE
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

    private fun showSnackbar() {
        Snackbar.make(requireView(), "تم ازالة الاسم ", Snackbar.LENGTH_LONG).apply {
            show()
            setAction("تراجع") {

            }

        }
    }

    private fun animate(view: View) {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
        view.startAnimation(animation)
    }

}
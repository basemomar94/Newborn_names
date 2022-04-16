package com.bassem.newbornnames.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bassem.newbornnames.R
import com.bassem.newbornnames.entities.NameClass
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SwipeAdapter(
    var namesList: MutableList<NameClass>,
    val listner: Click,
    val context: Context

) : BaseAdapter() {
    override fun getCount(): Int {
        return namesList.size

    }

    override fun getItem(p0: Int): Any {

        return namesList[p0]
    }


    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater =
            parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.name_item, null)
        val currentItem = namesList[p0]
        val name: TextView = view!!.findViewById(R.id.name)
        val description = view.findViewById<TextView>(R.id.description)
        val fav = view.findViewById<ImageView>(R.id.fav)
        val share = view.findViewById<ImageView>(R.id.share)
        val cancel = view.findViewById<ImageView>(R.id.cancel)
        name.text = currentItem.title
        description.text = currentItem.description

        share.setOnClickListener {
            listner.onshareClick(currentItem)

        }
        fav.setOnClickListener {
            listner.onfavClick(currentItem)
        }
        if (currentItem.isFavorite) {
            fav.setImageResource(R.drawable.red_favorite_24)
        } else {
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        cancel.setOnClickListener {
            listner.onCancel(currentItem)
        }




        return view
    }

    interface Click {
        fun onfavClick(item: NameClass)
        fun onshareClick(item: NameClass)
        fun onCancel(item: NameClass)
    }

    fun addList(list: MutableList<NameClass>) {
        namesList=list
        notifyDataSetChanged()
    }
}
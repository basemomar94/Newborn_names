package com.bassem.newbornnames.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bassem.newbornnames.R
import com.bassem.newbornnames.entities.NameClass

class SwipeAdapter(
    val namesList: MutableList<NameClass>,
    //val context: Context
    val listner: Click
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
        name.text = currentItem.title
        description.text = currentItem.description
        share.setOnClickListener {
            listner.onshareClick(currentItem)
        }



        return view
    }

    interface Click {
        fun onfavClick(item: NameClass)
        fun onshareClick(item: NameClass)
    }
}
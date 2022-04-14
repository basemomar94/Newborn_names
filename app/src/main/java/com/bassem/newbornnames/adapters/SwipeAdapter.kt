package com.bassem.newbornnames.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bassem.newbornnames.R
import com.bassem.newbornnames.entities.NameClass

class SwipeAdapter(
    val namesList: MutableList<NameClass>,
    //val context: Context
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
        // Inflate the custom view
        val inflater = parent?.context?.
        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.name_item,null)
        val currentItem = namesList[p0]
        val name: TextView = view!!.findViewById(R.id.name)
        val description = view!!.findViewById<TextView>(R.id.description)
        name.text = currentItem.title
        description.text = currentItem.description


        return view
    }
}
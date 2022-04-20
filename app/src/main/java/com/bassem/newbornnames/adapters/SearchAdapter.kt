package com.bassem.newbornnames.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bassem.newbornnames.R
import com.bassem.newbornnames.entities.NameClass

class SearchAdapter(
    var namesList: MutableList<NameClass>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name = itemview.findViewById<TextView>(R.id.nameSearch)
        val like = itemview.findViewById<ImageView>(R.id.likeSearch)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = namesList[position]
        holder.name.text = name.title
    }

    override fun getItemCount() = namesList.size

    fun addList(list: MutableList<NameClass>) {
        namesList = list
        notifyDataSetChanged()
    }
}
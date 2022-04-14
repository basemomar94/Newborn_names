package com.bassem.newbornnames.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bassem.newbornnames.R
import com.bassem.newbornnames.entities.NameClass

class NamesAdapter(
    val namesList: MutableList<NameClass>
) : RecyclerView.Adapter<NamesAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name = itemview.findViewById<TextView>(R.id.name)
        val description = itemview.findViewById<TextView>(R.id.description)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.name_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = namesList[position]
        holder.name.text = name.title
        holder.description.text = name.description
    }

    override fun getItemCount(): Int {
        return namesList.size
    }


}
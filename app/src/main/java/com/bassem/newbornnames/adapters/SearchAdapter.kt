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
    var namesList: MutableList<NameClass>,
    val click: OnClick
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name = itemview.findViewById<TextView>(R.id.nameSearch)
        val like = itemview.findViewById<ImageView>(R.id.likeSearch)

        init {
            like.setOnClickListener {
                val name = namesList[adapterPosition]
                click.makeFav(name, adapterPosition)
            }
            itemview.setOnClickListener {
                val name = namesList[adapterPosition]
                click.expandName(name, adapterPosition)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = namesList[position]
        holder.name.text = name.title

        if (name.isFavorite) {
            holder.like.setImageResource(R.drawable.red_favorite_24)
        }
    }

    override fun getItemCount() = namesList.size

    fun addList(list: MutableList<NameClass>) {
        namesList = list
        notifyDataSetChanged()
    }

    interface OnClick {
        fun makeFav(name: NameClass, position: Int)
        fun expandName(name: NameClass, position: Int)
    }
}
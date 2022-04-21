package com.bassem.newbornnames.ui.nameview

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.local.NamesDatabase

class ViewViewModel : ViewModel() {


    fun saveName(name: NameClass, context: Context) {
        val db = NamesDatabase.getInstance(context)
        db.namesDao().addFavorite(name.id!!)

    }

    fun removeName(name: NameClass, context: Context) {
        val db = NamesDatabase.getInstance(context)
        db.namesDao().removeFavorite(name.id!!)
    }
}
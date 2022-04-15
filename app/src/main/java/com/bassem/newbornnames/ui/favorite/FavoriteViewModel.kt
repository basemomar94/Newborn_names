package com.bassem.newbornnames.ui.favorite

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.local.NamesDatabase

class FavoriteViewModel : ViewModel() {
    val favNames = MutableLiveData<MutableList<NameClass>>()


    fun getFavNames(context: Context) {
        val db = NamesDatabase.getInstance(context)
        favNames.postValue(db.namesDao().getNames())
    }

    fun removeFav(item: NameClass, context: Context) {
        val db = NamesDatabase.getInstance(context)
        db.namesDao().deleteName(item)

    }
}
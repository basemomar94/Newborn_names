package com.bassem.newbornnames.ui.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.local.NamesDatabase
import com.google.firebase.firestore.FirebaseFirestore

class SearchViewModel : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    val namesList = MutableLiveData<MutableList<NameClass>>()

    fun getNames(key: String) {
        var list: MutableList<NameClass> = mutableListOf()
        db.collection(key).get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (dc in it.result) {
                    list.add(dc.toObject(NameClass::class.java))
                }
                namesList.postValue(list)
            }

        }
    }

    fun getallNames(key: String, context: Context) {
        var db = NamesDatabase.getInstance(context)
        namesList.postValue(db.namesDao().getFilteredNames(key))
    }


    fun addtoFav(item: NameClass, context: Context) {
        var db = NamesDatabase.getInstance(context)
        db.namesDao().addFavorite(item.id!!)
    }

    fun removFav(item: NameClass, context: Context) {
        var db = NamesDatabase.getInstance(context)
        db.namesDao().removeFavorite(item.id!!)
    }
}
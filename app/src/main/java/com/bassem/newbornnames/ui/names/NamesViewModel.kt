package com.bassem.newbornnames.ui.names

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.newbornnames.entities.NameClass
import com.bassem.newbornnames.entities.Settings
import com.bassem.newbornnames.local.NamesDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Date
import java.sql.Timestamp

class NamesViewModel : ViewModel() {
    val namesList = MutableLiveData<MutableList<NameClass>>()
    val db = FirebaseFirestore.getInstance()
    val namesRoom = MutableLiveData<MutableList<NameClass>>()
    val settings = MutableLiveData<Settings>()




    fun addtoDatabase(list: MutableList<NameClass>, context: Context) {
        val db = NamesDatabase.getInstance(context).namesDao()
        db.addFirebase(list)
    }

    fun getDataFirebase() {
        var list: MutableList<NameClass> = mutableListOf()
        db.collection("names").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (dc in it.result) {
                        list.add(dc.toObject(NameClass::class.java))

                    }

                }
                namesList.postValue(list)
                Log.d("Firebase", namesList.toString())
                println("$namesList  NAMES")

            }
    }

    fun getDataRoom(context: Context, key: String) {
        val db = NamesDatabase.getInstance(context).namesDao()
        namesRoom.postValue(db.getFilteredNames(key))


    }

    fun addtoFav(item: NameClass, context: Context) {
        var db = NamesDatabase.getInstance(context)
        db.namesDao().addName(item)
    }
    fun removeFav(item: NameClass, context: Context) {
        var db = NamesDatabase.getInstance(context)
        db.namesDao().removeFavorite(item.id!!)
    }
}
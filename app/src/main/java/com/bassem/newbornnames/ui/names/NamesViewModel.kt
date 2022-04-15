package com.bassem.newbornnames.ui.names

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.newbornnames.entities.NameClass
import com.google.firebase.firestore.FirebaseFirestore

class NamesViewModel : ViewModel() {
    val namesList = MutableLiveData<MutableList<NameClass>>()
    val db = FirebaseFirestore.getInstance()


    fun getGirlsNames() {
        var list: MutableList<NameClass> = mutableListOf()
        db.collection("girls").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (dc in it.result) {
                    list.add(dc.toObject(NameClass::class.java))

                }

            }
            namesList.postValue(list)
        }

    }

    fun getBoyssNames() {
        var list: MutableList<NameClass> = mutableListOf()
        db.collection("boys").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (dc in it.result) {
                    list.add(dc.toObject(NameClass::class.java))

                }

            }
            namesList.postValue(list)
        }

    }
}
package com.bassem.newbornnames.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.bassem.newbornnames.entities.NameClass

@Dao
interface NamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addName(name: NameClass)

    @Delete
    fun deleteName(name: NameClass)

    @Query("Select * from namesTable")
    fun getNames(): MutableList<NameClass>

    @Insert(onConflict = REPLACE)
    fun addFirebase(list: MutableList<NameClass>)

    @Query("Select * from namesTable where sex = :key")
    fun getFilteredNames(key: String): MutableList<NameClass>

    @Query("Select * From namesTable where isFavorite = 1")
    fun getFavorite(): MutableList<NameClass>

    @Query("Update namesTable set isFavorite = 0 Where id=:keyId")
    fun removeFavorite(keyId: Int)

    @Query("Update namesTable set isFavorite = 1 Where id=:keyId")
    fun addFavorite(keyId: Int)


}
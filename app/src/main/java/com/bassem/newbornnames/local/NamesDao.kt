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

}
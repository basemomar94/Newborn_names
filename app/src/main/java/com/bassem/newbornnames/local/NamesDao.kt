package com.bassem.newbornnames.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bassem.newbornnames.entities.NameClass

@Dao
interface NamesDao {
    @Insert(onConflict = REPLACE)
    fun addName(name:NameClass)
    @Delete
    fun deleteName(name: NameClass)
    @Query("Select * from namesTable")
    fun getNames():MutableList<NameClass>

}
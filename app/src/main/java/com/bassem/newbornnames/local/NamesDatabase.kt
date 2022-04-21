package com.bassem.newbornnames.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bassem.newbornnames.entities.NameClass

private const val DATABASE_NAME = "NAMES_DATABASE"

@Database(entities = [NameClass::class], version = 4, exportSchema = false)
abstract class NamesDatabase : RoomDatabase() {

    abstract fun namesDao(): NamesDao

    companion object {
        @Volatile
        private var instance: NamesDatabase? = null

        fun getInstance(context: Context): NamesDatabase {
            return instance ?: synchronized(Any()) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): NamesDatabase {

            return Room.databaseBuilder(
                context.applicationContext, NamesDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        }


    }
}
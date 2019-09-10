package com.example.roomdb_person

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

    @Database(entities = arrayOf(PersonEntity::class), version = 1)
    abstract class AppDatabase:RoomDatabase(){
        abstract fun personDAO():PersonDataAccess
        companion object {
            private var INSTANCE: AppDatabase? = null
            fun getInstance(context: Context): com.example.roomdb_person.AppDatabase {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "persondb")
                        .build()
                }
                return INSTANCE as AppDatabase
            }
        }
    }



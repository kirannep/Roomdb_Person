package com.example.roomdb_person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name ="firstName") val firstName:String?,
    @ColumnInfo(name = "secondName") val secondName:String?
)


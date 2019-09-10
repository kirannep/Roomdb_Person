package com.example.roomdb_person

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PersonDataAccess {
    @Query("SELECT * FROM person")
    fun getAll(): Observable<List<PersonEntity>>

    @Query("SELECT * FROM person WHERE uid IN (:personID)")
    fun getAllByIDs(personID: IntArray):List<PersonEntity>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertUser(user:PersonEntity):Completable

    @Insert
    fun insertAll(persons:PersonEntity):Completable

    @Delete
    fun delete(person:PersonEntity)


}
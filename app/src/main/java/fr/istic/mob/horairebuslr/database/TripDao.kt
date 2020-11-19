package fr.istic.mob.horairebuslr.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TripDao {

    @Query("SELECT * FROM Trip")
    fun getAll(): List<Trip>;

    @Insert
    fun insertAll(vararg trip: Trip)

    @Delete
    fun delete(trip: Trip)
}
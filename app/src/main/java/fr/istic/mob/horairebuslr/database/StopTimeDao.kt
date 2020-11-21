package fr.istic.mob.horairebuslr.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StopTimeDao {

    @Query("SELECT * FROM StopTime")
    fun getAll(): List<StopTime>;

    @Insert
    fun insertAll(vararg StopTime: StopTime)

    @Delete
    fun delete(StopTime: StopTime)
}
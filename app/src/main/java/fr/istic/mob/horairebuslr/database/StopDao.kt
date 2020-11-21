package fr.istic.mob.horairebuslr.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StopDao {

    @Query("SELECT * FROM Stop")
    fun getAll(): List<Stop>;

    @Insert
    fun insertAll(vararg Stop: Stop)

    @Delete
    fun delete(Stop: Stop)
}
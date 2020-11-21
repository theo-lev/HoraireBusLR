package fr.istic.mob.horairebuslr.database

import androidx.room.*

@Dao
interface CalendarDao {

    @Query("SELECT * FROM Calendar")
    fun getAll(): List<Calendar>;

    @Insert
    fun insertAll(vararg Calendar: Calendar)

    @Delete
    fun delete(Calendar: Calendar)
}
package fr.istic.mob.horairebuslr.database

import androidx.room.*

@Dao
interface BusRouteDao {

    @Query("SELECT * FROM BusRoute")
    fun getAll(): List<BusRoute>;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg busRoute: BusRoute)

    @Delete
    fun delete(busRoute: BusRoute)
}
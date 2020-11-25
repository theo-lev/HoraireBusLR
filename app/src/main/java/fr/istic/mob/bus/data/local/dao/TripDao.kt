package fr.istic.mob.bus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.TripEntity

/**
 * Trip dao
 * */
@Dao
interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity)

    @Query("select * from trip")
    suspend fun getAllTrip(): MutableList<TripEntity>
}
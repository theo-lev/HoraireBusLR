package fr.istic.mob.bus.data.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.StopEntity
import fr.istic.mob.bus.util.StarContract

@Dao
interface StopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStop(stopEntity: StopEntity)

    @Query("select * from stop")
    suspend fun getAllStop(): MutableList<StopEntity>
    @Query ("SELECT DISTINCT stop.stopId, stop.stopName, stop.stopDesc, stop.stopLat, stop.stopLon, stop.wheelchairBoarding, trip.tripHeadSign, trip.directionId FROM stop, stop_time, trip WHERE trip.tripEntityId = stop_time.tripId AND stop_time.stopId = stop.stopId AND trip.routeId = :routeId AND trip.directionId = :directionId ORDER BY departureTime")
    fun getStops(
        routeId: String?,
        directionId: String?
    ): Cursor?
}
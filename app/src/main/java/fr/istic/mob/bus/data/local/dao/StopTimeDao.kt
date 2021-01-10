package fr.istic.mob.bus.data.local.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.istic.mob.bus.data.entity.StopTimeEntity
import fr.istic.mob.bus.util.StarContract

@Dao
interface StopTimeDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertStopTime(StopTimeEntity: StopTimeEntity)


    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.monday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")
    fun getStopTimeCursorMonday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?
    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.tuesday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")
    fun getStopTimeCursorTuesday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?
    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.wednesday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")

    fun getStopTimeCursorWednesday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?

    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.thursday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")
    fun getStopTimeCursorThursday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?


    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.friday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")
    fun getStopTimeCursorFriday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?

    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.saturday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")

    fun getStopTimeCursorSaturday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?

    @Query("SELECT DISTINCT stop_time.tripId, stop_time.stopId, stop_time.arrivalTime, stop_time.departureTime, stop_time.stopSequence, trip.tripEntityId, trip.tripHeadSign FROM stop_time, trip, calendar WHERE stop_time.tripId = trip.tripEntityId AND trip.serviceId=calendar.serviceId AND trip.routeId = :routeId AND stop_time.stopId = :stopId AND trip.directionId = :directionId AND calendar.sunday = 1 AND stop_time.arrivalTime > :arrivalTime GROUP BY stop_time.arrivalTime ORDER BY stop_time.arrivalTime")

    fun getStopTimeCursorSunday(
        routeId: String?,
        stopId: String?,
        directionId: String?,
        arrivalTime: String?
    ): Cursor?

    @Query("SELECT DISTINCT stop.stopName, stop_time.arrivalTime FROM stop_time, stop WHERE stop.stopId=stop_time.stopId AND stop_time.tripId = :tripId AND arrivalTime > :arrivalTime ORDER BY stop_time.arrivalTime")
    fun getRouteDetail(
        tripId: String?,
        arrivalTime: String?
    ): Cursor?
}
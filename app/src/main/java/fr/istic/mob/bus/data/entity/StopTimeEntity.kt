package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.istic.mob.bus.data.local.DataBase

/**
 * Stop time entity.
 * */
@Entity(tableName = "stop_time")
data class StopTimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tripId: String,
    val arrivalTime: String,
    val departureTime: String,
    val stopId: String,
    val stopSequence: String,
    val stopHeadsign: String,
    val pickupType: String,
    val dropOffType: String,
    val shapeDistTraveled: String
)
{
    companion object{
        fun parseStopTimeObject(csvStopTime :Map<String, String>):StopTimeEntity
        {
            return StopTimeEntity(id=1,
                tripId=csvStopTime["trip_id"]?: error(""),
                arrivalTime=csvStopTime["arrival_time"]?: error(""),
                departureTime=csvStopTime["departure_time"]?: error(""),
                stopId=csvStopTime["stop_id"]?: error(""),
                stopSequence=csvStopTime["stop_sequence"]?: error(""),
                stopHeadsign=csvStopTime["stop_headsign"]?: error(""),
                pickupType=csvStopTime["pickup_type"]?: error(""),
                dropOffType=csvStopTime["drop_off_type"]?: error(""),
                shapeDistTraveled=csvStopTime["shape_dist_traveled"]?: error("")
                )
        }
    }
}
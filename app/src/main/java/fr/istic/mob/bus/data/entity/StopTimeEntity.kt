package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.istic.mob.bus.data.local.DataBase

/**
 * Stop time entity.
 * */
@Entity(tableName = "stop_time")
data class StopTimeEntity   @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val tripId: String,
    val arrivalTime: String,
    val departureTime: String,
    val stopId: String,
    val stopSequence: String
)
{
    companion object{
        fun parseStopTimeObject(csvStopTime :Map<String, String>):StopTimeEntity
        {
            return StopTimeEntity(
                tripId=csvStopTime["trip_id"]?: error(""),
                arrivalTime=csvStopTime["arrival_time"]?: error(""),
                departureTime=csvStopTime["departure_time"]?: error(""),
                stopId=csvStopTime["stop_id"]?: error(""),
                stopSequence=csvStopTime["stop_sequence"]?: error(""))
        }
    }
}
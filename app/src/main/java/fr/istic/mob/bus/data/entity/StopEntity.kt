package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.istic.mob.bus.data.local.DataBase

/**
 * Stop entity.
 * */
@Entity(tableName = "stop")
data class StopEntity(
    @PrimaryKey
    val stopId: String,
    val stopName: String,
    val stopDesc: String,
    val stopLat: String,
    val stopLon: String,
    val wheelchairBoarding: String
) {
 companion object{
    fun parseStopObject(csvStop: Map<String, String>):StopEntity
     {
         return StopEntity(
             stopId=csvStop["stop_id"]?: error(""),
             stopName=csvStop["stop_name"]?: error(""),
             stopDesc=csvStop["stop_desc"]?: error(""),
             stopLat=csvStop["stop_lat"]?: error(""),
             stopLon=csvStop["stop_lon"]?: error(""),
             wheelchairBoarding=csvStop["wheelchair_boarding"]?: error("")
         )
     }
 }
}
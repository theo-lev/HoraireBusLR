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
    val stopCode: String,
    val stopName: String,
    val stopDesc: String,
    val stopLat: String,
    val stopLon: String,
    val zoneId: String,
    val stopUrl: String,
    val locationType: String,
    val parentStation: String,
    val stopTimezone: String,
    val wheelchairBoarding: String
) {
 companion object{
    fun parseStopObject(csvStop: Map<String, String>):StopEntity
     {
         return StopEntity(
             stopId=csvStop["stop_id"]?: error(""),
             stopCode=csvStop["stop_code"]?: error(""),
             stopName=csvStop["stop_name"]?: error(""),
             stopDesc=csvStop["stop_desc"]?: error(""),
             stopLat=csvStop["stop_lat"]?: error(""),
             stopLon=csvStop["stop_lon"]?: error(""),
             zoneId=csvStop["zone_id"]?: error(""),
             stopUrl=csvStop["stop_url"]?: error(""),
             locationType=csvStop["location_type"]?: error(""),
             parentStation=csvStop["parent_station"]?: error(""),
             stopTimezone=csvStop["stop_timezone"]?: error(""),
             wheelchairBoarding=csvStop["wheelchair_boarding"]?: error("")
         )
     }
 }
}
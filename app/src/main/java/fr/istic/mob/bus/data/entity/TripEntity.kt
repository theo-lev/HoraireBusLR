package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Trip entity.
 **/
@Entity(tableName = "trip")
data class TripEntity(
    @PrimaryKey(autoGenerate = true)
    val tripEntityId: Int,
    val routeId: String,
    val serviceId: String,
    val tripId: String,
    val tripHeadSign: String,
    val tripShortName: String,
    val directionId: String,
    val BlockId: String,
    val shapeId: String,
    val wheelchairAccessible: String,
    val bikesAllowed: String

) {
    companion object {
        fun parseTripObject(csvTrip: Map<String, String>): TripEntity {
            return TripEntity(
                tripEntityId = 1,
                routeId = csvTrip["route_id"] ?: error(""),
                serviceId = csvTrip["service_id"] ?: error(""),
                tripId = csvTrip["trip_id"] ?: error(""),
                tripHeadSign = csvTrip["trip_headsign"] ?: error(""),
                tripShortName = csvTrip["trip_short_name"] ?: error(""),
                directionId = csvTrip["direction_id"] ?: error(""),
                BlockId = csvTrip["block_id"] ?: error(""),
                shapeId = csvTrip["shape_id"] ?: error(""),
                wheelchairAccessible = csvTrip["wheelchair_accessible"] ?: error(""),
                bikesAllowed = csvTrip["bikes_allowed"] ?: error("")
            )
        }
    }
}


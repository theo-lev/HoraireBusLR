package fr.istic.mob.bus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Trip entity.
 **/
@Entity(tableName = "trip")
data class TripEntity @JvmOverloads constructor(
    @PrimaryKey
    val tripEntityId: String,
    val routeId: String,
    val serviceId: String,
    val tripHeadSign: String,
    val directionId: String,
    val BlockId: String,
    val wheelchairAccessible: String

) {
    companion object {
        fun parseTripObject(csvTrip: Map<String, String>): TripEntity {
            return TripEntity(
                routeId = csvTrip["route_id"] ?: error(""),
                tripEntityId = csvTrip["trip_id"] ?: error(""),
                serviceId = csvTrip["service_id"] ?: error(""),
                tripHeadSign = csvTrip["trip_headsign"] ?: error(""),
                directionId = csvTrip["direction_id"] ?: error(""),
                BlockId = csvTrip["block_id"] ?: error(""),
                wheelchairAccessible = csvTrip["wheelchair_accessible"] ?: error(""))
        }
    }
}


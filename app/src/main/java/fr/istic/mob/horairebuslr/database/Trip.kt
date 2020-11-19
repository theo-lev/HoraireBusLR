package fr.istic.mob.horairebuslr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Trip",
    foreignKeys = [ForeignKey(
        entity = BusRoute::class,
        parentColumns = arrayOf("route_id"),
        childColumns = arrayOf("route_id")
    )]
)
data class Trip(

    @PrimaryKey
    @ColumnInfo(name = "trip_id") val tripId: String?,
    @ColumnInfo(name = "route_id") val route_id: String?,
    @ColumnInfo(name = "service_id") val serviceId: String?,
    @ColumnInfo(name = "direction_id") val directionId: String?,
    @ColumnInfo(name = "trip_headsign") val tripHeadsign: String?,
    @ColumnInfo(name = "trip_short_name") val tripShortName: String?,

)
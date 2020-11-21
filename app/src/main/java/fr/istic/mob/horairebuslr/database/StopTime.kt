package fr.istic.mob.horairebuslr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "StopTime",
    foreignKeys = [ForeignKey(
        entity = Trip::class,
        parentColumns = arrayOf("trip_id"),
        childColumns = arrayOf("trip_id")
    ),
    ForeignKey(
        entity = Stop::class,
        parentColumns = arrayOf("stop_id"),
        childColumns = arrayOf("stop_id")
    )])
data class StopTime(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stopTime_id") val stopTimeId: String?,
    @ColumnInfo(name = "trip_id") val tripId: String?,
    @ColumnInfo(name = "stop_id") val stopId: String?,
    @ColumnInfo(name = "arrival_time") val arrivalTime: String?,
    @ColumnInfo(name = "departure_time") val departureTime: String?,

)
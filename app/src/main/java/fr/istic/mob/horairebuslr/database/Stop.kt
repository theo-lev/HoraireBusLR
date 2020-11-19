package fr.istic.mob.horairebuslr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Stop")
data class Stop(

    @PrimaryKey
    @ColumnInfo(name = "stop_id") val stopId: String?,
    @ColumnInfo(name = "stop_name") val stopName: String?,
    @ColumnInfo(name = "stop_desc") val stopDesc: String?,

)
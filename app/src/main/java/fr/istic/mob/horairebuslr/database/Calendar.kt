package fr.istic.mob.horairebuslr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Calendar")
data class Calendar(
    @PrimaryKey
    @ColumnInfo(name = "service_id") val serviceId: String?,
    @ColumnInfo(name = "monday") val monday: Boolean?,
    @ColumnInfo(name = "tuesday") val tuesday: Boolean?,
    @ColumnInfo(name = "wednesday") val wednesday: Boolean?,
    @ColumnInfo(name = "thursday") val thursday: Boolean?,
    @ColumnInfo(name = "friday") val friday: Boolean?,
    @ColumnInfo(name = "saturday") val saturday: Boolean?,
    @ColumnInfo(name = "sunday") val sunday: Boolean?,
    @ColumnInfo(name = "start_date") val startDate: String?,
    @ColumnInfo(name = "end_date") val end_date: String?
)
package fr.istic.mob.horairebuslr.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "BusRoute")
data class BusRoute(
    @PrimaryKey
    @ColumnInfo(name = "route_id") val routeId: String?,
    @ColumnInfo(name = "route_short_name") val routeShortName: String?,
    @ColumnInfo(name = "route_long_name") val routeLongName: String?,
    @ColumnInfo(name = "route_desc") val routeDesc: String?,
    @ColumnInfo(name = "route_type") val routeType: String?,
    @ColumnInfo(name = "route_color") val routeColor: String?,
    @ColumnInfo(name = "route_text_color") val routeTextColor: String?,
    @ColumnInfo(name = "route_sort_order") val routeSortOrder: String?
)